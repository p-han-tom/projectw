package com.mygdx.maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Game;

public class GridMap {
	private static int map[][];
	public static int tileDim;
	public static int offsetX;
	public static int offsetY;
	public static int mapLength;
	public GridMap(int map[][]) {
		this.setMap(map);
	}
	public static int[][] getMap() {
		return map;
	}
	public static void setMap(int map[][]) {
		GridMap.map = map;
		tileDim = 40;
		offsetX = (Game.WIDTH-(map.length*tileDim))/2;
		offsetY = (Game.HEIGHT-(map.length*tileDim))/2;
		mapLength = map.length;
	}
	public static void draw(ShapeRenderer sr) {
		for (int row = 0; row<map.length; row++) {
			for (int col = 0; col<map[row].length; col++) {
				if (map[row][col]==0) {
					sr.begin(ShapeType.Line);
					sr.setColor(Color.WHITE);
					sr.rect(row*tileDim+offsetX, col*tileDim+offsetY, tileDim, tileDim);
				}
				else if (map[row][col]==1) {
					sr.begin(ShapeType.Filled);
					sr.setColor(Color.WHITE);
					sr.rect(row*tileDim+offsetX, col*tileDim+offsetY, tileDim, tileDim);
					sr.end();
					sr.begin(ShapeType.Line);
					sr.setColor(Color.BLACK);
					sr.rect(row*tileDim+offsetX, col*tileDim+offsetY, tileDim, tileDim);
				}
				sr.end();
			}
		}
	}
}
