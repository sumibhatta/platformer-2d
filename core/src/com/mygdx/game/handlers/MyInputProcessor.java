package com.mygdx.game.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {
    public boolean keyDown(int k){



        if(k == Input.Keys.W){
            UserInput.setKey(UserInput.BUTTON1, true);
        }

        if(k == Input.Keys.D){
            UserInput.setKey(UserInput.BUTTON2, true);
        }

        if(k == Input.Keys.A){
            UserInput.setKey(UserInput.BUTTON3, true);
        }

        if(k == Input.Keys.S){
            UserInput.setKey(UserInput.BUTTON4, true);
        }
        return true;
    }

    public boolean keyUp(int k){

        if(k == Input.Keys.W){
            UserInput.setKey(UserInput.BUTTON1, false);
        }

        if(k == Input.Keys.D){
            UserInput.setKey(UserInput.BUTTON2, false);
        }

        if(k == Input.Keys.A){
            UserInput.setKey(UserInput.BUTTON3, false);
        }

        if(k == Input.Keys.S){
            UserInput.setKey(UserInput.BUTTON4, false);
        }
        return true;
    }




}
