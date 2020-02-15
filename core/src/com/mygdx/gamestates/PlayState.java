package com.mygdx.gamestates;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.entities.Hero;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.maps.TileMap;

public class PlayState extends GameState{
	private static ShapeRenderer sr;
	private static Hero hero;
	private static Texture spritesheet;
	private static int spritedim = 16;
	private static SpriteBatch batch;
	private static TileMap tmap;
	public static ArrayList<Unit> units = new ArrayList<Unit>();
	
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
		
		tmap = new TileMap(mapint, 50);
		
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		sr.end();
		spritesheet = new Texture("placeholder/sheet.png");
		Sprite heroSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		hero = new Hero("Dbu", 1, 1, heroSprite);
		
		batch = new SpriteBatch();
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		tmap.draw();
		hero.draw(batch, tmap);
	}

	public void handleInput() {
		//player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		//player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		//player.setUp(GameKeys.isDown(GameKeys.UP));
		if (MouseButtons.isLeftPressed()) {
			int a = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
			int b = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
			if (b < tmap.mapLength && b >= 0 && a < tmap.mapWidth && a >= 0 ) {
				hero.setCol(a);
				hero.setRow(b);
			}
		}
	}

	public void dispose() {
		
	}
}
