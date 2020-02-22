package com.mygdx.trees.skills;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.Unit;

public class Indomitable extends Skill{

	private SpriteBatch batch = new SpriteBatch();
	private BitmapFont font = new BitmapFont(Gdx.files.internal("font/origa.fnt"), false);
	private boolean activated = false;
	
	public Indomitable(Unit unit) {
		super(unit);
	}
	
	@Override
	public boolean activationCondition() {
		if (unit.getHp() <= 0 && !activated) return true;
		return false;
	}
	
	public void effect() {
		unit.setHp(1);
		activated = true;
	}



}
