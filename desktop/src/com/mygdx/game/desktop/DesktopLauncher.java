package com.mygdx.game.desktop;

import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// config.height = (int)
		// Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		// config.width =(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		config.height = 600;
		config.width = 800;

		config.resizable = false;
		config.fullscreen = false;

		new LwjglApplication(new Game(), config);

		// Windowed Borderless
		// Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

	}
}
