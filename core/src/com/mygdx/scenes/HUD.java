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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public class HUD {
	public static int WIDTH = 100;
	
	public Stage stage;
	private Viewport viewport;
	
	private Unit cUnit;
	private Label lblCurrentTurn;
	private Label lblUnitInfo;
	
	private Table table;
	
	private BattleManager combat;
	
	public HUD(SpriteBatch batch, ShapeRenderer sr, BitmapFont font, BattleManager combat) {
		cUnit = combat.getCurrentUnit();
		viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		table = new Table();
		
//		table.debug();
		table.right();
		table.setPosition(800, Game.HEIGHT-75);
		lblCurrentTurn = new Label("It is currently "+cUnit.getName()+"'s turn.", new Label.LabelStyle(font,Color.BLACK));
		lblCurrentTurn.setWrap(true);
		lblCurrentTurn.setWidth(250);
		lblUnitInfo = new Label("Position: "+cUnit.getCol()+", "+cUnit.getRow(), new Label.LabelStyle(font,Color.BLACK));
		lblUnitInfo.setWrap(true);
		lblUnitInfo.setWidth(250);
		table.add(lblCurrentTurn).width(250).padTop(10);
		table.row();
		table.add(lblUnitInfo).width(250).padTop(10);
		stage.addActor(table);
	}
	public void draw(SpriteBatch batch, ShapeRenderer sr, BitmapFont font) {
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.WHITE);
		sr.rect(Game.WIDTH-260, 0, 260, Game.HEIGHT);
		sr.end();
		
		batch.setProjectionMatrix(stage.getCamera().combined);
		stage.draw();
	}
	public void update(BattleManager combat) {
		cUnit = combat.getCurrentUnit();
		lblCurrentTurn.setText("It is currently "+cUnit+"'s turn.");
		lblUnitInfo.setText("Position: "+(cUnit.getRow()+1)+", "+(cUnit.getCol()+1));
	}
}
