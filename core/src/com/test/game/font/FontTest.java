package com.test.game.font;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontTest extends ApplicationAdapter {
	SpriteBatch batch;
	
	FreeTypeFontGenerator fontGenerator;
	
	FreeTypeBitmapFontData bitmapFontData;
	
	BitmapFont font;
	
	@Override
	public void create () {
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("华康少女字体.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters = "长的帅人告白才叫丑男那性骚扰游戏室HY-";
		parameter.flip = false;
		bitmapFontData = fontGenerator.generateData(parameter);
		font = new BitmapFont(bitmapFontData, bitmapFontData.getTextureRegions(), false);
		font.setColor(Color.BLUE);
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.drawMultiLine(batch,"hello Fighting Potato: \n \n长的帅的人告白才叫告白，\n长的丑的         男人告白那叫性骚扰。", 50, 420);
		font.draw(batch, "--------HYH游戏室", 150, 90);
		batch.end();
	}
}
