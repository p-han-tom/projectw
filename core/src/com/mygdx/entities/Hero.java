package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hero extends Unit{
	public Hero(String name, int col, int row, Sprite sprite) {
		super(name, col, row, sprite);
	}
	public static void move(int row, int col) {
		// move handler goes here (currently in playstate.java)
	}
}
