package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Unit extends Entity{
	private boolean goodGuy;
	public Unit(String name, int col, int row, int tileDim, Sprite sprite, boolean goodGuy) {
		super(name, col, row, tileDim, sprite);
		this.goodGuy = goodGuy;
	}
	public static int rollInitiative() {
		return (int)(Math.random()*20)+1;
	}
	public void move(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}

}
