package com.test.game.planewar;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Plane extends Actor implements BulletDestribe{
	int hp = 1;
	
	float durationTime = 0;
	
	float bulletDurationTime = 0.1f;//发射子弹时间间隔
	float bulletDestribeTime = 0;//子弹分配时间
	float bulletSpeed = 20;
	
	float x, y, moveX, moveY, offsetX, offsetY, 
	touch_X, touch_Y, touchBaseX, touchBaseY, 
	dis_X_temp, dis_Y_temp, width, height;

	Texture plane;
	TextureRegion[][] planeRegion;
	Animation planeFly;
	Animation planeDestroy;
	
	Rectangle rectangle = new Rectangle();
	
	List<Bullet> bullets = new ArrayList<Bullet>();

	public Plane(float x, float y, float width, float height) {
		this.x = moveX = x;
		this.y = moveY = y;
		this.width = width;
		this.height = height;
		plane = new Texture("plane.png");
		planeRegion = TextureRegion.split(plane, 71, 86);
		
		planeFly = new Animation(0.1f, planeRegion[0][0], planeRegion[1][0]);
		planeDestroy = new Animation(2f, planeRegion[2][0], planeRegion[3][0], planeRegion[4][0], planeRegion[5][0]);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		touch();
		durationTime += Gdx.graphics.getDeltaTime();
		switch (hp) {
		case 0:
			planeDestroy(batch);
			break;
		case 1:
			planeAlive(batch);
			destribeBullet();
			destroyBullet();
			break;
		default:
			break;
		}
	}
	
	void touch() {
		if(hp <= 0)//hp降为0时，触摸无效
			return;
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
	
	/**
	 * 当战机仍然生存时动作
	 */
	private void planeAlive(Batch batch) {
		rectangle.set(moveX + offsetX, moveY + offsetY, width, height);
		batch.draw(planeFly.getKeyFrame(durationTime, true), moveX + offsetX, moveY + offsetY, width, height);
	}
	
	/**
	 * 飞机销毁时动作
	 * @param batch
	 */
	private void planeDestroy(Batch batch){
		batch.draw(planeDestroy.getKeyFrame(durationTime), moveX + offsetX, moveY + offsetY, width, height);
		if(planeDestroy.isAnimationFinished(8))
			hp = -1;
	}

	@Override
	public void destribeBullet() {
		// TODO Auto-generated method stub
		if(durationTime - bulletDestribeTime >= bulletDurationTime){
			bulletDestribeTime = durationTime;
			Bullet bullet = new Bullet(moveX + offsetX + width/2, moveY + offsetY, bulletSpeed);
			bullets.add(bullet);
			if(getStage() instanceof GameScreen){
				getStage().addActor(bullet);
			}
		}
	}

	@Override
	public void destroyBullet() {
		// TODO Auto-generated method stub
		for (int i = 0; i < bullets.size(); i++) {
			Bullet tmp = bullets.get(i);
			if(tmp.hp == 0 || tmp.y > getStage().getCamera().position.y + Gdx.graphics.getHeight()/2 + height){
				getStage().getRoot().removeActor(bullets.remove(i));
			}
		}
	}
}
