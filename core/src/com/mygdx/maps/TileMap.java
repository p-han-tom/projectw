package com.mygdx.maps;

import com.mygdx.tiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class TileMap {
	private Tile[][] map;
	private SpriteBatch batch = new SpriteBatch();
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
		
		//loops through each element in int array and creates a tile based off int key value
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {

				if (map[row][col] == 0) {
					this.map[row][col] = new GrassTile(tileDim, offsetX, offsetY);
				} else if (map[row][col] == 1) {
					this.map[row][col] = new TreeTile(tileDim, offsetX, offsetY);
				} else if (map[row][col] == 2) {
					this.map[row][col] = new MudTile(tileDim, offsetX, offsetY);
				}
				
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
