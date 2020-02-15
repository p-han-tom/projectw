package com.mygdx.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.GridMap;

public abstract class Tile {
	protected static Texture spritesheet = new Texture("placeholder/sheet.png");
	protected static int tileDim = GridMap.tileDim;
	protected static int offx = GridMap.offsetX;
	protected static int offy = GridMap.offsetY;
	protected int spritedim = 16;
	
	protected int row, col;
	protected Sprite sprite;
	protected boolean passable;
	protected int movement;
	
	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public abstract void render(SpriteBatch batch);
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}
