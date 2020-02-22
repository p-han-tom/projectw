package com.mygdx.trees.skills;

import java.util.List;

import com.mygdx.entities.Unit;

public class Zeal extends Skill{

	
	private int activation;
	private int current;
	
	public Zeal(Unit unit, int current) {
		super(unit);
		this.current = current;
		activationMessage = "ZEAL: EXTRA TURN!";
	}

	@Override
	public boolean activationCondition() {
		activation = (int) (Math.random()*100+1);
		if (activation <= 100) return true;
		return false;
	}

	@Override
	public void effect() {
		
	}

	@Override
	public void reset() {
		
	}

	

	

}
