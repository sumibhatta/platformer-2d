package com.mygdx.game.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {
    public boolean keyDown(int k){

        if(k == Input.Keys.Z){
            UserInput.setKey(UserInput.BUTTON1, true);
        }

        if(k == Input.Keys.X){
            UserInput.setKey(UserInput.BUTTON2, true);
        }
        return true;
    }

    public boolean keyUp(int k){

        if(k == Input.Keys.Z){
            UserInput.setKey(UserInput.BUTTON1, false);
        }

        if(k == Input.Keys.X){
            UserInput.setKey(UserInput.BUTTON2, false);
        }
        return true;
    }




}
