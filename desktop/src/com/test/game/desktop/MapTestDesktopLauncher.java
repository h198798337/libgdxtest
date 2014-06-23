package com.test.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.game.maptest.MapTest;
import com.test.game.maptest.TiledMapAtlasAssetManagerTest;

public class MapTestDesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "map test";
		config.width = 960;
		config.height = 640;
		new LwjglApplication(new MapTest(), config);
//		new LwjglApplication(new TiledMapAtlasAssetManagerTest(), config);
	}
}
