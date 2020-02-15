package com.mygdx.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	private static Texture spritesheet;
	private static int spritedim = 16;
	private static SpriteBatch batch;
	
	public PlayState (GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		int[][] mapint = {{1,1,1,1,1,1,1,1},
						{1,0,1,0,0,0,0,1},
					   	{1,0,1,0,0,0,0,1},
					   	{1,0,1,0,0,0,0,1},
					   	{1,0,0,0,0,1,0,1},
					   	{1,0,0,0,0,1,0,1},
					   	{1,0,0,0,0,1,0,1},
					  	{1,1,1,1,1,1,1,1}};
		map = new GridMap(mapint);
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		sr.end();
		spritesheet = new Texture("placeholder/sheet.png");
		Sprite playerSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		player = new Player(1,1,Color.RED, playerSprite);
		
		batch = new SpriteBatch();
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		map.draw(sr);
		player.draw(batch, map);
//		batch.begin();
//		Player.getSprite().draw(batch);
//		batch.end();
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
