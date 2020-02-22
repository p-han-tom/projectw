package com.mygdx.trees.skills;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.entities.Unit;
import com.mygdx.managers.BattleManager;

public class Zeal extends Skill{
	
	private int activation;
	
	public Zeal(BattleManager combat) {
		super(combat);
		activationMessage = "ZEAL: EXTRA TURN!";
		special = true;
		specialName = "ZEAL";
	}

	@Override
	public boolean activationCondition() {
		System.out.println(combat.getCurrentUnit());
		activation = (int) (Math.random()*100+1);
		if (activation <= 10) return true;
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
