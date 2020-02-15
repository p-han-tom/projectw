package com.mygdx.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MouseButtons {
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
