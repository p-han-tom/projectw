package com.mygdx.trees.skills;

import com.mygdx.managers.BattleManager;

public class Zeal extends Skill{
	
	private int activation;
	
	public Zeal() {
		activationMessage = "ZEAL: EXTRA TURN!";
		beforeTurn = false;
	}

	@Override
	public boolean activationCondition() {
		activation = (int) (Math.random()*100+1);
		if (activation <= 15) return true;
		return false;
	}

	@Override
	public void effect() {
		combat.units.addFirst(combat.cUnit);
	}

	@Override
	public void reset() {
		
	}

	

	

}
