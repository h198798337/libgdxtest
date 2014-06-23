package com.test.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.game.skintest.UISimpleTest;
import com.test.game.skintest.UITestForJSON;

public class UISimpleTestDesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "UISimpleTest";
		config.width = 640;
		config.height = 640;
//		new LwjglApplication(new UISimpleTest(), config);
		new LwjglApplication(new UITestForJSON(), config);
	}
}
