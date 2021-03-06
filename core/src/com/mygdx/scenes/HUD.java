package com.mygdx.scenes;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.abilities.Ability;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.maps.TileMap;

public class HUD {
	public static int HUDDisplace = 100;
	private static SpriteBatch batch;
	private static ShapeRenderer sr;
	private BattleManager combat;
	
	public int width = 250;
	private int padding = 10;
	
	private Unit cUnit;
	private Label lblCurrentTurn, lblUnitInfo, lblRound;
	
	private Table tableMain, tableAbilities, tableBottom;
		
	private TextButton btnEndTurn;
	private double endPressFlicker = 0;
	
	private List<Ability> abilityList;
	private List<Button> abilityButtonList = new ArrayList<Button>();
	private boolean abilityActivated = false;
	public boolean endTurnPressed = false;
	private int buttonIndex;
	
	public boolean abilityUsed = false;
	
	public HUD(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font, BattleManager combat) {
		
		this.combat = combat;
		
		getNextAbilities();
		
		// This line needs to be here for buttons to work
		Gdx.input.setInputProcessor(stage);
		
		cUnit = combat.cUnit;
		
		tableMain = new Table();
		tableMain.right();
		tableAbilities = new Table();
		
		tableBottom = new Table();
		tableBottom.right();
		
		lblCurrentTurn = new Label("It is currently "+cUnit.getName()+"'s turn.", new Label.LabelStyle(font,Color.WHITE)) {{
			setWrap(true);
			setAlignment(Align.center);
			setWidth(width);
		}};
		
		lblUnitInfo = new Label("HP: "+cUnit.attribute.maxHP+"/"+cUnit.attribute.maxHP, new Label.LabelStyle(font,Color.WHITE)) {{
			setWrap(true);
			setAlignment(Align.center);
			setWidth(width);
		}};
		
		
		lblRound = new Label("Combat round: " + combat.round, new Label.LabelStyle(font, Color.WHITE)) {{
			setWrap(true);
			setAlignment(Align.center);
			setWidth(width);
		}};
		
		// Uncomment these to see table outlines
//		tableMain.debug();
//		tableAbilities.debug();
//		tableBottom.debug();
		
		tableMain.add(lblCurrentTurn).width(width).padTop(padding);
		tableMain.row();
		
		tableMain.add(lblUnitInfo).width(width).padTop(padding);
		tableMain.row();
		
		tableMain.add(lblRound).width(width).padTop(padding);
		tableMain.row();
		
		
		
		getNextButtons();
		tableMain.add(tableAbilities);
		
		
		tableMain.setPosition(Game.WIDTH-padding/2, Game.HEIGHT-125);
		
		stage.addActor(tableMain);
		
		TextButtonStyle styleEndTurn = new TextButtonStyle();
		styleEndTurn.font = font;
		styleEndTurn.fontColor = Color.YELLOW;

		btnEndTurn = new TextButton("End Turn", styleEndTurn) {
			{
				right();
				setSize(150,75);
				setPosition(Game.WIDTH-width+(width-150)/2-padding/2, 250);
				addListener(new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						abilityActivated = false;
						endTurnPressed = true;
					}
				});
			}
		};
		
		stage.addActor(btnEndTurn);
	}
	public void draw(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font) {
		// Hud gray rectangle
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.DARK_GRAY);
		sr.rect(Game.WIDTH-(width+padding), 0, (width+padding), Game.HEIGHT);
		sr.end();
		
		//Hud rectangle gray border
		sr.begin();
		sr.setColor(Color.BLACK);
		sr.rect(Game.WIDTH-(width+padding)+padding/2, padding/2, width, Game.HEIGHT-padding);
		sr.end();
		
		// Highlight ability button and range
		if (abilityActivated && !abilityUsed) {
			sr.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL30.GL_BLEND);
			Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			sr.setColor(new Color(1,0,0,0.3f));
			sr.rect(abilityButtonList.get(buttonIndex).getX()+tableMain.getX()+tableAbilities.getX(), abilityButtonList.get(buttonIndex).getY()+tableMain.getY()+tableAbilities.getY(), abilityButtonList.get(buttonIndex).getWidth(),abilityButtonList.get(buttonIndex).getHeight());
			
			sr.end();
			Gdx.gl.glDisable(GL30.GL_BLEND);
			abilityList.get(buttonIndex).range.draw();
		}
		
		// Highlights end turn button if mouse is held down
		if ((MouseButtons.isLeftDown() && MouseButtons.getX() >= btnEndTurn.getX() && MouseButtons.getX() <= btnEndTurn.getX()+btnEndTurn.getWidth()
			&& Game.HEIGHT-MouseButtons.getY() >= btnEndTurn.getY() && Game.HEIGHT-MouseButtons.getY() <= btnEndTurn.getY()+btnEndTurn.getHeight()) || 
				(endPressFlicker > 0 && endPressFlicker <= 0.1)) {
			sr.begin(ShapeType.Filled);
			Gdx.gl.glEnable(GL30.GL_BLEND);
			Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			sr.setColor(new Color(0,1,0,0.3f));
			sr.rect(btnEndTurn.getX(), btnEndTurn.getY(), btnEndTurn.getWidth(), btnEndTurn.getHeight());
			sr.end();
			Gdx.gl.glDisable(GL30.GL_BLEND);
			endPressFlicker += Gdx.graphics.getDeltaTime();
		} else endPressFlicker = 0;
		
		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.draw();
	}
	
	public void update(BattleManager combat) {
		if (!combat.canMove) getNextAbilityRange(combat.cUnit);
		if (endTurnPressed) {
			endTurnPressed = false;
			abilityUsed = false;
			combat.getNextTurn();
			getNextAbilityRange(combat.cUnit);
			abilityButtonList.clear();
			getNextAbilities();
			getNextButtons();
		}
		
		if (!abilityUsed) combat.abilitySelected = abilityActivated;
		else combat.abilitySelected = false;
		cUnit = combat.cUnit;
		lblCurrentTurn.setText("It is currently "+cUnit+"'s turn.");
		lblUnitInfo.setText("HP: "+cUnit.getHp()+"/"+cUnit.attribute.maxHP);
		lblRound.setText("Combat round: " + combat.round);
	}
	
	public void getNextAbilityRange(Unit unit) {
		for (Ability ability : abilityList) {
			ability.range.clearRange();
//			ability.range.buildRange(unit.getRow() ,unit.getCol(), ability.getAbilityRange());
		}
	}
	
	public void clearRange() {abilityActivated = false;}
	public Ability getCurrentAbility() {return abilityList.get(buttonIndex);}
	
	private void getNextAbilities() {
		// Loops through abilities to initialize their range and create a button for each one
		abilityList = combat.cUnit.abilities;	
		for (int i = 0; i < abilityList.size(); i ++) {
			final int index = i;
			abilityList.get(i).range.createMapContext(combat.map);
			abilityList.get(i).range.buildRange(combat.cUnit.getRow(), combat.cUnit.getCol(), abilityList.get(i).getAbilityRange());
			abilityButtonList.add(new Button(new TextureRegionDrawable(abilityList.get(i).getIcon())) {
				{
					setSize(60,60);
					addListener(new ClickListener() {
						public void clicked(InputEvent event, float x, float y) {
							abilityActivated = !abilityActivated;
							buttonIndex = index;
						}
					});
				}
			});
		}
	}
	
	private void getNextButtons() {
		tableAbilities.clear();
		for (Button button : abilityButtonList) {
			tableAbilities.add(button)
			.width(button.getWidth())
			.height(button.getHeight())
			.pad(25);
		}
		
//		tableMain.add(tableAbilities);
		
	}
	
}
