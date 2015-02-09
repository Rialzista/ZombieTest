package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.rialzista.zbhelpers.InputHandler;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameScreen implements Screen {

    private GameWorld mWorld;
    private GameRenderer mRenderer;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        this.mWorld = new GameWorld(midPointY);       // initialize world
        this.mRenderer = new GameRenderer(this.mWorld); // initialize renderer

        Gdx.input.setInputProcessor(new InputHandler(this.mWorld.getBird()));
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        this.mWorld.update(delta);
        this.mRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
