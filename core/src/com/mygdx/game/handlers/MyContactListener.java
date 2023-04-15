package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

//    private boolean playerOnGround;
    private int numFootContacts;
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

//        System.out.println(fa.getUserData()+","+fb.getUserData());

        if(fa.getUserData()!= null && fa.getUserData().equals("foot")){
//            System.out.println("fa is foot");
//            playerOnGround = true;
            numFootContacts++;
        }

        if(fb.getUserData()!= null && fb.getUserData().equals("foot")){
//            System.out.println("fb is foot");
//            playerOnGround = true;
            numFootContacts++;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

//        System.out.println(fa.getUserData()+","+fb.getUserData());

        if(fa.getUserData()!= null && fa.getUserData().equals("foot")){
//            System.out.println("fa is foot");
            numFootContacts--;
        }

        if(fb.getUserData()!= null && fb.getUserData().equals("foot")){
//            System.out.println("fb is foot");
            numFootContacts--;
        }

    }

    public boolean isPlayerOnGround(){
        return numFootContacts > 0;
    }



    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
