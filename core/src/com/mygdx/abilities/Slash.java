package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.abilities.range.AbilityRange;

import com.mygdx.managers.BattleManager;

//Test ability for melee classes 

public class Slash extends Ability {

	public Slash() {
		abilityRange = 1;
		range = new AbilityRange(abilityRange, new Color(255,0,0,0.3f));
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*0,spritedim*31+31,spritedim,spritedim));
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

}
