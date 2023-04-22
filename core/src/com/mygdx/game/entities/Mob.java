package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.MyGdxGame;


public class Mob extends B2DSprite {
	
	public Mob(Body body) {
		
		super(body);

		Texture tex = MyGdxGame.res.getTexture("mobs");
		TextureRegion[][] tmp = TextureRegion.split(tex,
				tex.getWidth() / 5,
				tex.getHeight() / 3);
		TextureRegion[] sprites = new TextureRegion[5];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 5; j++) {
				sprites[index++] = tmp[i][j];
                System.out.println(i+","+j);
			}
		}

//		TextureRegion[] sprites = TextureRegion.split(tex, 32, 32)[0];
		animation.setFrames(sprites, 1 / 12f);
		
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();
		
	}
	
}
