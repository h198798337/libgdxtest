package com.test.game.collision;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mario extends Actor implements InputProcessor{
	public float x;
	public float y;
	public float statetime;
	public int[][] barries;
	public int MAP_TILE_WIDTH;
	public int MAP_TILE_HEIGHT;
	
	public TextureRegion curFrame;
	public TextureRegion rightIdel;
	public TextureRegion leftIdel;
	
	Animation rightA;
	Animation leftA;
	
	STATE preState;
	STATE state;
	
	private enum STATE {
		Right, Left, Idel
	}
	
	private Map<Integer, STATE> statelist = new HashMap<Integer, Mario.STATE>();//动作队列
	
	public Mario(float x, float y) {
		this.x = x;
		this.y = y;
		Texture texture = new Texture("mario.png");
		TextureRegion[][] standers = TextureRegion.split(texture, 64, 64);
		TextureRegion[][] mirror = TextureRegion.split(texture, 64, 64);
		
		for(TextureRegion[] row : mirror){
			for(TextureRegion col : row){
				col.flip(true, false);//左右调转
			}
		}
		
		TextureRegion[] rightR = new TextureRegion[3];
		rightR[0] = standers[0][1];
		rightR[1] = standers[0][2];
		rightR[2] = standers[0][0];
		rightA = new Animation(0.1f, rightR);
		
		TextureRegion[] leftR = new TextureRegion[3];
		leftR[0] = mirror[0][1];
		leftR[1] = mirror[0][2];
		leftR[2] = mirror[0][0];
		leftA = new Animation(0.1f, leftR);
		
		rightIdel = standers[0][0];
		leftIdel = mirror[0][0];
		
		statetime = 0f;
		state = STATE.Idel;
		preState = STATE.Right;
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		statetime += Gdx.graphics.getDeltaTime();
		check();
		update();
		if(3*Gdx.graphics.getDeltaTime() <= statetime){
			statetime = statetime % 3;
		}
		batch.draw(curFrame, x, y);
	}
	
	private void check() {
		if(statelist.size() == 1)
			state = statelist.values().iterator().next();
		else
			state = STATE.Idel;
		if(state == STATE.Left){
			curFrame = leftA.getKeyFrame(statetime, true);
			preState = STATE.Left;
		} else if(state == STATE.Right){
			curFrame = rightA.getKeyFrame(statetime, true);
			preState = STATE.Right;
		} else if(state == STATE.Idel){
			curFrame = preState == STATE.Right ? rightIdel : leftIdel;
		}
	}
	
	private boolean crossAllow(float xoffset, float yoffset) {//是否可通过
		int colum = (int) ((y + yoffset)/MAP_TILE_HEIGHT);
		int line = (int) ((x + xoffset)/MAP_TILE_WIDTH);
		return barries[line][colum < 0 ? 0 : colum] != 1;
	}
	
	private void update() {
		if(crossAllow(32, 0)){
			y -= 1.5f;
		}
		if(state == STATE.Right && crossAllow(64, 32)){
			x += 1.5f;
		} else if(state == STATE.Left && crossAllow(0, 32)){
			x -= 1.5f;
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch (keycode) {
		case Input.Keys.LEFT:
			statelist.put(keycode, STATE.Left);
			return true;
		case Input.Keys.RIGHT:
			statelist.put(keycode, STATE.Right);
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		statelist.remove(keycode);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
