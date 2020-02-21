package com.mygdx.trees.skills;

import com.mygdx.entities.Unit;

public class Indomitable extends Skill{

	private boolean activated = false;
	
	public Indomitable(Unit unit) {
		super(unit);
	}
	
	@Override
	public void activationCondition() {
		if (unit.getHp() <= 0) {
			effect();
		}
	}

	@Override
	public void effect() {
		unit.setCol(1);
		activated = true;
	}

	@Override
	public void reset() {
		activated = false;
	}

}
