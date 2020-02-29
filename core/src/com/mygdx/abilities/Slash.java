package com.mygdx.abilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.abilities.range.Range;
import com.mygdx.managers.BattleManager;

//Test ability for melee classes 

public class Slash extends Ability {

	public Slash(Range range) {
		super(range);
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*0,spritedim*31+31,spritedim,spritedim));
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

}
