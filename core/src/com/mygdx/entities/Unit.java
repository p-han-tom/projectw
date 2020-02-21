package com.mygdx.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.maps.TileMap;

public class Unit extends Entity{
	
	private int strength;
	private int arcana;
	private int constitution;
	private int finesse;
	
	private int moves;
	public MovementRange movement;
	
	public Unit(String name, int col, int row, Sprite sprite, int moves) {
		super(name, col, row, sprite);
		this.moves = moves;
	}
	
	public void createMovementRange(TileMap map) {
		movement = new MovementRange(map, this.row, this.col);
	}
	
	public static int rollInitiative() {
		return (int)(Math.random()*20)+1;
	}
	
	public void move(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}
		
	//Movement range methods
	
	
	//getters and setters
	public int getMovement() {
		return moves;
	}
	

}
