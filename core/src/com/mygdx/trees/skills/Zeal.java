package com.mygdx.trees.skills;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.entities.Unit;

public class Zeal extends Skill{
	
	private int activation;
	
	public Zeal(Unit unit) {
		super(unit);
		activationMessage = "ZEAL: EXTRA TURN!";
		special = true;
		specialName = "ZEAL";
	}

	@Override
	public boolean activationCondition() {
		activation = (int) (Math.random()*100+1);
		if (activation <= 10) return true;
		return false;
	}

	@Override
	public void effect() {
	}

	@Override
	public void reset() {
		
	}

	

	

}
