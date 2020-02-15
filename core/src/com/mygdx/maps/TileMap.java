package com.mygdx.maps;

import com.mygdx.tiles.*;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;

public class TileMap {
	private Tile[][] map;
	private SpriteBatch drawer = new SpriteBatch();
	public static int tileDim;
	public static int offsetX;
	public static int offsetY;
	
	public int mapLength, mapWidth;


	public TileMap(int[][] map, int tileDim) {
		
		this.map = new Tile[map.length][map[0].length];
		this.tileDim = tileDim;
		
		mapLength = map.length;
		mapWidth = map[0].length;
		
		offsetX = (Game.WIDTH-(map.length*tileDim))/2;
		offsetY = (Game.HEIGHT-(map.length*tileDim))/2;
		
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
				this.map[row][col] = new GrassTile(row, col); //work in progress, id system needs to be implemented
			}
		}
	}
	
	public void draw() {
		for (int row = 0; row < map.length; row ++) {
			for (int col = 0; col < map[0].length; col ++) {
				map[row][col].render(drawer);
			}
		}
	}
	

	
	
}
