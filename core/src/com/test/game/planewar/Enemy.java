package com.test.game.planewar;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor{

	float x, y, speed;
	int hp;
	
	TextureRegion enemy;
	
	public Enemy(int x, int y, int type) {
		this.x = x;
		this.y = y;
		switch (type) {
		case 0:
//			enemy = new TextureRegion(new Texture("planes.jpg"), )
			break;
		case 1:

			break;

		default:
			break;
		}
		
		
		
	}
	
	
}
