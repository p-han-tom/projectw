package com.mygdx.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
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
		
		if (moves-map.getTile(row, col).moveValue() >= 0) {
			findRange(map,row+1,col,moves-map.getTile(row, col).moveValue());
			findRange(map,row-1,col,moves-map.getTile(row, col).moveValue());
			findRange(map,row,col+1,moves-map.getTile(row, col).moveValue());
			findRange(map,row,col-1,moves-map.getTile(row, col).moveValue());
		} else return;
		
	}
	
	public void clearRange() {
		visited = new boolean[mLength][mWidth];
		range.clear();
	}

	public boolean inRange(int row, int col) {
		for (Pair pair : range) if (pair.x == row && pair.y == col) return true;
		return false;
	}
	
	public void displayRange(int offx, int offy) {
		sr.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		sr.setColor(new Color(0, 1, 0, 0.3f));
		for (Pair pair : range) {
			sr.rect(pair.y*this.tileDim+offx, pair.x*this.tileDim+offy, this.tileDim, this.tileDim);
		}
		sr.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}
	
	//getters and setters
	
	public int getMovement() {
		return moves;
	}
	

}
