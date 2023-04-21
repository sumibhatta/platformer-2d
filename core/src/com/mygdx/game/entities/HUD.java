package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.MyContactListener;

public class HUD {

    private Player player;
    private TextureRegion[] countCoins;

    private MyContactListener cl;

    private TextureRegion[][] tmp;


    //font for hud
    private BitmapFont scoreFont ;
    private GlyphLayout scoreLayout;




    public HUD(Player player, MyContactListener cl){
        this.player = player;
        this.cl = cl;
        this.scoreFont = new BitmapFont(Gdx.files.internal("asset/fonts/score.fnt"));



    }
    public void render(SpriteBatch sb){
        Texture tex = MyGdxGame.res.getTexture("coins");

        TextureRegion[][] tmp = TextureRegion.split(tex,
                tex.getWidth() / 9,
                tex.getHeight() / 12);
        TextureRegion coin = tmp[4][0];





        scoreFont.getData().setScale(0.75f);
        scoreLayout = new GlyphLayout(scoreFont,""+cl.getCoinCount());
//        System.out.println("Width"+Gdx.graphics.getWidth()/2/PPM);


        sb.begin();
        scoreFont.draw(sb,scoreLayout,50,350);
        sb.draw(coin, 5, 325, 50, 50);
        sb.end();

    }

}
