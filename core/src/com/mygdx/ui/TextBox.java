package com.mygdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TextBox {
	private static int paddingX = 20;
	private static int paddingY = 20;
	private static int maxLineLength = 200;
	// IDK what halign does. Neither does google
	private static int halign = 200;
	private static int x, y;
	private static String text;
	private static Color bgColor, fgColor;
	public TextBox(int x, int y, String text, Color bgColor, Color fgColor) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.bgColor = bgColor;
		this.fgColor = fgColor;
	}
	public void draw(SpriteBatch batch, BitmapFont font, ShapeRenderer sr) {
		// GlyphLayout lets us measure the width and height of the text before we draw it
		GlyphLayout layout = new GlyphLayout(font, text, Color.WHITE, maxLineLength, halign, true);
		float textBoxWidth = layout.width;
		float textBoxHeight = -layout.height;
		
		// Draw the rectangle
		sr.begin(ShapeType.Filled);
		sr.setColor(bgColor);
		sr.rect(x, y, textBoxWidth+paddingX*2, textBoxHeight-paddingY*2);
		sr.end();
		
		// Draw the text
		batch.begin();
		font.setColor(fgColor);
		font.draw(batch, text, x+paddingX, y-paddingY, maxLineLength, halign, true);
		font.getLineHeight();
		batch.end();
	}
	public void setPaddingX(int paddingX) {
		this.paddingX = paddingX;
	}
	public void setPaddingY(int paddingY) {
		this.paddingY = paddingY;
	}
	public void setMaxLineLength(int maxLineLength) {
		this.maxLineLength = maxLineLength;
	}
}
