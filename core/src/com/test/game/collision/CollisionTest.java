package com.test.game.collision;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CollisionTest extends ApplicationAdapter {

	private CollisonStage collisonStage;
	
	@Override
	public void create() {
		Viewport viewport = new ScreenViewport();
		viewport.setWorldWidth(Gdx.graphics.getWidth());
		viewport.setWorldHeight(Gdx.graphics.getHeight());
		collisonStage = new CollisonStage(viewport);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(collisonStage.mario);
		multiplexer.addProcessor(collisonStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		collisonStage.act();
		collisonStage.draw();
	}

}
