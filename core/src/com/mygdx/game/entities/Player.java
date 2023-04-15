package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.states.Play;

public class Player extends B2DSprite {
    private int numCrystals;
    private int totalCrystals;
    private World world;

    public Player (Body body){
        super(body);

        Texture tex = MyGdxGame.res.getTexture("player");

        //////////////////////////// this works////////////////////
//        TextureRegion[][] tmp = TextureRegion.split(tex,
//                tex.getWidth() / 17,
//                tex.getHeight() / 8);
//        TextureRegion[] sprites = new TextureRegion[8 * 17];
//        int index = 0;
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 17; j++) {
//                sprites[index++] = tmp[i][j];
//            }
//        }
        //////////////////////////////////////////////////////////
        TextureRegion[][] tmp = TextureRegion.split(tex,
                tex.getWidth() / 17,
                tex.getHeight() / 8);
        TextureRegion[] sprites = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                sprites[index++] = tmp[i][j];
            }
        }
//        TextureRegion[] sprites = TextureRegion.split(tex, 32,32)[0];

        setAnimation(sprites, 1/12f);

    }

    public void collectCrystals(){numCrystals++;}
    public int getNumCrystals(){return numCrystals;}
    public  void setTotalCrystals(int i){totalCrystals = i;}
}
