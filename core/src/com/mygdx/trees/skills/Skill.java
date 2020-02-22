package com.mygdx.trees.skills;

import java.util.List;

import com.mygdx.entities.Unit;

public abstract class Skill {
	protected Unit unit;
	protected String activationMessage;
	
	public Skill(Unit unit) {
		this.unit = unit;
	}
	
	public abstract boolean activationCondition();
	public abstract void effect();
	public abstract void reset();
	
	public String getActivation() {
		return activationMessage;
	}

	
}
