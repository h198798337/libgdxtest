package com.test.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.game.collision.CollisionTest;

public class CollisonTestDestopLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "CollisionTest";
		config.width = 480;
		config.height = 480;
		new LwjglApplication(new CollisionTest(), config);
	}

}
