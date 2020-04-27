package com.mygdx.controller;

import com.mygdx.managers.BattleManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.maps.TileMap;
import com.mygdx.scenes.HUD;

public class ControllerPlayer extends Controller {

	@Override
	public void act(BattleManager combat, int mRow, int mCol, HUD hud) {
		//Checks if an ability has not been selected and if the current unit can move. If so, the unit moves to the selected space.
		TileMap map = combat.map;
				if (!combat.abilitySelected && combat.canMove) {
					if (MouseButtons.isLeftPressed()) {
						if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0 && map.getTile(mRow, mCol).passable) {
							if (!combat.cUnit.rangeFinder.inRange(mRow, mCol)) return;

							combat.cUnit.move(mRow, mCol);
							combat.canMove = false;
						}
					}
				}
				
				//checks if an ability has been selected and the current unit can use an ability. If so, the unit casts an ability.
				else if (combat.abilitySelected && combat.canCast){
					if (MouseButtons.isLeftPressed()) {
						if (mRow < map.length && mRow >= 0 && mCol < map.width && mCol >= 0) {
							if (!hud.getCurrentAbility().range.inRange(mRow, mCol)) return;
							
							hud.getCurrentAbility().effect(mRow, mCol, combat);
							
							combat.animatedAbility = hud.getCurrentAbility();
							combat.animatedAbility.drawLocation(combat.cUnit.getCol()*map.tileDim+map.offsetX, combat.cUnit.getRow()*map.tileDim+map.offsetY,
									mCol*map.tileDim+map.offsetX, mRow*map.tileDim+map.offsetY);
							
							combat.abilityIsDrawing = true;

							hud.clearRange();
							combat.canCast = false;
							hud.abilityUsed = true;
						}
					}
				}
	}
	
}
