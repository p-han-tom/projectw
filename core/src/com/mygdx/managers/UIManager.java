package com.mygdx.managers;

import java.util.ArrayList;
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
	
	private static ArrayList<TextBox> statboxes;
	
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
			}
		}
		if (MouseButtons.isRightPressed()) {
			statboxes = new ArrayList<TextBox>();
			float totalHeight = 0;
			float maxWidth = 0;
			for (Unit unit:combat.nextUnits) {
				if (unit.getCol()==mouseCol && unit.getRow()==mouseRow) {
					String text = unit.getName();
					TextBox statboxUnit = new TextBox(MouseButtons.getX()+tmap.tileDim/2, Game.HEIGHT-MouseButtons.getY()+tmap.tileDim+(int)totalHeight, text, Color.WHITE, Color.BLACK);
					statboxes.add(statboxUnit);
					statboxOpen = true;
					totalHeight+=statboxUnit.getTotalHeight();
				}
			}
			for (Trap trap:combat.traps) {
				if (trap.getCol()==mouseCol && trap.getRow()==mouseRow) {
					String text = trap.getName();
					TextBox statboxTrap = new TextBox(MouseButtons.getX()+tmap.tileDim/2, Game.HEIGHT-MouseButtons.getY()+tmap.tileDim+(int)totalHeight, text, Color.WHITE, Color.BLACK);
					statboxes.add(statboxTrap);
					statboxOpen = true;
					totalHeight+=statboxTrap.getTotalHeight();
				}
			}
		}
	}
	public static void draw(SpriteBatch batch, BitmapFont font, ShapeRenderer sr) {
		if (statboxOpen==true) {
			for (TextBox statbox:statboxes) {
				statbox.draw(batch, font, sr);
			}
		}
	}
	public static boolean isStatBoxOpen() {
		return statboxOpen;
	}
}
