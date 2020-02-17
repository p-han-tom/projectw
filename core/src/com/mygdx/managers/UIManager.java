package com.mygdx.managers;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.entities.Unit;
import com.mygdx.game.Game;
import com.mygdx.maps.TileMap;
import com.mygdx.ui.TextBox;

public class UIManager {
	private static boolean statboxOpen = false;
	private static TextBox statbox;
	private BattleManager combat;
	private TileMap tmap;
	public UIManager(BattleManager combat, TileMap tmap) {
		this.combat = combat;
		this.tmap = tmap;
	}
	public void handleInput(int mouseCol, int mouseRow) {
		if (statboxOpen == true) {
			if (MouseButtons.isLeftPressed() || MouseButtons.isRightPressed()) {
				statbox = null;
				statboxOpen = false;
			}
		}
		if (MouseButtons.isRightPressed()) {
			for (Unit unit:combat.units) {
				if (unit.getCol()==mouseCol && unit.getRow()==mouseRow) {
					String text = unit.getName();
					statbox = new TextBox(MouseButtons.getX()+tmap.tileDim/2, Game.HEIGHT-MouseButtons.getY()+tmap.tileDim, text, Color.WHITE, Color.BLACK);
					statboxOpen = true;
				}
			}
		}
	}
	public static void draw(SpriteBatch batch, BitmapFont font, ShapeRenderer sr) {
		if (statbox!=null) {
			statbox.draw(batch, font, sr);
		}
	}
	public static boolean isStatBoxOpen() {
		return statboxOpen;
	}
}
