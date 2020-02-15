package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.gamestates.PlayState;
import com.mygdx.maps.GridMap;

public abstract class Entity {
	private static int col; 
	private static int row;
	private static Color color;
	private static Sprite sprite;
	public Entity(int col, int row, Color color, Sprite sprite) {
		this.col = col;
		this.row= row;
		this.color = color;
		this.setSprite(sprite);
		sprite.setSize(GridMap.tileDim, GridMap.tileDim);
	}
	public static void draw(SpriteBatch batch, GridMap map) {
		batch.begin();
		sprite.setPosition(col*map.tileDim+map.offsetX, row*map.tileDim+map.offsetY);
		sprite.draw(batch);
		batch.end();
		
	}
	public static int getCol() {
		return col;
	}
	public static void setCol(int col) {
		Entity.col = col;
	}
	public static int getRow() {
		return row;
	}
	public static void setRow(int row) {
		Entity.row = row;
	}
	public static Color getColor() {
		return color;
	}
	public static void setColor(Color color) {
		Entity.color = color;
	}
	//test comment for commit 
	public static Sprite getSprite() {
		return sprite;
	}
	public static void setSprite(Sprite sprite) {
		Entity.sprite = sprite;
	}
}
