package com.test.game.planewar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor{
	int hp = 1;
	
	float x, y, speed;
	
	TextureRegion bulletTexture;
	Rectangle rectangle = new Rectangle();
	
	public Bullet(float x, float y, float speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		bulletTexture = new TextureRegion(new Texture("bullet.png"), 10, 15);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		y += speed;
		rectangle.set(x, y, bulletTexture.getRegionWidth(), bulletTexture.getRegionHeight());
		batch.draw(bulletTexture, x, y);
	}
}
