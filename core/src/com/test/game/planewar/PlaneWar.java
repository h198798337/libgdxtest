package com.test.game.planewar;

import com.badlogic.gdx.Game;

public class PlaneWar extends Game{
	GameScreen gameScreen;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

}
