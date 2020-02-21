package com.mygdx.managers;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.entities.Trap;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;
import com.mygdx.ui.TextBox;

public class UIManager {
	private static boolean statboxOpen = false;
	private static TextBox statboxUnit;
	private static TextBox statboxTrap;
	private BattleManager combat;
	private TileMap tmap;
	public UIManager(BattleManager combat, TileMap tmap) {
		this.combat = combat;
		this.tmap = tmap;
	}
	public void handleInput(int mouseCol, int mouseRow) {
		if (statboxOpen == true) {
			if (MouseButtons.isLeftPressed() || MouseButtons.isRightPressed()) {
				statboxOpen = false;
				statboxUnit = null;
				statboxTrap = null;
			}
		}
		if (MouseButtons.isRightPressed()) {
			float totalHeight = 0;
			for (Unit unit:combat.units) {
				if (unit.getCol()==mouseCol && unit.getRow()==mouseRow) {
					String text = unit.getName();
					statboxUnit = new TextBox(MouseButtons.getX()+tmap.tileDim/2, Game.HEIGHT-MouseButtons.getY()+tmap.tileDim-(int)totalHeight, text, Color.WHITE, Color.BLACK);
					statboxOpen = true;
					totalHeight+=statboxUnit.getTextBoxHeight();
				}
			}
			for (Trap trap:combat.traps) {
				if (trap.getCol()==mouseCol && trap.getRow()==mouseRow) {
					String text = trap.getName();
					statboxTrap = new TextBox(MouseButtons.getX()+tmap.tileDim/2, Game.HEIGHT-MouseButtons.getY()+tmap.tileDim-(int)totalHeight, text, Color.WHITE, Color.BLACK);
					statboxOpen = true;
					totalHeight+=statboxTrap.getTextBoxHeight();
				}
			}
		}
	}
	public static void draw(SpriteBatch batch, BitmapFont font, ShapeRenderer sr) {
		if (statboxOpen!=false) {
			if (statboxUnit!=null) statboxUnit.draw(batch, font, sr);
			if (statboxTrap!=null) statboxTrap.draw(batch, font, sr);
		}
	}
	public static boolean isStatBoxOpen() {
		return statboxOpen;
	}
}
