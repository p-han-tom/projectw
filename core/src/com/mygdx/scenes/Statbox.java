package com.mygdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.managers.BattleManager;

public class Statbox {
	private VerticalGroup vgroup;
	private Label lblStats;
	public Statbox(Stage stage, SpriteBatch batch, ShapeRenderer sr, BitmapFont font, BattleManager combat) {
		vgroup = new VerticalGroup();
		vgroup.debug();
		lblStats = new Label(text, new Label.LabelStyle(font,Color.BLACK))
		
	}
	public void clear() {
		for ()
	}
}
