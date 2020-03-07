package com.mygdx.controller;

import com.mygdx.game.Game;
import com.mygdx.managers.BattleManager;
import com.mygdx.managers.MouseButtons;
import com.mygdx.scenes.HUD;

public class ControlPlayer extends Controller{
	public void control(BattleManager combat, HUD hud) {
		int mCol = (MouseButtons.getX()-combat.map.offsetX)/combat.map.tileDim;
		int mRow = (Game.HEIGHT-(MouseButtons.getY()+combat.map.offsetY))/combat.map.tileDim;
		if (!combat.hudIsOpen && combat.canMove) {
			//If a left click is received, the unit is moving
			if (MouseButtons.isLeftPressed()) {

				if (mRow < combat.map.length && mRow >= 0 && mCol < combat.map.width && mCol >= 0 && combat.map.getTile(mRow, mCol).passable) {
					if (!combat.getCurrentUnit().rangeFinder.inRange(mRow, mCol)) return;

					combat.getCurrentUnit().move(mRow, mCol);
					combat.canMove = false;
				}
			}
		} else if (combat.hudIsOpen && combat.canCast){
			if (MouseButtons.isLeftPressed()) {
				//adding ability effect
				if (mRow < combat.map.length && mRow >= 0 && mCol < combat.map.width && mCol >= 0) {
					if (!hud.getCurrentAbility().range.inRange(mRow, mCol)) return;
					
					hud.getCurrentAbility().effect(mRow, mCol, combat);
					
					abilityToDraw = hud.getCurrentAbility();
					abilityToDraw.drawLocation(cUnit.getCol()*map.tileDim+map.offsetX, cUnit.getRow()*map.tileDim+map.offsetY,
							mCol*map.tileDim+map.offsetX, mRow*map.tileDim+map.offsetY);

					abilityIsDrawing = true;
					
					hud.dispose();
					combat.canCast = false;
					hud.abilityUsed = true;
				}
			}
		}
	}
}
