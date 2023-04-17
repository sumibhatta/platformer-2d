package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.MyContactListener;

public class HUD {

    private Player player;
    private TextureRegion[] countCoins;

    private MyContactListener cl;

    private TextureRegion[][] tmp;



    public HUD(Player player, MyContactListener cl){
        this.player = player;
        this.cl = cl;

        Texture tex = MyGdxGame.res.getTexture("ui");
    countCoins = new TextureRegion[10];

    TextureRegion[][] tmp = TextureRegion.split(tex,
                tex.getWidth() / 17,
                tex.getHeight() / 17);
        int index = 0;
        for (int i = 8; i < 10; i++) {
            for (int j = 12; j < 17; j++) {
                countCoins[index++] = tmp[i][j];
              System.out.println("From HUD"+i+","+j);
            }
        }
    }
    public void render(SpriteBatch sb){
        for(int i = 0; i<10;i++){
            if(cl.getCoinCount() == i){
                System.out.println("i is "+i);
                sb.begin();
                sb.draw(countCoins[i], 40, 320);
                sb.end();
            }

        }

    }

}
