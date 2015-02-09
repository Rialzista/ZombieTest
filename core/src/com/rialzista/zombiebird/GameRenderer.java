package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameRenderer {

    private GameWorld mWorld;
    private OrthographicCamera mOrthographicCamera;
    private ShapeRenderer mShapeRenderer;

    public GameRenderer (GameWorld world) {
        this.mWorld = world;

        this.mOrthographicCamera = new OrthographicCamera();
        this.mOrthographicCamera.setToOrtho(true, 136, 204);

        this.mShapeRenderer = new ShapeRenderer();
        this.mShapeRenderer.setProjectionMatrix(this.mOrthographicCamera.combined);
    }

    public void render() {
        Gdx.app.log("GameRender", "render");

     }

}
