package com.mygdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public class HUD {
	public static int HUDDisplace = 100;
	public int width = 250;
	private int padding = 10;
	
	private Unit cUnit;
	private Label lblCurrentTurn;
	private Label lblUnitInfo;
	
	private Table table;
	
	private BattleManager combat;
	
	public HUD(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font, BattleManager combat) {
		cUnit = combat.getCurrentUnit();
		
		table = new Table();
		
		table.debug();
		table.right();
		table.setPosition(Game.WIDTH-5, Game.HEIGHT-75);
		lblCurrentTurn = new Label("It is currently "+cUnit.getName()+"'s turn.", new Label.LabelStyle(font,Color.BLACK));
		lblCurrentTurn.setWrap(true);
		lblCurrentTurn.setAlignment(Align.center);
		lblCurrentTurn.setWidth(width);
		
		lblUnitInfo = new Label("Position: "+cUnit.getCol()+", "+cUnit.getRow(), new Label.LabelStyle(font,Color.BLACK));
		lblUnitInfo.setWrap(true);
		lblUnitInfo.setAlignment(Align.center);
		lblUnitInfo.setWidth(width);
		
		table.add(lblCurrentTurn).width(width).padTop(padding);
		table.row();
		table.add(lblUnitInfo).width(width).padTop(padding);
		stage.addActor(table);
		System.out.println(lblUnitInfo.getWidth());
	}
	public void draw(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font) {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
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
		cUnit = combat.getCurrentUnit();
		lblCurrentTurn.setText("It is currently "+cUnit+"'s turn.");
		lblUnitInfo.setText("Position: "+(cUnit.getCol()+1)+", "+(cUnit.getRow()+1));
	}
}
