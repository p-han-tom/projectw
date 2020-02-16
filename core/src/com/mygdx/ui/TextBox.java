package com.mygdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TextBox {
	public static void draw(SpriteBatch batch, BitmapFont font, ShapeRenderer sr, int x, int y, String text, Color bgColor, Color fgColor) {
		GlyphLayout layout = new GlyphLayout(font, text, Color.WHITE, 200, 200, true);
		float textBoxWidth = layout.width;
		float textBoxHeight = -layout.height;
		sr.begin(ShapeType.Filled);
		sr.setColor(bgColor);
		sr.rect(x, y, textBoxWidth+40, textBoxHeight-40);
		sr.end();
		batch.begin();
		font.setColor(fgColor);
		font.draw(batch, text, x+20, y-20, 200, 200, true);
		font.getLineHeight();
		batch.end();
	}
}
