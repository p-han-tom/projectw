package com.mygdx.gamestates;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.abilities.Fireball;
import com.mygdx.abilities.Slash;
import com.mygdx.controller.ControllerDownerUpper;
import com.mygdx.controller.ControllerPlayer;
import com.mygdx.entities.Attributes;
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
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;
import com.mygdx.trees.skills.Zeal;
import com.mygdx.scenes.HUD;
import com.mygdx.ui.TextBox;

public class PlayState extends GameState{
	private static ShapeRenderer sr;
	private static HUD hud;
	private static SpriteBatch batch = new SpriteBatch();
	
	private static Texture spritesheet;
	private static int spritedim = 16;
	private static TileMap tmap;

	private static List<Unit> units = new LinkedList<Unit>();
	private static List<Trap> traps = new ArrayList<Trap>();

	private static Unit heroDbu;
	private static Unit heroMee;
	private static Unit dummy;

	private static Trap trapMagic;

	private static BitmapFont font;
	private static BattleManager combat;
	private static UIManager uim;
	public Stage stage;
	private Viewport viewport;

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

		// 1 = tree (impassable)
		// 2 = mud (higher move cost)
		// 0 = empty
		
		tmap = new TileMap(mapint, Game.HEIGHT/10);
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		// shitty font
		font = new BitmapFont(Gdx.files.internal("font/origa.fnt"), false);
		font.getData().setScale(0.5f, 0.5f);

		//These sprites are placeholders until we code all the basics and decide to draw them i guess
		spritesheet = new Texture("placeholder/sheet.png");
		Sprite heroDbuSprite = new Sprite(new TextureRegion(spritesheet, 25*spritedim+25, 2*spritedim+2, spritedim, spritedim));
		
		heroDbu = new Unit("Dbu", 1, 1, new ControllerPlayer(), heroDbuSprite, new Attributes(10, 10, 15, 10)) {{
			createMovementRange(tmap);
			abilities.add(new Fireball());
//			abilities.add(new Slash());
			skills.add(new Indomitable());
			skills.add(new Zeal());
		}};;
		units.add(heroDbu);
		
		Sprite heroMeeSprite = new Sprite(new TextureRegion(spritesheet, 26*spritedim+26, 2*spritedim+2, spritedim, spritedim));
		heroMee = new Unit("Mee", 3, 2, new ControllerPlayer(), heroMeeSprite, new Attributes(10, 10, 10, 10)) {{
			createMovementRange(tmap);
			abilities.add(new Slash());
			abilities.add(new Fireball());
			
			skills.add(new Indomitable());
			skills.add(new Zeal());
		}};
		units.add(heroMee);
		
		Sprite dummySprite = new Sprite(new TextureRegion(spritesheet, 28*spritedim+28, 2*spritedim+2, spritedim, spritedim));
		dummy = new Unit("Dummy", 4, 5, new ControllerDownerUpper(), dummySprite, new Attributes(10, 10, 10, 10)) {{
			createMovementRange(tmap);
			abilities.add(new Fireball());
			abilities.add(new Slash());
			skills.add(new Indomitable());
			skills.add(new Zeal());
		}};
		units.add(dummy);
		
		Sprite trapMagicSprite = new Sprite(new TextureRegion(spritesheet, 31*spritedim+31, 11*spritedim+11, spritedim, spritedim));
		trapMagic = new Trap("Magic trap", 3, 3, trapMagicSprite) ;
		traps.add(trapMagic);

		// When the level starts, have each unit roll for initiative
		combat = new BattleManager(tmap, units, traps);
		uim = new UIManager(combat, tmap);
		
		viewport = new FitViewport(Game.WIDTH, Game.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);
		hud = new HUD(stage, batch, sr, font, combat);
	}

	public void update(float dt) {
		if (!combat.abilityIsDrawing) {
			hud.update(combat);
			handleInput();
		}
	}

	public void draw() {
		
		hud.draw(stage, batch, sr, font);
		combat.draw();
		UIManager.draw(batch, font, sr);
		
		
	}

	public void handleInput() {
		// This math stuff finds the row and column that the cursor is on
		int mouseCol = (MouseButtons.getX()-tmap.offsetX)/tmap.tileDim;
		int mouseRow = (Game.HEIGHT-(MouseButtons.getY()+tmap.offsetY))/tmap.tileDim;
		
		if (!uim.isStatBoxOpen()) combat.handleTurn(mouseRow, mouseCol, hud);
		uim.handleInput(mouseCol, mouseRow);
	}

	public void dispose() {

	}
}
