package com.mygdx.trees.skills;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entities.Unit;

public class Indomitable extends Skill{

	private SpriteBatch batch = new SpriteBatch();
	private BitmapFont font = new BitmapFont(Gdx.files.internal("font/origa.fnt"), false);
	
	private boolean activated = false;
	
	public Indomitable(Unit unit) {
		super(unit);
	}
	
	@Override
	public void activationCondition() {
		if (unit.getHp() <= 0 && !activated) {
			System.out.println("Here");
			effect();
		}
	}

	@Override
	public void effect() {
		
		font.getData().setScale(0.5f, 0.5f);
		batch.begin();
		font.setColor(Color.WHITE);
		font.draw(batch, "INDOMITABLE ACTIVATE!!", unit.getRow()*unit.getUnitDim(), unit.getCol()*unit.getUnitDim());
		batch.end();
		
		unit.setHp(1);
//		activated = true;
	}

	@Override
	public void reset() {
		activated = false;
	}

}
