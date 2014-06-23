package com.test.game.skintest;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UITestForJSON extends ApplicationAdapter {
   String[] listEntries = {"potato1", "potato2", "potato3", "potato4",
           "potato5", "potato6", "potato7", "potato8"};

       Skin skin;
       Stage stage;
       SpriteBatch batch;
       Texture texture1;
       Texture texture2;
       Actor root;
       Label fpsLabel;
   
   @Override
   public void create() {        
	   Viewport viewport = new ScreenViewport();
	   viewport.setWorldHeight(Gdx.graphics.getHeight());
	   viewport.setWorldWidth(Gdx.graphics.getWidth());
	   
       batch = new SpriteBatch();
       skin = new Skin(Gdx.files.internal("uitest/uiskin.json"));
       texture1 = new Texture(Gdx.files.internal("uitest/cat.jpg"));
       texture2 = new Texture(Gdx.files.internal("uitest/potato.jpg"));
       
       TextureRegion image = new TextureRegion(texture1);
       TextureRegion imageFlipped = new TextureRegion(image);
       imageFlipped.flip(true, true);
       
       TextureRegion image2 = new TextureRegion(texture2);
       
       stage = new Stage(viewport);
       
       Gdx.input.setInputProcessor(stage);
       //°´Å¥
       ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
       
       style.imageUp = new TextureRegionDrawable(image);
       
       style.imageDown = new TextureRegionDrawable(imageFlipped);
       
       ImageButton iconButton = new ImageButton(style);

       Button buttonMulti = new TextButton("Button", skin, "toggle");
       
       Button imgButton = new Button(new Image(image), skin);
       
       Button imgToggleButton = new Button(new Image(image), skin, "toggle");

       Label myLabel = new Label("this is some text.", skin);
       
       myLabel.setWrap(true);

       Table t = new Table();
       t.row();
       t.add(myLabel);

       t.layout();

       CheckBox checkBox = new CheckBox("TEXT", skin);
       
       final Slider slider = new Slider(0, 10, 1, false, skin);
       
       TextField textfield = new TextField("", skin);
       
       textfield.setMessageText("POTATO");
       
       SelectBox<String> dropdown = new SelectBox<String>(skin);
       dropdown.setItems(new String[] {"POTATO-Android", "POTATO-Windows", "POTATO-Linux", "POTATO-OSX"});

       
       Image imageActor = new Image(image2);
       
       ScrollPane scrollPane = new ScrollPane(imageActor);
       
       List<String> list = new List<String>(skin);
       list.setItems(listEntries);
       
       ScrollPane scrollPane2 = new ScrollPane(list, skin);
       
       scrollPane2.setFlickScroll(false);
       
       SplitPane splitPane = new SplitPane(scrollPane, scrollPane2, false, skin, "default-horizontal");
       
       fpsLabel = new Label("fps:", skin);

       // configures an example of a TextField in password mode.
       final Label passwordLabel = new Label("Password :", skin);
       
       final TextField passwordTextField = new TextField("", skin);
       
       passwordTextField.setMessageText("POTATO");
       
       passwordTextField.setPasswordCharacter('*');
       
       passwordTextField.setPasswordMode(true);

       // window.debug();
       Window window = new Window("POTATO", skin);
       
       window.setPosition(220, 100);
       window.defaults().spaceBottom(10);
       window.row().fill().expandX();
       window.add(iconButton);
       window.add(buttonMulti);
       window.add(imgButton);
       window.add(imgToggleButton);
       window.row();
       window.add(checkBox);
       window.add(slider).minWidth(100).fillX().colspan(3);
       window.row();
       window.add(dropdown);
       window.add(textfield).minWidth(100).expandX().fillX().colspan(3);
       window.row();
       window.add(splitPane).fill().expand().colspan(4).maxHeight(200);
       window.row();
       window.add(passwordLabel).colspan(2);
       window.add(passwordTextField).minWidth(100).expandX().fillX().colspan(2);
       window.row();
       window.add(fpsLabel).colspan(4);
       window.pack();
       stage.addActor(window);

       textfield.setTextFieldListener(new TextFieldListener() {
           public void keyTyped (TextField textField, char key) {
               if (key == '\n') textField.getOnscreenKeyboard().show(false);
           }
       });

       slider.addListener(new ChangeListener() {
           public void changed (ChangeEvent event, Actor actor) {
               Gdx.app.log("UITest", "slider: " + slider.getValue());
           }
       });

       iconButton.addListener(new ChangeListener() {
           public void changed (ChangeEvent event, Actor actor) {
               new Dialog("Potato", skin, "dialog") {
                   protected void result (Object object) {
                       System.out.println("Chosen: " + object);
                   }
               }.text("Do you like Potato?").button("Yes", true).button("No", false).key(Keys.ENTER, true)
                   .key(Keys.ESCAPE, false).show(stage);
           }
       });
   }
   
   @Override
   public void dispose() {
       stage.dispose();
       skin.dispose();
       texture1.dispose();
       texture2.dispose();
   }

   @Override
   public void render() {        
       Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());

       stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
       stage.draw();
       Table.drawDebug(stage);
       
   }

   @Override
   public void pause() {
   }

   @Override
   public void resume() {
       
   }
}
