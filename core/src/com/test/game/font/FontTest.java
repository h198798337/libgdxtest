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
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("������Ů����.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters = "����˧�˸�ײŽг�������ɧ����Ϸ��HY-";
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
		font.drawMultiLine(batch,"hello Fighting Potato: \n \n����˧���˸�ײŽи�ף�\n���ĳ��         ���˸���ǽ���ɧ�š�", 50, 420);
		font.draw(batch, "--------HYH��Ϸ��", 150, 90);
		batch.end();
	}
}
