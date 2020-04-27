package com.mygdx.controller;

import com.mygdx.managers.BattleManager;
import com.mygdx.scenes.HUD;

public abstract class Controller {
	public abstract void act(BattleManager combat, int mRow, int mCol, HUD hud);
}
