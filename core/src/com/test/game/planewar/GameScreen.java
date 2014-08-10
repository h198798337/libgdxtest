package com.test.game.planewar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage implements Screen {
	float groundOffsetY = 0;
	
	Texture texture;
	TextureRegion region1;
	TextureRegion region2;
	
	Plane plane;
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		act();
		draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		updateTheWorld();
		drawTheWorld();
		super.draw();
	}

	public void updateTheWorld() {
		getCamera().position.y += 3;
		if (getCamera().position.y - groundOffsetY > Gdx.graphics.getHeight() * 1.5) {
			groundOffsetY += Gdx.graphics.getHeight();
		}
		plane.offsetX = 0;
		plane.offsetY = getCamera().position.y - Gdx.graphics.getHeight()/2;
	}
	
	public void drawTheWorld() {
		getCamera().update();
		Batch batch = getBatch();
		batch.setProjectionMatrix(getCamera().combined);
		batch.begin();
		batch.draw(region1, 0, groundOffsetY);
		batch.draw(region2, 0, region1.getRegionHeight() + groundOffsetY);
		batch.end();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		((OrthographicCamera) getCamera()).setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		System.out.println("camera init y: " + getCamera().position.y);
		texture = new Texture("plane_bg0.jpg");
		region1 = new TextureRegion(texture, 325, 458);
		region2 = new TextureRegion(texture, 325, 458);
		plane = new Plane(Gdx.graphics.getWidth()/2-40, 0, 60, 60);
		addActor(plane);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
