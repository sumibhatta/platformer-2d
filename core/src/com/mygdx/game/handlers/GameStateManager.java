package com.mygdx.game.handlers;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Levels;
import com.mygdx.game.states.Menu;
import com.mygdx.game.states.Play;

import java.util.Stack;

public class GameStateManager {

    private MyGdxGame game;

    private Stack<GameState> gameStates;

    public  static final int PLAY = 388031654;
    public static final int MENU =83774392;
    public static final int LEVEL =1231;

    public GameStateManager(MyGdxGame game){
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(LEVEL);
    }

    public MyGdxGame game(){return game;}

    public void update(float dt){
        gameStates.peek().update(dt);
    }

    public void render(){
        gameStates.peek().render();
    }



    //helper method to create new gameStates
    private  GameState getState(int state){
        if(state == MENU) return new Menu(this);
        if(state == PLAY) return new Play(this);
        if(state == LEVEL) return new Levels(this);
        return null;
    }

    //replacing whatever is in the top
    public void setState(int state){
        popState();
        pushState(state);
    }

    public void popState(){
        GameState g = gameStates.pop();
        g.dispose();
    }

    public void pushState(int state){
        gameStates.push(getState(state));
    }

    public void dispose(){
        GameState gameState = gameStates.pop();
        gameState.dispose();
    }

}
