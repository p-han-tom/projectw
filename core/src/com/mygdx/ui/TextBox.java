package com.mygdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TextBox {
	private int paddingX = 20, paddingY = 20;
	private int maxLineLength = 200;
	// halign = horizontal align??
	private int halign = 200;
	private int x, y;
	private String text;
	private Color bgColor, fgColor;
	private float textWidth, textHeight;
	private static float totalWidth, totalHeight;

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
		textWidth = layout.width;
		textHeight = -layout.height;

		totalWidth = textWidth+paddingX*2;
		totalHeight = textHeight-paddingY*2;
		// Draw the rectangle
		sr.begin(ShapeType.Filled);
		sr.setColor(bgColor);
		sr.rect(x, y, totalWidth, totalHeight);
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
	public float getTotalWidth() {
		return totalWidth;
	}
	public float getTotalHeight() {
		System.out.println(totalHeight);
		return totalHeight;
	}
}
