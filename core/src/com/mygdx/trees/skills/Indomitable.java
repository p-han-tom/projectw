package com.mygdx.trees.skills;

import com.mygdx.managers.BattleManager;

public class Indomitable extends Skill{

	private boolean activated = false;
		
	public Indomitable() {
		activationMessage = "LIVE ANOTHER DAY!";
		beforeTurn = true;
	}
	
	@Override
	public boolean activationCondition() {
		if (combat.cUnit.getHp() <= 0 && !activated) return true;
		return false;
	}
	
	@Override
	public void effect() {
		combat.cUnit.setHp(1);
		activated = true;
	}

	@Override
	public void reset() {
		activated = false;
	}



}
