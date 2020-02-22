package com.mygdx.gamestates;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.classes.Attributes;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.managers.GameKeys;
import com.mygdx.managers.GameStateManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.managers.TurnManager;
import com.mygdx.managers.UIManager;
import com.mygdx.maps.TileMap;
import com.mygdx.trees.skills.Skill;
import com.mygdx.ui.TextBox;

public class PlayState extends GameState{
	private static ShapeRenderer sr;

	private static Texture spritesheet;
	private static int spritedim = 16;
	private static SpriteBatch batch = new SpriteBatch();
	private static TileMap tmap;

	private static List<Unit> units = new LinkedList<Unit>();
	private static List<Trap> traps = new ArrayList<Trap>();

	private static Unit heroDbu;
	private static Unit heroMee;

	private static Trap trapMagic;

	private static BitmapFont font;
	private static BattleManager combat;
	private static UIManager uim;

	public PlayState (GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		// Define the map. This should be replaced eventually with some dungeon generation algorithim or something
		// THE MAP IS UPSIDE DOWN BUT EVERYTHING STILL WORKS
		int[][] mapint = {{1,1,1,1,1,1,1,1},
				{1,0,1,0,0,0,0,1},
				{1,0,1,0,0,0,0,1},
				{1,0,0,0,2,0,0,1},
				{1,0,0,2,0,0,0,1},
				{1,0,2,2,0,1,0,1},
				{1,0,2,2,0,1,0,1},
				{1,1,1,1,1,1,1,1}};

		tmap = new TileMap(mapint, Game.HEIGHT/10);

		sr = new ShapeRenderer();

		// shitty font
		font = new BitmapFont(Gdx.files.internal("font/origa.fnt"), false);
		font.getData().setScale(0.5f, 0.5f);

		//These sprites are placeholders until we code all the basics and decide to draw them i guess
		spritesheet = new Texture("placeholder/sheet.png");
		Sprite heroDbuSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		heroDbu = new Unit("Dbu", 1, 1, heroDbuSprite, new Attributes(10, 1.5, 10, 0.5, 10, 1, 10, 1)) {{
			createMovementRange(tmap);
		}};;
		units.add(heroDbu);
		Sprite heroMeeSprite = new Sprite(new TextureRegion(spritesheet, 26*spritedim+26, 2*spritedim+2, spritedim, spritedim));
		heroMee = new Unit("Mee", 3, 2, heroMeeSprite, new Attributes(10, 1.5, 10, 0.5, 10, 1, 10, 1)) {{
			createMovementRange(tmap);
		}};
		units.add(heroMee);
		Sprite trapMagicSprite = new Sprite(new TextureRegion(spritesheet, 31*spritedim+31, 11*spritedim+11, spritedim, spritedim));
		trapMagic = new Trap("Magic trap", 3, 3, trapMagicSprite) ;
		traps.add(trapMagic);

		// When the level starts, have each unit roll for initiative
		combat = new BattleManager(tmap, units, traps);
		uim = new UIManager(combat, tmap);
		
	}

	public void update(float dt) {
		handleInput();
	}

	public void draw() {
		combat.draw();

		// this below is very basic ui but once its fleshed out more it will belong in a ui class. It displays whose turn it is
		batch.begin();
		font.setColor(Color.WHITE);
		font.draw(batch, "It is currently "+combat.getCurrentUnit().getName()+"'s turn", 10, Game.HEIGHT-10);
		batch.end();

		UIManager.draw(batch, font, sr);
	}

	public void handleInput() {
		// This math stuff finds the row and column that the cursor is on
		int mouseCol = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
		int mouseRow = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
		
		if (!uim.isStatBoxOpen()) combat.handleTurn(mouseRow, mouseCol);
		uim.handleInput(mouseCol, mouseRow);
	}

	public void dispose() {

	}
}
