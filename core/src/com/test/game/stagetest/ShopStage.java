package com.test.game.stagetest;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ShopStage extends Stage{
	Image buysuccess;
	
	public ShopStage(Viewport viewport){
		super(viewport);
		init();
	}
	
	public void init() {
		Texture material = new Texture("object.png");
		
		TextureRegion backgroundRegion = new TextureRegion(material, 0, 81, 512, 373);
		Image background = new Image(backgroundRegion);
		background.setSize(741, 440);
		
		Image heart = new Image(new TextureRegion(material, 0, 0, 107, 78));
		heart.setPosition(116, 282);
		heart.addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				buysuccess.setVisible(true);
				return true;
			}
		});
		
		Image gold = new Image(new TextureRegion(material, 106, 0, 100, 83));
		gold.setPosition(318, 288); 
		gold.addListener(new InputListener(){
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				buysuccess.setVisible(true);
				return true;
			}
		});
		
		buysuccess = new Image(new TextureRegion(material, 515, 0, 267, 255));
		buysuccess.setPosition(241, 152);
		buysuccess.setVisible(false);
		
		addActor(background);
		addActor(heart);
		addActor(gold);
		addActor(buysuccess);
		
	}
}
