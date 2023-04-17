package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class MyContactListener implements ContactListener {

//    private boolean playerOnGround;
    private int numFootContacts;
    private Array<Body>  bodiesToRemove;
    private int coinCount;


    public MyContactListener(){
//        super();
        bodiesToRemove = new Array<Body>();
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

//        System.out.println(fa.getUserData()+","+fb.getUserData());
//        if(fa == null | fb== null){
//            return;
//        }

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



        //for crystals
        if(fa.getUserData()!= null && fa.getUserData().equals("crystal")){

            //remove crystals
            bodiesToRemove.add(fa.getBody());
        }
        if(fb.getUserData()!= null && fb.getUserData().equals("crystal")){

            //remove crystals
            bodiesToRemove.add(fb.getBody());
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        System.out.println(fa.getUserData()+","+fb.getUserData());
//        if(fa == null || fb== null){
//            return;
//        }

        //counting numbers of coins and sprite collision

        if(fa.getUserData().equals("player") && fb.getUserData().equals("crystal")){
            coinCount++;
        }
//        System.out.println("coinCount"+coinCount);

        if(fa.getUserData()!= null && fa.getUserData().equals("foot")){
//            System.out.println("fa is foot");
            numFootContacts--;
        }

        if(fb.getUserData()!= null && fb.getUserData().equals("foot")){
//            System.out.println("fb is foot");
            numFootContacts--;
        }

    }

    public Array<Body> getBodiesToRemove(){return bodiesToRemove;}
    public int getCoinCount(){return coinCount;}
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
