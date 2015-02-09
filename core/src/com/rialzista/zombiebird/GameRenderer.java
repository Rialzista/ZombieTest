package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rialzista.gameobjects.Bird;
import com.rialzista.zbhelpers.AssetLoader;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameRenderer {

    private GameWorld mWorld;
    private OrthographicCamera mOrthographicCamera;
    private ShapeRenderer mShapeRenderer;

    private SpriteBatch mBatcher;

    private int mMidPointY, mGameHeight;

    public GameRenderer (GameWorld world, int gameHeight, int midPointY) {
        this.mWorld = world;

        this.mMidPointY = midPointY;
        this.mGameHeight = gameHeight;

        this.mOrthographicCamera = new OrthographicCamera();
        this.mOrthographicCamera.setToOrtho(true, 137, 204);

        this.mBatcher = new SpriteBatch();
        // attach batcher to cam
        this.mBatcher.setProjectionMatrix(this.mOrthographicCamera.combined);

        this.mShapeRenderer = new ShapeRenderer();
        this.mShapeRenderer.setProjectionMatrix(this.mOrthographicCamera.combined);
    }

    public void render(float runTime) {

        Bird bird = this.mWorld.getBird();

        // Fill bg
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Paint bg color
        this.mShapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        this.mShapeRenderer.rect(0, 0, 136, mMidPointY + 66);

        // Paint grass
        this.mShapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        this.mShapeRenderer.rect(0, mMidPointY + 66, 136, 11);

        // Paint dirt
        this.mShapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        this.mShapeRenderer.rect(0, this.mMidPointY + 77, 136, 52);

        // Ending ShapeRenderer
        this.mShapeRenderer.end();

        // Start SpriteBatch
        this.mBatcher.begin();
        // Disable opacity
        // This action is better to performance
        this.mBatcher.disableBlending();
        this.mBatcher.draw(AssetLoader.bg, 0, mMidPointY + 23, 136, 43);

        // Bird is need opacity turn on there
        this.mBatcher.enableBlending();

        // Paint Bird in coordinates. Get Animation from AssetLoader
        this.mBatcher.draw(AssetLoader.birdAnimation.getKeyFrame(runTime),
                bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());

        // Ending SpriteBatch
        this.mBatcher.end();
     }

}
