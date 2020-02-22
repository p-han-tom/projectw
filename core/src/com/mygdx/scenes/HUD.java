package com.mygdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	private BattleManager combat;
	
	public HUD(SpriteBatch batch, BitmapFont font, BattleManager combat) {
		cUnit = combat.getCurrentUnit();
		viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		Table table = new Table();
		
		table.debug();
		table.right();
		table.setPosition(800, Game.HEIGHT-75);
		lblCurrentTurn = new Label("It is currently "+cUnit.getName()+"'s turn.", new Label.LabelStyle(font,Color.WHITE));
		lblCurrentTurn.setWrap(true);
		lblCurrentTurn.setWidth(250);
		lblUnitInfo = new Label("Position: "+cUnit.getCol()+", "+cUnit.getRow(), new Label.LabelStyle(font,Color.WHITE));
		lblUnitInfo.setWrap(true);
		lblUnitInfo.setWidth(250);
		table.add(lblCurrentTurn).width(250).pad(10);
		table.row();
		table.add(lblUnitInfo).width(250).pad(10);
		stage.addActor(table);
	}

	public void update(BattleManager combat) {
		cUnit = combat.getCurrentUnit();
		lblCurrentTurn.setText("It is currently "+cUnit+"'s turn.");
		lblUnitInfo.setText("Position: "+cUnit.getRow()+", "+cUnit.getCol());
	}
}
