package com.rialzista.gameobjects;

import java.util.Random;

/**
 * Created by Rialzista on 10.02.2015.
 */
public class Pipe extends Scrollable {

    private Random r;

    public Pipe(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        // Change height to random value
        height = r.nextInt(90) + 15;
    }
}
