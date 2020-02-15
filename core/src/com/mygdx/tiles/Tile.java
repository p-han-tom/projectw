package com.mygdx.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.TileMap;

public abstract class Tile {
	
	//static is feelsweirdman for tiledm, offx, and offy. needs to change
	protected static Texture spritesheet = new Texture("placeholder/sheet.png");
	protected static int tileDim = TileMap.tileDim;
	protected static int offx = TileMap.offsetX;
	protected static int offy = TileMap.offsetY;
	protected int spritedim = 16;
	
	protected int row, col, movement;
	protected Sprite sprite;
	public boolean passable;
		
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
