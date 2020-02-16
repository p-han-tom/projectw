package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.maps.TileMap;

public abstract class Entity {
	private String name;
	private int col; 
	private int row;
	private Sprite sprite;
	
	public Entity(String name, int col, int row, Sprite sprite) {
		this.setName(name);
		this.col = col;
		this.row= row;
		this.sprite = sprite;
		sprite.setSize(TileMap.tileDim, TileMap.tileDim);
	}
	
	public void draw(SpriteBatch batch, TileMap map) {
		batch.begin();
		sprite.setPosition(col*map.tileDim+map.offsetX, row*map.tileDim+map.offsetY);
		sprite.draw(batch);
		batch.end();
		
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
