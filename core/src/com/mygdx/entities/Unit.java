package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gamestates.PlayState;

public abstract class Unit extends Entity{

	public Unit(String name, int col, int row, Sprite sprite) {
		super(name, col, row, sprite);
	}
	public static int rollInitiative() {
		return (int)(Math.random()*20)+1;
	}
	
}
