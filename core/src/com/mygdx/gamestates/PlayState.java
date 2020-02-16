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
	private static Unit heroDbu;
	private static Unit heroMee;
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
		int[][] mapint = {{1,1,1,1,1,1,1,1},
						{1,0,1,0,0,0,0,1},
					   	{1,0,1,0,0,0,0,1},
					   	{1,0,1,0,0,0,0,1},
					   	{1,0,0,0,0,1,0,1},
					   	{1,0,0,0,0,1,0,1},
					   	{1,0,0,0,0,1,0,1},
					  	{1,1,1,1,1,1,1,1}};
		
		tmap = new TileMap(mapint, 60);
		
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		sr.end();
		spritesheet = new Texture("placeholder/sheet.png");
		System.out.println(tmap.offsetX + " " + tmap.offsetY);
		
		Sprite heroDbuSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		heroDbu = new Unit("Dbu", 1, 1, tmap.tileDim, heroDbuSprite, true);
		units.add(heroDbu);
		
		Sprite heroMeeSprite = new Sprite(new TextureRegion(spritesheet, 26*spritedim+26, 2*spritedim+2, spritedim, spritedim));
		heroMee = new Unit("Mee", 3, 2, tmap.tileDim, heroMeeSprite, true);
		units.add(heroMee);
		
		units = TurnManager.newTurnOrder(units);
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		tmap.draw();
		for (Unit unit : units) unit.draw(batch, tmap);
		
		// this below is ui prototype but it belongs in a ui manager or class idk
		batch.begin();
		font.setColor(Color.WHITE);
		font.draw(batch, "It is currently "+units.get(unitTracker).getName()+"'s turn", 10, Game.HEIGHT-10);
		batch.end();
	}

	public void handleInput() {
		int mouseCol = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
		int mouseRow = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
		for (Unit unit:units) {
			if (unit.getCol()==mouseCol && unit.getRow()==mouseRow) {
				String text = unit.getName();
				TextBox.draw(batch, font, sr, MouseButtons.getX()+20, Game.HEIGHT-MouseButtons.getY(), text, Color.WHITE, Color.BLACK);
			}
		}
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
