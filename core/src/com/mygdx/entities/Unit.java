package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Unit extends Entity{

	public Unit(String name, int col, int row, Sprite sprite) {
		super(name, col, row, sprite);
	}

}
