package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.GameStateManager;

public abstract class GameState {

    protected GameStateManager gsm;
    protected MyGdxGame game;

    protected SpriteBatch sb;
    protected OrthographicCamera cam, hudCam; //Our player floowing camera and hud following camera

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        game = gsm.game();
        sb = game.getSpriteBatch();
        cam = game.getCam();
        hudCam = game.getHudCam();
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();

}
