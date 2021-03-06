package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FadingMessage {

	private int x;
	private int y;
	private double timer = 0;
	private float opacity = 1;
	private String text;
	private BitmapFont font = new BitmapFont(Gdx.files.internal("font/origa.fnt"), false);
	
	
	public FadingMessage(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer sr) {
		if (timer < 2.3) {
			font.getData().setScale(0.5f, 0.5f);
			font.setColor(new Color(1,1,1,opacity));
			opacity-=.0075;
			
			batch.begin();
			font.draw(batch, text, x, y);
			batch.end();
			timer += Gdx.graphics.getDeltaTime();
		}
	}
	
}
