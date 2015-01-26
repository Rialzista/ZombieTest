package com.rialzista.zombiebird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class ZombieGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("ZBGame", "created");
        setScreen(new GameScreen());
    }

}