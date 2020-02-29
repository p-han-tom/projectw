package com.mygdx.abilities.range;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.entities.Unit;
import com.mygdx.entities.UnitRangeFinder.Pair;
import com.mygdx.maps.TileMap;

public class AbilityRange {
	public static class Pair {
		private int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "("+x+", "+y+")";
		}
	}
	
	public ArrayList<Pair> canTarget = new ArrayList<Pair>();
	
	private int range;
	private Color color;
	
	private static final float fade = 0.3f;
	private static ShapeRenderer sr = new ShapeRenderer();
	private TileMap map;
	private boolean[][] visited;
	
	public AbilityRange(int range, Color color) {
		this.range = range;
		this.color = color;
		this.color.a = fade;
	}
	
	public void createMapContext(TileMap map) {
		this.map = map;
		visited = new boolean[map.length][map.width];
	}
	
	public void reset() {
		visited = new boolean[map.length][map.width];
		canTarget.clear();
	}
	
	public void buildRange(int row, int col, int range) {	
		if (range >= 0 && row < map.length && col < map.width && 
				map.getTile(row, col).passable) {
				if (!visited[row][col]) {
					canTarget.add(new Pair(row, col));
					visited[row][col] = true;
				}	
		} else return;
			
		buildRange(row+1,col,range-1);
		buildRange(row-1,col,range-1);
		buildRange(row,col+1,range-1);
		buildRange(row,col-1,range-1);
	}
	
	public void draw() {
		sr.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		sr.setColor(color);
		for (Pair pair : canTarget) {
			sr.rect(pair.y * map.tileDim+map.offsetX, pair.x * map.tileDim + map.offsetY, map.tileDim,map.tileDim);
		}
		sr.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}
}
