package com.mygdx.trees.skills;

import com.mygdx.entities.Unit;

public abstract class Skill {
	protected Unit unit;
	public Skill(Unit unit) {
		this.unit = unit;
	}
	
	public abstract void activationCondition();
	public abstract void effect();
	public abstract void reset();
}
