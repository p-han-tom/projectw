package com.mygdx.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.maps.TileMap;

public class UnitRangeFinder {
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

	private TileMap map;
	private int row, col;
	private boolean[][] visited;
	private ShapeRenderer sr = new ShapeRenderer();
	
	public List<Pair> range = new ArrayList<Pair>();
	
	public UnitRangeFinder(TileMap map, int row, int col) {
		this.map = map;
		this.row = row;
		this.col = col;
		this.visited = new boolean[map.length][map.width];
	}
	
	public void findRange(TileMap map, int row, int col, int moves) {
		if (moves >= 0 && row < map.length && col < map.width && 
			map.getTile(row, col).passable && !map.getTile(row, col).isOccupied) {
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
		visited = new boolean[map.length][map.width];
		range.clear();
	}
	public boolean inRange(int row, int col) {
		for (Pair pair : range) if (pair.x == row && pair.y == col) return true;
		return false;
	}	
	public void displayRange(int offx, int offy, int tileDim) {
		sr.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		sr.setColor(new Color(0, 1, 0, 0.3f));
		for (Pair pair : range) {
			sr.rect(pair.y*tileDim+offx, pair.x*tileDim+offy, tileDim, tileDim);
		}
		sr.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}
}
