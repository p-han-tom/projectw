package com.mygdx.gamestates;

import java.util.ArrayList;
import java.util.List;

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
import com.mygdx.managers.TurnManager;
import com.mygdx.maps.TileMap;

public class PlayState extends GameState{
	private static ShapeRenderer sr;
	private static Hero heroDbu;
	private static Hero heroMee;
	private static Texture spritesheet;
	private static int spritedim = 16;
	private static SpriteBatch batch = new SpriteBatch();
	private static TileMap tmap;
	private static List<Unit> units = new ArrayList<Unit>();
	private static int unitTracker = 0;
	
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
		
		Sprite heroDbuSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		heroDbu = new Hero("Dbu", 1, 1, heroDbuSprite);
		units.add(heroDbu);
		
		Sprite heroMeeSprite = new Sprite(new TextureRegion(spritesheet, 26*spritedim+26, 2*spritedim+2, spritedim, spritedim));
		heroMee = new Hero("Mee", 2, 2, heroMeeSprite);
		units.add(heroMee);
		
		units = TurnManager.newTurnOrder(units);
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		tmap.draw();
		for (Unit unit : units) unit.draw(batch, tmap);
	}

	public void handleInput() {
		
		if (MouseButtons.isLeftPressed()) {
			int a = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
			int b = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
			if (b < tmap.mapLength && b >= 0 && a < tmap.mapWidth && a >= 0 ) {
				units.get(unitTracker).setCol(a);
				units.get(unitTracker).setRow(b);
				unitTracker++;
				if (unitTracker == units.size()) {
					unitTracker = 0;
					units = TurnManager.newTurnOrder(units);
				}
			}
		}
	}

	public void dispose() {
		
	}
}
