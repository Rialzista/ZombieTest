package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rialzista.gameobjects.Bird;
import com.rialzista.gameobjects.Grass;
import com.rialzista.gameobjects.Pipe;
import com.rialzista.gameobjects.ScrollHandler;
import com.rialzista.zbhelpers.AssetLoader;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameRenderer {

    // Game Objects
    private Bird bird;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

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

        // Draw Grass
        drawGrass();

        // Draw Pipes
        drawPipes();

        // Bird is need opacity turn on there
        this.mBatcher.enableBlending();

        // Draw skulls (need opacity)
        drawSkulls();

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

        if (mWorld.isReady()) {
            // Отрисуем сначала тень
            AssetLoader.shadow.draw(this.mBatcher, "Touch me", (136 / 2)
                    - (42), 76);
            // Отрисуем сам текст
            AssetLoader.font.draw(this.mBatcher, "Touch me", (136 / 2)
                    - (42 - 1), 75);
        } else {

            if (mWorld.isGameOver()) {
                AssetLoader.shadow.draw(this.mBatcher, "Game Over", 25, 56);
                AssetLoader.font.draw(this.mBatcher, "Game Over", 24, 55);

                AssetLoader.shadow.draw(this.mBatcher, "Try again?", 23, 76);
                AssetLoader.font.draw(this.mBatcher, "Try again?", 24, 75);



            }

            String score = mWorld.getScore() + "";

            AssetLoader.shadow.draw(this.mBatcher, "" + mWorld.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            AssetLoader.font.draw(this.mBatcher, "" + mWorld.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);
        }

        this.mBatcher.end();

        /*
        *
        * Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);

        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(bg, 0, midPointY + 23, 136, 43);

        drawGrass();

        drawPipes();
        batcher.enableBlending();

        drawSkulls();

        if (bird.shouldntFlap()) {
            batcher.draw(birdMid, bird.getX(), bird.getY(),
                    bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());

        } else {
            batcher.draw(birdAnimation.getKeyFrame(runTime), bird.getX(),
                    bird.getY(), bird.getWidth() / 2.0f,
                    bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(),
                    1, 1, bird.getRotation());
        }


        // ВРЕМЕННЫЙ КОД! Изменим позже:

        if (myWorld.isReady()) {
            // Отрисуем сначала тень
            AssetLoader.shadow.draw(batcher, "Touch me", (136 / 2)
                    - (42), 76);
            // Отрисуем сам текст
            AssetLoader.font.draw(batcher, "Touch me", (136 / 2)
                    - (42 - 1), 75);
        } else {

            if (myWorld.isGameOver()) {
                AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                AssetLoader.font.draw(batcher, "Game Over", 24, 55);

                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again?", 24, 75);



            }

            String score = myWorld.getScore() + "";

            AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
                    - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
                    - (3 * score.length() - 1), 11);
        }

        batcher.end();
        *
        * */
     }

    private void drawGrass() {
        // Draw grass
        this.mBatcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());

        this.mBatcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // temp code
        this.mBatcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);

        this.mBatcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        this.mBatcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);

        this.mBatcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        this.mBatcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);

        this.mBatcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);

    }

    private void drawPipes() {
        // temp code
        this.mBatcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        this.mBatcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), mMidPointY + 66 - (pipe1.getHeight() + 45));

        this.mBatcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        this.mBatcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), mMidPointY + 66 - (pipe2.getHeight() + 45));

        this.mBatcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        this.mBatcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), mMidPointY + 66 - (pipe3.getHeight() + 45));
    }

    private void initGameObjects() {
        bird = this.mWorld.getBird();
        scroller = this.mWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
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
