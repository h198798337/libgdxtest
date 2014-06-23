package com.test.game.stagetest;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.test.game.actortest.Mario;

public class GameStage extends Stage {
	
	InputMultiplexer multiplexer;
	
	public GameStage(Viewport viewport){
		super(viewport);
		init();
	}
	
	public void init() {
		Texture texture = new Texture("mario_shop.jpg");
		TextureRegion mushRegion = new TextureRegion(texture, 204, 0, 75, 75);
		Image mushroom = new Image(mushRegion);
		mushroom.setPosition(290, 60);
		
		Image background = new Image(new Texture("mario_map.jpg"));
		background.setPosition(0, 170);
		
		Mario mario = new Mario(100, 190);
		
		addActor(background);
		addActor(mario);
		addActor(mushroom);
		
		mushroom.addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Constants.StageFlag = Constants.ShopStage;
				return true;
			}
		});
		
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(mario);
		multiplexer.addProcessor(this);
	}
}
