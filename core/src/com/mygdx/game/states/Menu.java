package com.mygdx.game.states;

import static com.mygdx.game.handlers.Box2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.handlers.Background;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.UserInput;

import java.security.PrivateKey;

public class Menu extends GameState {
    private boolean debug = false;
    MyGdxGame game;

    private Background bg;
    private Button btn;

    private Stage stage;

    public Menu(GameStateManager gsm){
        super(gsm);

        //Background
        Texture tex = MyGdxGame.res.getTexture("menu");
        bg = new Background(new TextureRegion(tex), cam, 0);

        stage = new Stage();


        //Button
        tex = MyGdxGame.res.getTexture("enter");
        TextureRegion myTextureRegion = new TextureRegion(tex);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        btn = new ImageButton(myTexRegionDrawable);

        stage.addActor(btn);
        btn.setPosition(MyGdxGame.V_WIDTH/2+150,MyGdxGame.V_HEIGHT/2-100);

    }


    @Override
    public void handleInput() {

        if(UserInput.isPressed(UserInput.BUTTON5)){
            gsm.setState(GameStateManager.PLAY);
            System.out.println("pressed");
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        bg.update(dt);
    }

    @Override
    public void render() {
        bg.render(sb);
        stage.draw();

    }

    @Override
    public void dispose() {


    }
}
