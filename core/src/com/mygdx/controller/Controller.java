package com.mygdx.controller;

import com.mygdx.managers.BattleManager;
import com.mygdx.scenes.HUD;

public abstract class Controller {
	public Controller() {
		
	}
	public abstract void control(BattleManager combat, HUD hud);
}
