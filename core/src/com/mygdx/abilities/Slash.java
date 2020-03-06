package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.entities.Unit;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

//Test ability for melee classes 

public class Slash extends Ability {

	public Slash() {
		abilityRange = 1;
		range = new AbilityRange(abilityRange, new Color(255,0,0,0.3f));
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*0,spritedim*31+31,spritedim,spritedim));
	}

	@Override
	public void effect(int row, int col, BattleManager combat) {
		for (Unit unit:combat.nextUnits) {
			if (unit.getRow() == row && unit.getCol() == col) {
				unit.damage(5);
			}
		}
	}

}
