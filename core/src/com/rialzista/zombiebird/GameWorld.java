package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.rialzista.gameobjects.Bird;
import com.rialzista.gameobjects.ScrollHandler;
import com.rialzista.zbhelpers.AssetLoader;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private GameState currentState;
    public int midPointY;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        // Grass grow in 66 px bottom midPoint
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
        this.midPointY = midPointY;
    }

    public void update(float delta) {
        // Add limit to delta, to fix freeze game.

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }


    }

    private void updateRunning(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (bird.isAlive() && scroller.collides(bird)) {
            scroller.stop();
            AssetLoader.dead.play();
            bird.die();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
        }

    }

    private void updateReady(float delta) {
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
