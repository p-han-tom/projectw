package com.mygdx.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.TileMap;

public abstract class Tile {
	
	protected static Texture spritesheet = new Texture("placeholder/sheet.png");
	
	//tile dimensions is the size of a tile
	//offsets x and y are for centering the grid 
	//movement is for the movement restriction of a tile
	//sprite dimension is for the size of a sprite on the sprite sheet
	protected int tileDim, offx, offy, movement;
	protected int spritedim = 16;
	
	protected Sprite sprite;
	
	//impassable tiles such as TreeTile cannot be moved through normally
	public boolean passable;
		
	public Tile(int tileDm, int offx, int offy) {
		this.tileDim = tileDm;
		this.offx = offx;
		this.offy = offy;
	}
	
	//method for drawing the tile
	public void render(SpriteBatch batch, int row, int col) {
		batch.begin();
		sprite.setPosition(col*tileDim+offx, row*tileDim+offy);
		sprite.draw(batch);
		batch.end();
	}
	
	public int moveValue() {
		return movement;
	}

}
