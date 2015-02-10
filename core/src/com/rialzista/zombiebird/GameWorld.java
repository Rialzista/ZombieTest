package com.rialzista.zombiebird;

import com.badlogic.gdx.Gdx;
import com.rialzista.gameobjects.Bird;
import com.rialzista.gameobjects.ScrollHandler;

/**
 * Created by Rialzista on 25.01.2015.
 */
public class GameWorld {

    private Bird bird;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        bird = new Bird(33, midPointY - 5, 17, 12);
        // Grass grow in 66 px bottom midPoint
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        bird.update(delta);
        scroller.update(delta);
    }

    public Bird getBird() {
        return bird;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

}
