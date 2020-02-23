package com.mygdx.trees.skills;

import com.mygdx.managers.BattleManager;

public class Zeal extends Skill{
	
	private int activation;
	
	public Zeal(BattleManager combat) {
		super(combat);
		activationMessage = "ZEAL: EXTRA TURN!";
	}

	@Override
	public boolean activationCondition() {
		activation = (int) (Math.random()*100+1);
		if (activation <= 50) return true;
		return false;
	}

	@Override
	public void effect() {
		combat.units.addFirst(combat.getCurrentUnit());
	}

	@Override
	public void reset() {
		
	}

	

	

}
