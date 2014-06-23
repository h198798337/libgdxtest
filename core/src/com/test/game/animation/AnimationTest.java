package com.test.game.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class AnimationTest extends ApplicationAdapter {
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;

	SpriteBatch batch;
	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	TextureRegion curFrame;

	float stateTime;

	@Override
	public void create() {
		walkSheet = new Texture("animation_sheet.png");
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames);
		walkAnimation.setPlayMode(PlayMode.LOOP);
		batch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		curFrame = walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		batch.draw(curFrame, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		batch.end();
		if(walkFrames.length*Gdx.graphics.getDeltaTime() <= stateTime){
			stateTime = stateTime%walkFrames.length;
		}
	}
}
