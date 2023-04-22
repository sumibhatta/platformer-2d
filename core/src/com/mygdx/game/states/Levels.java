package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.GameStateManager;

public class Levels extends GameState {

    private TextureRegion reg;

    private Stage stage;


    private Button btn1, btn2, btn3, btn4, btn5;


    public Levels(GameStateManager gsm) {

        super(gsm);

        reg = new TextureRegion(MyGdxGame.res.getTexture("background"), 0, 0, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);

        stage = new Stage();

        //Button

        TextureRegion buttonReg = new TextureRegion(MyGdxGame.res.getTexture("enter"));
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(buttonReg);
        btn1 = new ImageButton(myTexRegionDrawable);
        btn1.setPosition(500,100);
        stage.addActor(btn1);

        TextureRegion button2Reg = new TextureRegion(MyGdxGame.res.getTexture("enter"));
        TextureRegionDrawable myTexRegion2Drawable = new TextureRegionDrawable(button2Reg);
        btn2 = new ImageButton(myTexRegion2Drawable);
        btn2.setPosition(500,200);
        stage.addActor(btn2);

        TextureRegion button3Reg = new TextureRegion(MyGdxGame.res.getTexture("enter"));
        TextureRegionDrawable myTexRegion3Drawable = new TextureRegionDrawable(button3Reg);
        btn3 = new ImageButton(myTexRegion3Drawable);
        btn3.setPosition(500,300);
        stage.addActor(btn3);


        cam.setToOrtho(false, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);

    }

    public void handleInput() {
    }

    public void update(float dt) {

        handleInput();


    }


        public void render () {

            sb.setProjectionMatrix(cam.combined);
            sb.begin();
            sb.draw(reg, 0, 0);
            sb.end();

            stage.draw();
        }


    public void dispose() {
        // everything is in the resource manager com.neet.blockbunny.handlers.Content
    }

}
