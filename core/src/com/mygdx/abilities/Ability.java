package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.managers.BattleManager;
import com.mygdx.maps.TileMap;

public abstract class Ability {
	public String id;
	protected static SpriteBatch batcher = new SpriteBatch();
	protected double timer = 0;
	protected float x, y, fx, fy, rise, run;
	protected boolean above;
	
	public boolean finishedDrawing = false;
	
	protected Texture spritesheet = new Texture("placeholder/sheet.png");
	protected int spritedim = 16;
	protected Sprite icon;	
	
	public AbilityRange range;
	protected int abilityRange;

	public Ability() {
		
	}
	
	public abstract void effect(int row, int col, BattleManager combat);
	public abstract void draw();
	public void drawLocation(float x, float y, float fx, float fy) {
		this.x = x;
		this.y = y;
		this.fx = fx;
		this.fy = fy;
		
		if (fy > y) above = true;
		
		if (fx > x) {
			run = 1;
			rise = (fy-y)/(fx-x);
			return;
		}
		else if (fx < x) {
			run = -1;
			rise = -(fy-y)/(fx-x);
			return;
		}
		else {
			run = 0;
			if (fy > y) rise = 1;
			else rise = -1;
			return;
		}
		
	}
	
	public Sprite getIcon() {return icon;}
	public int getAbilityRange() {return abilityRange;}
}
