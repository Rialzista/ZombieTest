package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rialzista.gameobjects.Bird;
import com.rialzista.zbhelpers.AssetLoader;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameRenderer {

    // Game Objects
    private Bird bird;

    // Game Assets
    private TextureRegion bg, grass;
    private Animation birdAnimation;
    private TextureRegion birdMid, birdDown, birdUp;
    private TextureRegion skullUp, skullDown, bar;

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
        this.mBatcher.setProjectionMatrix(this.mOrthographicCamera.combined);
        this.mShapeRenderer = new ShapeRenderer();
        this.mShapeRenderer.setProjectionMatrix(this.mOrthographicCamera.combined);

        // Call helpers methods
        this.initGameObjects();
        this.initAssets();
    }

    public void render(float runTime) {

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
        this.mBatcher.draw(bg, 0, mMidPointY + 23, 136, 43);

        // Bird is need opacity turn on there
        this.mBatcher.enableBlending();

        if (bird.shouldntFlap()) {
            this.mBatcher.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        } else {
            this.mBatcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
                    1, 1, bird.getRotation());
        }

        // Ending SpriteBatch
        this.mBatcher.end();
     }

    private void initGameObjects() {
        bird = this.mWorld.getBird();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        birdAnimation = AssetLoader.birdAnimation;
        birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

}
