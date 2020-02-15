package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hero extends Entity{

	public Hero(int col, int row, Sprite sprite) {
		super(col, row, sprite);
	}
	public static void move(int row, int col) {
		// move handler goes here (currently in playstate.java)
	}
}
