package com.mygdx.controller;

import com.mygdx.managers.BattleManager;
import com.mygdx.scenes.HUD;

public class ControlDummy extends Controller {
	public void control(BattleManager combat, HUD hud) {
		combat.getNextTurn();
	}
}
