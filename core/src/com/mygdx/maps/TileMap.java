package com.mygdx.maps;

import com.mygdx.tiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class TileMap {
	private Tile[][] map;
	private SpriteBatch batch = new SpriteBatch();
	private static Map<Integer, Tile> tileKey = new HashMap<>();
	public int tileDim, offsetX, offsetY, mapLength, mapWidth;

	public TileMap(int[][] map, int tileDim) {
		
		//intakes a 2d int array and copies dimensions to tile array
		this.map = new Tile[map.length][map[0].length];
		this.tileDim = tileDim;
		
		mapLength = map.length;
		mapWidth = map[0].length;
		
		//offsets for centering grid
		offsetX = (Game.WIDTH-(map.length*tileDim))/2;
		offsetY = (Game.HEIGHT-(map.length*tileDim))/2;
		
		//tile keys 
		tileKey.put(0, new GrassTile(tileDim, offsetX, offsetY));
		tileKey.put(1, new TreeTile(tileDim, offsetX, offsetY));
		tileKey.put(2, new MudTile(tileDim, offsetX, offsetY));
		
		//loops through each element in int array and creates a tile based off int key value
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
				this.map[row][col] = tileKey.get(map[row][col]); 
			}
		}
	}
	
	public Tile getTile(int row, int col) {
		return map[row][col];
	}
	
	//draw method for displaying grid
	public void draw() {
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
				map[row][col].render(batch, row, col);
				
			}
		}
	}
	

	
	
}
