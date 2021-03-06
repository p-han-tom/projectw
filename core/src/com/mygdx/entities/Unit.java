package com.mygdx.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.abilities.Ability;
import com.mygdx.controller.Controller;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;
import com.mygdx.scenes.HUD;
import com.mygdx.trees.skills.Indomitable;
import com.mygdx.trees.skills.Skill;

public class Unit extends Entity{
	
	public String toString() {
		return this.name;
	}
	
	private Map<Integer, Integer> levelUpReq = new HashMap<Integer, Integer>() {{
		put(1, 20);
		put(2, 40);
		put(3, 80);
		put(4, 120);
		put(5, 180);
		put(6, 240);
		put(7, 320);
		put(8, 400);
		put(9, 500);
		put(10, 600);
	}};

	
	public List<Skill> skills = new ArrayList<Skill>();
	public List<Ability> abilities = new ArrayList<Ability>();
	
	public Attributes attribute;
	private int level;
	private int xp;
	private int hp;
	
	public UnitRangeFinder rangeFinder;
	private int unitDim, uOffX, uOffY;
	
	private Controller controller;
	
	public Unit(String name, int col, int row, Controller controller, Sprite sprite, Attributes attribute) {
		super(name, col, row, sprite);
		this.setController(controller);
		this.attribute = attribute;
		hp = attribute.maxHP;
	}
	
	
	
	public void createMovementRange(TileMap map) {
		rangeFinder = new UnitRangeFinder(map, this.row, this.col);
		setUnitDim(map.tileDim);
		setuOffX(map.offsetX);
		setuOffY(map.offsetY);
	}	
	
	public int rollInitiative() {
		return (int)(Math.random()*20) + attribute.initiative;
	}
	
	public void move(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}
	
	public int getHp() {return hp;}
	public void setHp(int hp) {this.hp = hp;}
	public void damage(int dmg) {this.hp-=dmg;}
	public int getUnitDim() {return unitDim;}
	public void setUnitDim(int unitDim) {this.unitDim = unitDim;}
	public int getuOffX() {return uOffX;}
	public void setuOffX(int uOffX) {this.uOffX = uOffX;}
	public int getuOffY() {return uOffY;}
	public void setuOffY(int uOffY) {this.uOffY = uOffY;}

	public Controller getController() {return controller;}
	public void setController(Controller controller) {this.controller = controller;}
}
