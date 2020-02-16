package com.mygdx.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.TileMap;

public abstract class Tile {
	
	protected static Texture spritesheet = new Texture("placeholder/sheet.png");
	protected int tileDim, offx, offy, movement;
	protected int spritedim = 16;
	
	protected Sprite sprite;
	public boolean passable;
		
	public Tile(int tileDm, int offx, int offy) {
		this.tileDim = tileDm;
		this.offx = offx;
		this.offy = offy;
	}
	
	public abstract void render(SpriteBatch batch, int row, int col);

}
