package com.test.game.skintest;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UISimpleTest extends ApplicationAdapter {
   Skin skin;
   Stage stage;
   SpriteBatch batch;

   @Override
   public void create () {
	   Viewport viewport = new ScreenViewport();
	   viewport.setWorldHeight(640);
	   viewport.setWorldHeight(640);
	   
       batch = new SpriteBatch();
       stage = new Stage(viewport);
       Gdx.input.setInputProcessor(stage);
       skin = new Skin();
       
       
       Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
       pixmap.setColor(Color.BLUE);
       pixmap.fill();
       skin.add("white", new Texture(pixmap));

       skin.add("default", new BitmapFont());

       TextButtonStyle textButtonStyle = new TextButtonStyle();
       textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
       textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
       textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
       textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
       textButtonStyle.font = skin.getFont("default");
       skin.add("default", textButtonStyle);

//       TextButton button = new TextButton("Click me!", skin, "bigButton");
       
       Table table = new Table();
       table.setFillParent(true);
       stage.addActor(table);

       final TextButton button1 = new TextButton("Click me!", skin);
       table.add(button1);

       button1.addListener(new ChangeListener() {
           public void changed (ChangeEvent event, Actor actor) {
               System.out.println("Clicked! Is checked: " + button1.isChecked());
               button1.setText("Good job!");
           }
       });

       table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
   }

   @Override
   public void render () {
       Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
       stage.draw();
       Table.drawDebug(stage);
   }

   @Override
   public void dispose () {
       stage.dispose();
       skin.dispose();
   }
}