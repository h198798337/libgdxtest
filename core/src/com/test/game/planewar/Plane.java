package com.test.game.planewar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Plane extends Actor{
	
	float time = 0;
	long duration = 0;
	
	float x, y, moveX, moveY, offsetX, offsetY, 
	touch_X, touch_Y, touchBaseX, touchBaseY, 
	dis_X_temp, dis_Y_temp, width, height;

	Texture plane;
	TextureRegion[][] planeRegion;
	Animation planeFly;
	Animation planeDestroy;

	public Plane(float x, float y, float width, float height) {
		this.x = moveX = x;
		this.y = moveY = y;
		this.width = width;
		this.height = height;
		plane = new Texture("plane.png");
		planeRegion = TextureRegion.split(plane, 71, 86);
		
		planeFly = new Animation(0.1f, planeRegion[0][0], planeRegion[1][0]);
		duration = System.currentTimeMillis();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		touch();
		time += Gdx.graphics.getDeltaTime();
		if(System.currentTimeMillis() - duration < 60000){
			batch.draw(planeFly.getKeyFrame(time, true), moveX + offsetX, moveY + offsetY, width, height);
		}
	}
	
	void touch() {
		// justTouched 是开始按下手指的第一个点。
		if (Gdx.input.justTouched()) {
			touchBaseX = Gdx.input.getX(0);
			touchBaseY = Gdx.input.getY(0);
		} // isTouched 是结束时，手指按下的点。
		else if (Gdx.input.isTouched(0)) {
			touch_X = Gdx.input.getX(0);
			touch_Y = Gdx.input.getY(0);
			float tempX = touch_X - touchBaseX;
			float tempY = -(touch_Y - touchBaseY);
			if(tempX != dis_X_temp){
				dis_X_temp = tempX;
				moveX = x + dis_X_temp;
				moveX = moveX < 0 ? 0 : (moveX > 325 - width ? 325 - width : moveX);
			}
			if(tempY != dis_Y_temp){
				dis_Y_temp = tempY;
				moveY = y + dis_Y_temp;
				moveY = moveY < 0 ? 0 : (moveY > 450 - height ? 450 - height : moveY);
			}
		} else {
			x = moveX;
			y = moveY;
		}
	}
}
