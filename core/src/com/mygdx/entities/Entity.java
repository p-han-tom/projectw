package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.maps.GridMap;

public abstract class Entity {
	private static int col; 
	private static int row;
	private static Color color;
	public Entity(int col, int row, Color color) {
		this.col = col;
		this.row= row;
		this.color = color;
	}
	public static void draw(ShapeRenderer sr, GridMap map) {
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.circle(col*map.tileDim+map.tileDim/2+map.offsetX, row*map.tileDim+map.tileDim/2+map.offsetY, (int)(map.tileDim/2*0.8));
		sr.end();
		
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
}
