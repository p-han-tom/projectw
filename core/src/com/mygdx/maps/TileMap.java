package com.mygdx.maps;

import com.mygdx.tiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class TileMap {
	private Tile[][] map;
	private SpriteBatch drawer = new SpriteBatch();
	public int tileDim;
	public int offsetX;
	public int offsetY;
	
	public int mapLength, mapWidth;

	private static Map<Integer, Tile> tileKey = new HashMap<>();
	
	
	public TileMap(int[][] map, int tileDim) {
		
		
		this.map = new Tile[map.length][map[0].length];
		this.tileDim = tileDim;
		
		mapLength = map.length;
		mapWidth = map[0].length;
		
		offsetX = (Game.WIDTH-(map.length*tileDim))/2;
		offsetY = (Game.HEIGHT-(map.length*tileDim))/2;
		
		//tile key
		tileKey.put(0, new GrassTile(tileDim, offsetX, offsetY));
		tileKey.put(1, new TreeTile(tileDim, offsetX, offsetY));
		
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
				this.map[row][col] = tileKey.get(map[row][col]); 
			}
		}
	}
	
	public Tile getTile(int row, int col) {
		return map[row][col];
	}
	
	public void draw() {
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
//				if (col == 0 || row == 0 || col == map[0].length-1 || row == map.length-1) {
//					tileKey.get(0).render(drawer, row, col);
//				}
				map[row][col].render(drawer, row, col);
				
			}
		}
	}
	

	
	
}
