//An animation that is attatcghed to box2d body
package com.mygdx.game.entities;

import static com.mygdx.game.handlers.Box2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.handlers.Animation;


public class B2DSprite {

    protected Body body;
    protected Animation animation;
    protected float width;
    protected float height;

    public B2DSprite(Body body){
        this.body = body;
        animation = new Animation();
    }

    public void setAnimation(TextureRegion[] reg, float delay){
        animation.setFrames(reg, delay);
        width = reg[0].getRegionWidth();
        height = reg[0].getRegionHeight();
    }

    public void update(float dt){
        animation.update(dt);
    }

    public void render(SpriteBatch sb){
//        System.out.println("Width=");
//        System.out.println(body.getPosition().x*PPM-width/2);
        sb.begin();
        sb.draw(
                animation.getFrame(),
                body.getPosition().x*PPM-width/2,
                body.getPosition().y*PPM-height/2
//                1600/PPM, 30000/PPM
        );
//        System.out.println(body.getPosition().x);
//        System.out.println("width ="+width);

        sb.end();
    }

    public Body getBody(){
        return body;
    }

    public Vector2 getPosition(){return  body.getPosition();}
    public float getWidth(){return width;}
    public float getHeight(){return height;}
}
