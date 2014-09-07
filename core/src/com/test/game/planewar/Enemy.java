package com.test.game.planewar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor{
	float durationTime = 0f;
	float x, y, speed;
	int width, height;
	int hp;
	
	TextureRegion[][] enemy;
	Animation enemyDestroyAnimation;//敌人被摧毁时动画
	Rectangle rectangle = new Rectangle();
	
	public Enemy(int x, int y, int speed, int type) {
		this.x = x;
		this.y = y;
		this.speed = speed; 
		switch (type) {
		case 0:
			width = 49;
			height = 63;
			enemy = TextureRegion.split(new Texture("enemy0.png"), width, height);
			hp = 1;
			enemyDestroyAnimation = new Animation(1f, enemy[0][1], enemy[0][2], enemy[0][3], enemy[0][4], enemy[0][4], enemy[0][4]);
			break;
		default:
			break;
		}	
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		durationTime += Gdx.graphics.getDeltaTime();
		switch (hp) {
		case 0:
			enemyDestroy(batch);
			break;
		case 1:
			enemyAlive(batch);
			break;
		default:
			break;
		}
	}
	
	private void enemyDestroy(Batch batch) {
		batch.draw(enemyDestroyAnimation.getKeyFrame(durationTime), x, y, width, height);
		if(enemyDestroyAnimation.isAnimationFinished(5))
			hp = -1;
	}
	
	private void enemyAlive(Batch batch) {
		y -= speed;
		rectangle.set(x, y, width, height);
		batch.draw(enemy[0][0], x, y, width, height);
	}
}
