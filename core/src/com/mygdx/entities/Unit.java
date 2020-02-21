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
import com.mygdx.classes.Attributes;
import com.mygdx.maps.TileMap;

public class Unit extends Entity{
	
	public Map<Integer, Integer> levelUpReq = new HashMap<Integer, Integer>() {{
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

	
	public Attributes attribute;

	private int hp;
	
	public MovementRange movement;
	
	public Unit(String name, int col, int row, Sprite sprite, Attributes attribute) {
		super(name, col, row, sprite);
		this.attribute = attribute;
	}
	
	public void createMovementRange(TileMap map) {movement = new MovementRange(map, this.row, this.col);}	
	
	public int rollInitiative() {
		return (int)(Math.random()*20) + attribute.initiative;
	}
	
	public void move(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}	
	
	

}
