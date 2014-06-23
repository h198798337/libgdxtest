package com.test.game.actortest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ActorTest extends ApplicationAdapter{
	
	Stage stage;
	
	@Override
	public void create() {
		Image image = new Image(new Texture("mario_map.jpg"));
		image.setPosition(0, 170);
		
		Viewport viewport = new ScreenViewport();
		viewport.setWorldWidth(480);
		viewport.setWorldHeight(320);
		stage = new Stage(viewport);
		Mario mario = new Mario(100, 190);
		
		stage.addActor(image);
		stage.addActor(mario);
		
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(mario);
		multiplexer.addProcessor(stage);
		
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}
	
}
