package com.mygdx.trees.skills;

import java.util.List;

import com.mygdx.entities.Unit;

public abstract class Skill<T> {
	protected Unit unit;
	public Skill(Unit unit) {
		this.unit = unit;
	}
	
	public abstract boolean activationCondition();
	public abstract void effect();

	
}
