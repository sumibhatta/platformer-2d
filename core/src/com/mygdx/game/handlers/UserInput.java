package com.mygdx.game.handlers;

public class UserInput {

    //current key state
    public static boolean[] keys;

    //previous game state from previous frame
    public static boolean[] pkeys;

    public static final int NUM_KEYS = 5;
    public static final int BUTTON1 = 0;
    public static final int BUTTON2 = 1;

    public static final int BUTTON3 = 2;
    public static final int BUTTON4 = 3;
    public static final int BUTTON5 = 4;


    //initalize arrays
    static{
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    //sets the previous state of keys to present state
    public static void update(){
        for(int i = 0; i<NUM_KEYS;i++){
            pkeys[i] = keys[i];

        }
    }

    public static void setKey(int i, boolean b ){keys[i] = b;}
    public static boolean isDown(int i){return keys[i];}
    public static boolean isPressed(int i){return keys[i] && !pkeys[i];}
}

