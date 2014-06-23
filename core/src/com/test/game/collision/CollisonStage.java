package com.test.game.collision;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CollisonStage extends Stage {
	private TiledMap map;
	private TiledMapRenderer renderer;
	public int[][] barries;
	
	Mario mario;
	
	public static final int MAP_TILE_WIDTH = 48;
	public static final int MAP_TILE_HEIGHT = 48;
	
	public CollisonStage(Viewport viewport){
		super(viewport);
		init();
	}
	
	public void init() {
		mario = new Mario(0, 0);
		barries = new int[10][10];
		mario.barries = barries;
		mario.MAP_TILE_HEIGHT = MAP_TILE_HEIGHT;
		mario.MAP_TILE_WIDTH = MAP_TILE_WIDTH;
		addActor(mario);
		
		map = new TmxMapLoader().load("map/collision.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f);
		setMario();
	}
	
	@Override
	public void draw () {
		getViewport().getCamera().update();
		Camera camera = getViewport().getCamera();
		renderer.setView((OrthographicCamera)camera);
		renderer.render();
		super.draw();
	}
	
	public void setMario() {
		MapLayers mapLayers = map.getLayers();
		for (MapLayer mapLayer : mapLayers) {
			if(mapLayer.getName().equals("actor")){
				MapObjects mapObjects = mapLayer.getObjects();
				for (MapObject mapObject : mapObjects) {
					if(mapObject.getName().equals("mario") 
							&& mapObject instanceof RectangleMapObject){
						RectangleMapObject ro = (RectangleMapObject)mapObject;
						mario.x = ro.getRectangle().x;
						mario.y = ro.getRectangle().y;
					}
				}
			} else if(mapLayer.getName().equals("barries")) {
				if(mapLayer instanceof TiledMapTileLayer){
					TiledMapTileLayer mapTileLayer = (TiledMapTileLayer)mapLayer;
					for(int i = 0; i < 10; i++){
						for(int j = 0; j < 10; j++){
							Cell cell = mapTileLayer.getCell(i, j);
							if(cell != null){
								barries[i][j] = 1;
							}
						}
					}
				}
			}
		}
	}
}
