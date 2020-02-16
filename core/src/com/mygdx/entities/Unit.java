package com.mygdx.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.maps.TileMap;

public class Unit extends Entity{
	public static class Pair {
		public int x;
		public int y;
		public Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "("+x+", "+y+")";
		}
	}
	
	private int moves, mLength, mWidth;
	private List<Pair> range = new ArrayList<Pair>();
	private boolean[][] visited;
	private ShapeRenderer sr = new ShapeRenderer();
	
	public Unit(String name, int col, int row, int tileDim, Sprite sprite, int moves) {
		super(name, col, row, tileDim, sprite);
		this.moves = moves;
	}
	
	public static int rollInitiative() {
		return (int)(Math.random()*20)+1;
	}
	
	public void move(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}
	
	//initializes a 2d visited array for possible locations to move to
	public void mapContext(int length, int width) {
		mLength = length;
		mWidth = width;
		visited = new boolean[mLength][mWidth];
	}
	
	//Movement range methods
	public void findRange(TileMap map, int row, int col, int moves) {
		if (moves >= 0 && row < map.mapLength && col < map.mapWidth && 
			map.getTile(row, col).passable) {
			if (!visited[row][col]) {
				range.add(new Pair(row, col));
				visited[row][col] = true;
			}	
		} else return;
		
		findRange(map,row+1,col,moves-1);
		findRange(map,row-1,col,moves-1);
		findRange(map,row,col+1,moves-1);
		findRange(map,row,col-1,moves-1);
	}
	
	public void clearRange() {
		visited = new boolean[mLength][mWidth];
		range.clear();
	}

	public boolean inRange(int row, int col) {
		for (Pair pair : range) if (pair.x == row && pair.y == col) return true;
		return false;
	}
	
	//getters and setters
	
	public int getMovement() {
		return moves;
	}
	

}
