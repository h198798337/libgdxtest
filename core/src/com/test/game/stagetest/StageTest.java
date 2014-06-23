package com.test.game.stagetest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StageTest extends ApplicationAdapter {
	
	StartStage startStage;
	GameStage gameStage;
	ShopStage shopStage;
	
	@Override
	public void create() {
		Viewport viewport = new ScreenViewport();
		viewport.setWorldWidth(741);
		viewport.setWorldHeight(446);
		startStage = new StartStage(viewport);
		gameStage = new GameStage(viewport);
		shopStage = new ShopStage(viewport);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Constants.StageFlag == Constants.StartStage){
			Gdx.input.setInputProcessor(startStage);
			startStage.act();
			startStage.draw();
		} else if(Constants.StageFlag == Constants.GameStage){
			Gdx.input.setInputProcessor(gameStage.multiplexer);
			gameStage.act();
			gameStage.draw();
		} else if(Constants.StageFlag == Constants.ShopStage){
			Gdx.input.setInputProcessor(shopStage);
			shopStage.act();
			shopStage.draw();
		}
	}
}
