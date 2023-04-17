package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.handlers.Content;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.MyInputProcessor;
import com.mygdx.game.handlers.UserInput;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 360;
	public static final int SCALE = 2;

	private SpriteBatch sb;
	private OrthographicCamera cam, hudCam;

	private GameStateManager gsm;

	public static final float STEP = 1/60f;
	private float accum; // to keep track of full time passed

	public static Content res;
	public SpriteBatch getSpriteBatch(){return  sb;}
	public OrthographicCamera getCam(){return  cam;}
	public OrthographicCamera getHudCam(){return hudCam;}




	
	@Override
	public void create () {

		Gdx.input.setInputProcessor(new MyInputProcessor());

		res = new Content();
		res.loadTexture("asset/player/player.png", "player");
		res.loadTexture("asset/map/Objects.png", "coins");
		res.loadTexture("asset/map/ui.png","ui");
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);



	}

	@Override
	public void render () {
		accum+= Gdx.graphics.getDeltaTime();
		while(accum>=STEP){
			accum -=STEP;
			gsm.update(STEP);
			gsm.render();
			UserInput.update();
		}

//		sb.setProjectionMatrix(hudCam.combined);
//		sb.begin();
//		sb.draw(res.getTexture("ui"), 0,0);
//		sb.end();
	}
	
	@Override
	public void dispose () {

	}
}
