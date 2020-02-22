package com.mygdx.trees.skills;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.Unit;
import com.mygdx.managers.BattleManager;

public class Indomitable extends Skill{

	private boolean activated = false;
		
	public Indomitable(BattleManager combat) {
		super(combat);
		activationMessage = "INDOMITABLE ACTIVATED";
		special = false;
	}
	
	@Override
	public boolean activationCondition() {
		if (combat.getCurrentUnit().getHp() <= 0 && !activated) return true;
		return false;
	}
	
	@Override
	public void effect() {
		combat.getCurrentUnit().setHp(1);
		activated = true;
	}

	@Override
	public void reset() {
		activated = false;
	}



}
