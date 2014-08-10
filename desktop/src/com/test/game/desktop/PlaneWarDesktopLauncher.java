package com.test.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.game.planewar.PlaneWar;

public class PlaneWarDesktopLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 450;
		config.width = 325;
		new LwjglApplication(new PlaneWar(), config);
	}
}
