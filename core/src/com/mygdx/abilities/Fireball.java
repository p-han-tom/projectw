package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.managers.BattleManager;

//Test ability for ranged classes

public class Fireball extends Ability{

	public Fireball() {
		abilityRange = 2;
		range = new AbilityRange(abilityRange, new Color(0,1,1,0.3f));
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*15+15, spritedim*10+10, spritedim, spritedim));
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

}
