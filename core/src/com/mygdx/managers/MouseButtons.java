package com.mygdx.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MouseButtons {
	
	private static ClickListener cl = new ClickListener();
	private static boolean[] clicks;
	private static boolean[] pclicks;
	
	private static final int NUM_CLICKS = 2;
	public static final int LEFT_CLICK = 0;
	public static final int RIGHT_CLICK = 1;
	
	static {
		clicks = new boolean[NUM_CLICKS];
		pclicks = new boolean[NUM_CLICKS];
	}
	
	public static void update() {
		for (int i = 0; i < NUM_CLICKS; i ++) {
			pclicks[i] = clicks[i];
		}
	}
	
	public static boolean isLeftPressed() {
		clicks[LEFT_CLICK] = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		return clicks[LEFT_CLICK] && !pclicks[LEFT_CLICK];
	}
	
	public static boolean isLeftDown() {
		return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}
	public static int getX() {
		return Gdx.input.getX();
	}
	public static int getY() {
		return Gdx.input.getY();
	}
}
