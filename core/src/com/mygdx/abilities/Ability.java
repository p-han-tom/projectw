package com.mygdx.abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.abilities.range.AbilityRange;
import com.mygdx.managers.BattleManager;

public abstract class Ability {
	protected static SpriteBatch batcher = new SpriteBatch();
	
	// variables for moving objects
	protected float x, y, fx, fy, rise, run;
	protected boolean aboveInitialPos;
	protected boolean sameInitialRow;
	protected boolean onInitialLeft;
	
	public boolean finishedDrawing = false;
	
	protected Texture spritesheet = new Texture("placeholder/sheet.png");
	protected int spritedim = 16;
	protected Sprite icon;	
	
	public AbilityRange range;
	protected int abilityRange;

	public Ability() {
		// Individual abilities are set up with their own constructors
	}
	
	public abstract void effect(int row, int col, BattleManager combat);
	public abstract void draw();
	
	public Sprite getIcon() {return icon;}
	public int getAbilityRange() {return abilityRange;}

	// this function initializes the direction and speed of the casted ability
	// x,y refers to the position of the unit
	// fx,fy refers to the position of the mouse where the ability has been cast
	public void drawLocation(float x, float y, float fx, float fy) {
		this.x = x;
		this.y = y;
		this.fx = fx;
		this.fy = fy;
		
		aboveInitialPos = (fy > y) ? true : false;
		sameInitialRow = (fy == y) ? true : false;
		onInitialLeft = (fx < x) ? true : false;

		if (fx > x) {
			run = 1;
			rise = (fy-y)/(fx-x);
		} else if (fx < x) {
			run = -1;
			rise = -(fy-y)/(fx-x);
		} else if (fx == x){
			run = 0;
			rise = (fy > y) ? 1 : -1;
		}
	}
}
