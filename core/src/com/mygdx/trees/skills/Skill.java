package com.mygdx.trees.skills;

import java.util.List;

import com.mygdx.entities.Unit;
import com.mygdx.managers.BattleManager;

public abstract class Skill {
	protected BattleManager combat;
	protected String activationMessage;
	protected boolean special;
	protected String specialName;
	
	public Skill(BattleManager combat) {
		this.combat = combat;
	}
	
	public abstract boolean activationCondition();
	public abstract void effect();
	public abstract void reset();
	
	public String getSpecialName() {
		return specialName;
	}
	
	public String getActivation() {
		return activationMessage;
	}

	public boolean isSpecial() {
		return special;
	}
	
}
