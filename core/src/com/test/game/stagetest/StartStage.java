package com.test.game.stagetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartStage extends Stage{
	
	Music backMusic;
	
	public StartStage(Viewport viewport){
		super(viewport);
		init();
	}
	
	public void init() {
		backMusic = Gdx.audio.newMusic(Gdx.files.internal("泳儿-一天一天.mp3"));
		
		Texture image = new Texture("mario_game_main.jpg");
		TextureRegion backgroundRegion = new TextureRegion(image, 0, 0, 741, 446);
		Image background = new Image(backgroundRegion);
		
		TextureRegion btnStartRegion = new TextureRegion(image, 852, 0, 99, 36);
		Image btnStart = new Image(btnStartRegion);
		btnStart.setPosition(77, 343);
		
		this.addActor(background);
		this.addActor(btnStart);
		
		btnStart.addListener(new InputListener(){
			@Override
	           public boolean touchDown(InputEvent event, float x, float y,
	                   int pointer, int button) {
	               Constants.StageFlag = Constants.GameStage;
	               return true;
	           }          
		});
		
		backMusic.setLooping(true);
		backMusic.play();
		backMusic.setVolume(0.9f);
	}
	
}
