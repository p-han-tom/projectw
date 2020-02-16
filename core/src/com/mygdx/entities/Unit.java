package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gamestates.PlayState;

public abstract class Unit extends Entity{

	public Unit(String name, int col, int row, int tileDim, Sprite sprite) {
		super(name, col, row, tileDim, sprite);
	}
	public static int rollInitiative() {
		return (int)(Math.random()*20)+1;
	}
	public abstract void move(int row, int col);
}
