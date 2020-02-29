package com.mygdx.abilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.managers.BattleManager;

//Test ability for ranged classes

public class Fireball extends Ability{

	public Fireball() {
//		super(combat);
		icon = new Sprite(new TextureRegion(spritesheet, spritedim*15+15, spritedim*10+10, spritedim, spritedim));
	}

	@Override
	public void effect() {
		// TODO Auto-generated method stub
		
	}

}
