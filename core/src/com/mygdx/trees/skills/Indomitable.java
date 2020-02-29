package com.mygdx.trees.skills;

import com.mygdx.managers.BattleManager;

public class Indomitable extends Skill{

	private boolean activated = false;
		
	public Indomitable(BattleManager combat) {
		super(combat);
		activationMessage = "LIVE ANOTHER DAY!";
		beforeTurn = true;
	}
	
	@Override
	public boolean activationCondition() {
		if (combat.getCurrentUnit().getHp() <= 0 && !activated) return true;
		return false;
	}
	
	@Override
	public void effect() {
		combat.getCurrentUnit().setHp(1);
		activated = true;
	}

	@Override
	public void reset() {
		activated = false;
	}



}
