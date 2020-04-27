package com.mygdx.controller;

import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;
import com.mygdx.scenes.HUD;

public class ControllerDownerUpper extends Controller {
	private boolean down = true;
	@Override
	public void act(BattleManager combat, int mRow, int mCol, HUD hud) {
		TileMap map = combat.map;
		int row = combat.cUnit.getRow();
		int col = combat.cUnit.getCol();

		if (down == true) {
			if (!map.getTile(row-1, col).isOccupied && map.getTile(row-1, col).passable) combat.cUnit.move(row-1, col);
			else down = false;
		}
		else {
			if (!map.getTile(row+1, col).isOccupied && map.getTile(row+1, col).passable) combat.cUnit.move(row+1, col);
			else down = true;
		}
		hud.endTurnPressed = true;
		hud.update(combat);
	}
}
