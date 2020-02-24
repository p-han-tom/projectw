package com.mygdx.abilities;

import com.mygdx.managers.BattleManager;

public abstract class Ability {
	public String id;
	
	private BattleManager combat;
	private int range;
	/*
	 * HOW ABILITIES WILL WORK
	 * 
	 * Ability HUD: A rendering of the ability list each unit has. Will be drawn every tick in BattleManager.
	 * 
	 * The Ability HUD object should also be in BattleManager. When an ability is clicked on, an event should be
	 * triggered that turns the cursor into an ability range selector for the current unit. If the user right clicks,
	 * the selection should be cancelled. 
	 * 
	 * After an ability from the Ability HUD is activated, a variable called abilityActivated should be set to true.
	 * Other abilities should not be able to be selected anymore. 
	 * 
	 * While abilityActivated is false, the BattleManager should not be allowed to go to the next turn unless the
	 * user presses an "End Turn" button. 
	 * 
	 */
	
	public Ability(BattleManager combat) {
		this.combat = combat;
	}
	
	public abstract void effect();
}
