package com.mygdx.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.entities.Player;
import com.mygdx.game.Game;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.maps.GridMap;

public class PlayState extends GameState{
	private static GridMap map;
	private static ShapeRenderer sr;
	private static Player player;
	public PlayState (GameStateManager gsm) {
		super(gsm);
	}
	public void init() {
		int[][] mapint = {{1,1,1,1,1,1,1,1},
						{1,0,0,0,0,0,0,1},
					   	{1,0,0,0,0,0,0,1},
					   	{1,0,0,0,0,0,0,1},
					   	{1,0,0,0,0,0,0,1},
					   	{1,0,0,0,0,0,0,1},
					   	{1,0,0,0,0,0,0,1},
					  	{1,1,1,1,1,1,1,1}};
		map = new GridMap(mapint);
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		sr.end();
		player = new Player(2,2,Color.RED);
	}

	public void update(float dt) {
		handleInput();
		//player.update(dt);
	}

	public void draw() {
		map.draw(sr);
		player.draw(sr, map);
	}

	public void handleInput() {
		//player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		//player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		//player.setUp(GameKeys.isDown(GameKeys.UP));
		if (MouseButtons.isLeftPressed()) {
			int a = (MouseButtons.getX()-map.offsetX)/map.tileDim;
			int b = (Game.HEIGHT-(MouseButtons.getY()+map.offsetY))/map.tileDim;
			if (b < map.mapLength && b >= 0 && a < map.mapWidth && a >= 0 && map.getMap()[b][a] == 0) {
				player.setCol(a);
				player.setRow(b);
			}
		}
	}

	public void dispose() {
		
	}
}
