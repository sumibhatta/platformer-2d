package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;

public class Crystal extends B2DSprite{
    public Crystal(Body body){
        super(body);

        Texture tex = MyGdxGame.res.getTexture("coins");


        TextureRegion[][] tmp = TextureRegion.split(tex,
                tex.getWidth() / 9,
                tex.getHeight() / 12);
        TextureRegion[] sprites = new TextureRegion[4];
        int index = 0;
        for (int i = 4; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                sprites[index++] = tmp[i][j];
//                System.out.println(i+","+j);
            }
        }
        setAnimation(sprites, 1/12f);
    }

}
