package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.rialzista.gameobjects.Bird;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameWorld {

    private Bird bird;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        bird.update(delta);
    }

    public Bird getBird() {
        return bird;
    }

}
