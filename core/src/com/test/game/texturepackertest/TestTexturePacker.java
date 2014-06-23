package com.test.game.texturepackertest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class TestTexturePacker extends ApplicationAdapter {
	
	SpriteBatch spriteBatch;
	TextureAtlas textureAtlas;
	Array<AtlasRegion> aliens;
	Animation animation;
	float stateTime = 0;
	Sprite songqian;
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		textureAtlas = new TextureAtlas("texturepacker/test.pack");
		aliens = textureAtlas.findRegions("alien");
		System.out.println(aliens.size);
		animation = new Animation(0.25f, aliens);
		animation.setPlayMode(PlayMode.LOOP);
		songqian = textureAtlas.createSprite("SongQian");
		songqian.setPosition(0, 0);
		songqian.setSize(420, 380);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		spriteBatch.begin();
		songqian.draw(spriteBatch);
		spriteBatch.draw(animation.getKeyFrame(stateTime), 430, 0);
		spriteBatch.end();
		if(aliens.size * Gdx.graphics.getDeltaTime() >= stateTime){
			stateTime = stateTime%aliens.size;
		}
		
	}
}
