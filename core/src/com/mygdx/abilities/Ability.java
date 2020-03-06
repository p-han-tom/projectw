package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public abstract class Ability {
	public String id;
	
	protected Texture spritesheet = new Texture("placeholder/sheet.png");
	protected int spritedim = 16;
	protected Sprite icon;	
	
	public AbilityRange range;
	protected int abilityRange;

	public Ability() {
		
	}
	
	public abstract void effect(int row, int col, BattleManager combat);
	public Sprite getIcon() {return icon;}
	public int getAbilityRange() {return abilityRange;}
}
