package com.mygdx.abilities;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.entities.Unit;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

//Test ability for ranged classes

public class Fireball extends Ability{

	boolean exit = false;
	
	public Fireball() {
		abilityRange = 4;
		range = new AbilityRange(abilityRange, new Color(0,1,1,0.3f));
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*15+15, spritedim*10+10, spritedim, spritedim));
	}

	@Override
	public void effect(int row, int col, BattleManager combat) {
		for (Unit unit:combat.nextUnits) {
			if (unit.getRow()==row && unit.getCol()==col) {
				unit.damage(8);
			}
			if (unit.getRow()==row-1 && unit.getCol()==col) {
				unit.damage(8);
			}
			if (unit.getRow()==row+1 && unit.getCol()==col) {
				unit.damage(8);
			}
			if (unit.getRow()==row && unit.getCol()==col-1) {
				unit.damage(8);
			}
			if (unit.getRow()==row && unit.getCol()==col+1) {
				unit.damage(8);
			}
		}
	}

}
