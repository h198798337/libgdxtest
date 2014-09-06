package com.test.game.planewar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends Stage implements Screen, EnemyDestriber, CrashChecker {
	float groundOffsetY = 0;
	int maxEnemyCount = 10;//最大敌人
	int density = 15;//密度
	
	Random random;
	
	Texture texture;
	TextureRegion region;
	
	Plane plane;
	List<Enemy> enemies = new ArrayList<Enemy>();
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		((OrthographicCamera) getCamera()).setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		texture = new Texture("plane_bg.png");
		region = new TextureRegion(texture, 325, 1717);
		plane = new Plane(Gdx.graphics.getWidth()/2-40, 0, 50, 50);
		random = new Random(maxEnemyCount);
		getBatch().setProjectionMatrix(getCamera().combined);
		addActor(plane);
		destribeEnemy();
	}
	
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
		if (getCamera().position.y - groundOffsetY > region.getRegionHeight() * 1.5) {
			groundOffsetY += region.getRegionHeight();
		}
		plane.offsetX = 0;
		plane.offsetY = getCamera().position.y - Gdx.graphics.getHeight()/2;
		checkCrash();
		destribeEnemy();
		destroyEnemy();
	}
	
	public void drawTheWorld() {
		getCamera().update();
		Batch batch = getBatch();
		batch.begin();
		batch.draw(region, 0, groundOffsetY);
		batch.draw(region, 0, region.getRegionHeight() + groundOffsetY);
		batch.end();
	}
	
	@Override
	public void destribeEnemy() {
		if(maxEnemyCount > enemies.size() && random.nextInt(density) == (density/2)){
			Enemy enemy = new Enemy(random.nextInt(Gdx.graphics.getWidth()-50), (int)(getCamera().position.y + Gdx.graphics.getHeight()), 1, 0);
			enemies.add(enemy);
			addActor(enemy);
		}
	}

	@Override
	public void destroyEnemy() {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tmp = enemies.get(i);
			if(tmp.y < getCamera().position.y - Gdx.graphics.getHeight()/2 - tmp.width){
				getRoot().removeActor(enemies.remove(i));
			}
		}
	}
	
	@Override
	public void checkCrash() {
		// TODO Auto-generated method stub
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tmp = enemies.get(i);
			if(tmp.rectangle.overlaps(plane.rectangle)) {
				plane.hp = tmp.hp = 0;
				break;
			}
		}
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
