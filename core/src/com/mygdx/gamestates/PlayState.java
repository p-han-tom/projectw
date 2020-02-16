package com.mygdx.gamestates;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.mygdx.ui.TextBox;

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
	private static BitmapFont font = new BitmapFont();
	
	public PlayState (GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		// Define the map. This should be replaced eventually with some dungeon generation algorithim or something
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
		
		//These sprites are placeholders until we code all the basics and decide to draw them i guess
		spritesheet = new Texture("placeholder/sheet.png");
		System.out.println(tmap.offsetX + " " + tmap.offsetY);
		Sprite heroDbuSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		heroDbu = new Hero("Dbu", 1, 1, tmap.tileDim, heroDbuSprite);
		units.add(heroDbu);
		Sprite heroMeeSprite = new Sprite(new TextureRegion(spritesheet, 26*spritedim+26, 2*spritedim+2, spritedim, spritedim));
		heroMee = new Hero("Mee", 3, 2, tmap.tileDim, heroMeeSprite);
		units.add(heroMee);
		
		// When the level starts, have each unit roll for initiative
		units = TurnManager.newTurnOrder(units);
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		tmap.draw();
		for (Unit unit : units) unit.draw(batch, tmap);
		
		// this below is very basic ui but once its fleshed out more it will belong in a ui class. It displays whose turn it is
		batch.begin();
		font.setColor(Color.WHITE);
		font.draw(batch, "It is currently "+units.get(unitTracker).getName()+"'s turn", 10, Game.HEIGHT-10);
		batch.end();
	}

	public void handleInput() {
		// This math stuff finds the row and column that the cursor is on
		int mouseCol = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
		int mouseRow = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
		
		// This loops through the units to see if it is being hovered over. Probably belongs somewhere else
		for (Unit unit:units) {
			if (unit.getCol()==mouseCol && unit.getRow()==mouseRow) {
				String text = unit.getName();
				TextBox.draw(batch, font, sr, MouseButtons.getX()+20, Game.HEIGHT-MouseButtons.getY(), text, Color.WHITE, Color.BLACK);
			}
		}
		
		// Moves the unit to the tile if it is clicked. Probably belongs somewhere else
		if (MouseButtons.isLeftPressed()) {
			if (mouseRow < tmap.mapLength && mouseRow >= 0 && mouseCol < tmap.mapWidth && mouseCol >= 0 ) {
				if (!tmap.getTile(mouseRow, mouseCol).passable) return;
				units.get(unitTracker).move(mouseRow, mouseCol);
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
