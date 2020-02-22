package com.mygdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Game;

public class HUD {
	public Stage stage;
	private Viewport viewport;
	
	private String currentTurn;
	private Label lblCurrentTurn;
	
	public HUD(SpriteBatch batch, BitmapFont font) {
		viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		
		Table table = new Table();
		table.top().left();
		table.setFillParent(true);
		
		lblCurrentTurn = new Label("It is currently "+currentTurn+"'s turn.", new Label.LabelStyle(font,Color.WHITE));
		
		table.add(lblCurrentTurn).padTop(20).padLeft(20);
		stage.addActor(table);
	}

	public void setCurrentTurn(String currentTurn) {
		this.currentTurn = currentTurn;
	}
	public void update() {
		lblCurrentTurn.setText("It is currently "+currentTurn+"'s turn.");
	}
}
