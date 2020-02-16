package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.TileMap;

// An entity is anything that has a position on the map
public abstract class Entity {
	private String name;
	private int col; 
	private int row;
	private Sprite sprite;
	
	public Entity(String name, int col, int row, int tileDim, Sprite sprite) {
		this.setName(name);
		this.col = col;
		this.row= row;
		this.sprite = sprite;
		sprite.setSize(tileDim, tileDim);
	}

	public void draw(SpriteBatch batch, TileMap map) {
		batch.begin();
		sprite.setPosition(col*map.tileDim+map.offsetX, row*map.tileDim+map.offsetY);
		sprite.draw(batch);
		batch.end();

	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
