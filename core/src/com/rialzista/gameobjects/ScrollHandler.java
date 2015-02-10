package com.rialzista.gameobjects;

/**
 * Created by Rialzista on 10.02.2015.
 */
public class ScrollHandler {

    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;

    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    public ScrollHandler(float yPos) {
        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED);
    }

    public void update(float delta) {
        // update all objects
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        // Check is objects pos in left range
        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.get);
        }

    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

}
