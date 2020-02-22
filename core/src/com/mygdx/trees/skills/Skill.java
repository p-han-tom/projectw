package com.mygdx.trees.skills;

import java.util.List;

import com.mygdx.entities.Unit;

public abstract class Skill {
	protected Unit unit;
	protected String activationMessage;
	protected boolean special;
	protected String specialName;
	
	public Skill(Unit unit) {
		this.unit = unit;
	}
	
	public abstract boolean activationCondition();
	public abstract void effect();
	public abstract void reset();
	
	public String getSpecialName() {
		return specialName;
	}
	
	public String getActivation() {
		return activationMessage;
	}

	public boolean isSpecial() {
		return special;
	}
	
}
