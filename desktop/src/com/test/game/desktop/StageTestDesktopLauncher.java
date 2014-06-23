package com.test.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.game.stagetest.StageTest;

public class StageTestDesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Stage Test";
		config.width = 741;
        config.height = 446;
		new LwjglApplication(new StageTest(), config);
	}
}
