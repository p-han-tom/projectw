package com.mygdx.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Hero extends Unit{
	public Hero(String name, int col, int row, int tileDim, Sprite sprite) {
		super(name, col, row, tileDim, sprite);
	}
	public void move(int row, int col) {
		this.setCol(col);
		this.setRow(row);
	}
}
