package com.mygdx.scenes;

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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.abilities.Ability;
import com.mygdx.abilities.Fireball;
import com.mygdx.abilities.Slash;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public class HUD {
	public static int HUDDisplace = 100;
	public int width = 250;
	private int padding = 10;

	private Unit cUnit;
	private Label lblCurrentTurn, lblUnitInfo, lblRound;
	private Button atest1;

	// Tracks the currently selected ability. Null is no abilities are selected.
	private Ability activatedAbility = null;

	private Table tableText;
	private Table tableAbilities;

	private BattleManager combat;

	private Ability[] testAbilities = {new Slash(), new Fireball(), null, null};
	private Button[] abilityButtons = new Button[4];

	public HUD(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font, BattleManager combat) {
		cUnit = combat.getCurrentUnit();

		tableText = new Table();

		//		table.debug();
		tableText.right();
		tableText.setPosition(Game.WIDTH-5, Game.HEIGHT-75);

		lblCurrentTurn = new Label("It is currently "+cUnit.getName()+"'s turn.", new Label.LabelStyle(font,Color.WHITE));
		lblCurrentTurn.setWrap(true);
		lblCurrentTurn.setAlignment(Align.center);
		lblCurrentTurn.setWidth(width);

		lblUnitInfo = new Label("HP: "+cUnit.attribute.maxHP+"/"+cUnit.attribute.maxHP, new Label.LabelStyle(font,Color.WHITE));
		lblUnitInfo.setWrap(true);
		lblUnitInfo.setAlignment(Align.center);
		lblUnitInfo.setWidth(width);

		lblRound = new Label("Combat round: " + combat.getRound(), new Label.LabelStyle(font, Color.WHITE));
		lblRound.setWrap(true);
		lblRound.setAlignment(Align.center);
		lblRound.setWidth(width);

		tableText.add(lblCurrentTurn).width(width).padTop(padding);
		tableText.row();
		tableText.add(lblUnitInfo).width(width).padTop(padding);
		tableText.row();
		tableText.add(lblRound).width(width).padTop(padding);

		tableAbilities = new Table();

		tableAbilities.debug();
		tableAbilities.right();
		tableAbilities.setPosition(Game.WIDTH-5, 0);
		tableAbilities.add(abilityButtons[0]);
		tableAbilities.add(abilityButtons[1]);
		tableAbilities.row();
		tableAbilities.add(abilityButtons[2]);
		tableAbilities.add(abilityButtons[3]);
		
		for (int i = 0; i < 4; i++) {
			if (testAbilities[i]!=null) {
				testAbilities[i].range.createMapContext(combat.map);
				testAbilities[i].range.buildRange(combat.getCurrentUnit().getRow(), combat.getCurrentUnit().getCol(), testAbilities[i].getAbilityRange());
				Drawable abilityIcon = new TextureRegionDrawable(testAbilities[i].getIcon());
				Button button = new Button(abilityIcon);
				button.setSize(75, 75);
				ClickListener cl = new ClickListener() {
					public void clicked(InputEvent event, float x, float y) {
						// 1 = i but it doesnt work like wtf
						if (activatedAbility==null) activatedAbility = testAbilities[i];
						else activatedAbility = null;
					}
				};
				button.addListener(cl);
			}
		}
		stage.addActor(atest1);

		stage.addActor(tableText);

	}
	public void draw(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font) {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.DARK_GRAY);
		sr.rect(Game.WIDTH-(width+padding), 0, (width+padding), Game.HEIGHT);
		sr.end();

		sr.begin();
		sr.setColor(Color.BLACK);
		sr.rect(Game.WIDTH-(width+padding)+padding/2, padding/2, width, Game.HEIGHT-padding);
		sr.end();

		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.draw();
	}
	public void update(BattleManager combat) {
		for(int i = 0; i < 4; i++) {
			testAbilities[i].range.reset();
			testAbilities[i].range.buildRange(cUnit.getRow(), cUnit.getCol(), testAbilities[i].getAbilityRange());
		}
		cUnit = combat.getCurrentUnit();
		lblCurrentTurn.setText("It is currently "+cUnit+"'s turn.");
		lblUnitInfo.setText("HP: "+cUnit.getHp()+"/"+cUnit.attribute.maxHP);
		lblRound.setText("Combat round: " + combat.getRound());
	}
}
