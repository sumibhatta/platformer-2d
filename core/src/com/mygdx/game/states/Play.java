package com.mygdx.game.states;

import static com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.Color;
import static com.mygdx.game.handlers.Box2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Crystal;
import com.mygdx.game.entities.HUD;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.MyContactListener;
import com.mygdx.game.handlers.TileMapHelper;
import com.mygdx.game.handlers.UserInput;

public class Play extends GameState{
    public static int level;
    boolean debug = true;

//    private BitmapFont font = new BitmapFont();
    private World world;
    private Box2DDebugRenderer b2dr; //this is going to render all the bodies for us

    private OrthographicCamera b2dCam;

    private MyContactListener cl;

    private TiledMap tiledMap;
    private  TileMapHelper tileMapHelper;
    private OrthogonalTiledMapRenderer tmr;

    private Player player;
    private Body body;
    private Array<Crystal> crystals;

    //Mobs
    private Array<Mob> mobs;

    //HUD
    private HUD hud;
    public Play(GameStateManager gsm){
        super(gsm);


        //World
        world = new World(new Vector2(0,-7f), true); //gravity haru x and y || any body that are inactive are put to sleep
       if(debug == true) {
           b2dr = new Box2DDebugRenderer();
       }



        //Create Body
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100/PPM,300/PPM);

        body = world.createBody(bdef);

        //create fixture
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        shape.setAsBox(11/PPM,11/PPM);
        fdef.shape = shape;
        fdef.friction = 0;
//        fdef.density = 1;
//        fdef.restitution = 1f;
//        fdef.filter.categoryBits = Box2DVars.BIT_PLAYER;
//        fdef.filter.maskBits = Box2DVars.BIT_GROUND;
        body.createFixture(fdef).setUserData("player");
//

//       foot sensor - not necessary i think in our case
        shape.setAsBox(5/PPM, 5/PPM, new Vector2(0, -11/PPM), 0);
        fdef.shape = shape;
//        fdef.filter.categoryBits = Box2DVars.BIT_PLAYER;
//        fdef.filter.maskBits = Box2DVars.BIT_GROUND;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("foot");
//
////       left hand sensor - not necessary i think in our case
//        shape.setAsBox(2/PPM, 2/PPM, new Vector2(-5/PPM, 0), 0);
//        fdef.shape = shape;
////        fdef.filter.categoryBits = Box2DVars.BIT_PLAYER;
////        fdef.filter.maskBits = Box2DVars.BIT_GROUND;
//        fdef.isSensor = true;
//        body.createFixture(fdef).setUserData("lefthand");
//
// //     right hand sensor - not necessary i think in our case
//        shape.setAsBox(2/PPM, 2/PPM, new Vector2(5/PPM, 0), 0);
//        fdef.shape = shape;
////        fdef.filter.categoryBits = Box2DVars.BIT_PLAYER;
////        fdef.filter.maskBits = Box2DVars.BIT_GROUND;
//        fdef.isSensor = true;
//        body.createFixture(fdef).setUserData("righthand");
//
////     head sensor - not necessary i think in our case
//        shape.setAsBox(2/PPM, 2/PPM, new Vector2(0, 8/PPM), 0);
//        fdef.shape = shape;
////        fdef.filter.categoryBits = Box2DVars.BIT_PLAYER;
////        fdef.filter.maskBits = Box2DVars.BIT_GROUND;
////         bdef.position.set(160/PPM,300/PPM);
//        fdef.isSensor = true;
//        body.createFixture(fdef).setUserData("head");


        cl = new MyContactListener();
        world.setContactListener(cl);

        //setup camera box2d
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH/PPM, MyGdxGame.V_HEIGHT/PPM);

        //////////////////////////////////////

        player = new Player(body);

        ////////////////////////

        tileMapHelper= new TileMapHelper(this);
        tmr= tileMapHelper.setupMap();
        ////////////////////////////
        createCrystals();

        //Create Mobs
        createMobs();

        sb.setProjectionMatrix(cam.combined);
        player.render(sb);

        hud = new HUD(player, cl);
    }

    private void createMobs() {

        //Array of mobs
        mobs = new Array<Mob>();

        //Get mobs form map
        MapLayer ml = tiledMap.getLayers().get("mobs");
        if(ml == null) return;

        for(MapObject mo : ml.getObjects()) {
            BodyDef cdef = new BodyDef();
            cdef.type = BodyDef.BodyType.StaticBody;
            float x = (float) mo.getProperties().get("x") / PPM;
            float y = (float) mo.getProperties().get("y") / PPM;
            cdef.position.set(x, y);
            Body body = world.createBody(cdef);
            FixtureDef cfdef = new FixtureDef();
            CircleShape cshape = new CircleShape();
            cshape.setRadius(5 / PPM);
            cfdef.shape = cshape;
            cfdef.isSensor = true;
            body.createFixture(cfdef).setUserData("mobs");
            Mob s = new Mob(body);
            body.setUserData(s);
            mobs.add(s);
            cshape.dispose();
        }
    }

    private void createCrystals() {
        crystals = new Array<Crystal>();

        tiledMap= new TmxMapLoader().load("asset/map/tilemap.tmx");
        MapLayer layer = tiledMap.getLayers().get("Crystals");

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();



        for(MapObject mo: layer.getObjects()){
            bdef.type = BodyDef.BodyType.StaticBody;

            float x = Float.parseFloat((mo.getProperties().get("x").toString())) /PPM;
            float y = Float.parseFloat((mo.getProperties().get("y").toString())) /PPM;

//            System.out.println(x+","+y);
            bdef.position.set(x,y);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(10/PPM);

            fdef.shape = circleShape;
            fdef.isSensor = true; //do not handle collision only detects
//            fdef.filter.categoryBits = Box2DVars
//            fdef.filter.maskBits

            Body body = world.createBody(bdef);
            body.createFixture(fdef).setUserData("crystal");
            Crystal c = new Crystal(body);
            crystals.add(c);

            body.setUserData(c);


        }
    }


    @Override
    public void handleInput() {
//            if(UserInput.isPressed(UserInput.BUTTON1)){
//                System.out.println("pressed z");
//            }
//            if(UserInput.isDown(UserInput.BUTTON2)){
//            System.out.println("x pressed");
//             }

        //make player jump
        //W is pressed then
        if(UserInput.isPressed(UserInput.BUTTON1)){

            if(cl.isPlayerOnGround()){
                player.getBody().applyForceToCenter(0,200, true);
            }
        }

        //D is pressed then
        if(UserInput.isPressed(UserInput.BUTTON2)){
            if(cl.isPlayerOnGround()){
                player.getBody().applyForceToCenter(100,0,true);
            }
        }

        //A is pressed then
        if(UserInput.isPressed(UserInput.BUTTON3)){
            if(cl.isPlayerOnGround()){
                player.getBody().applyForceToCenter(-100,0,true);
            }
        }

        //S is pressed then
        if(UserInput.isPressed(UserInput.BUTTON4)){
            if(cl.isPlayerOnGround()){
                player.getBody().applyForceToCenter(0,-200,true);
            }
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(dt, 1, 1);  //dt = 1/60 , accuracy of collision - how many steps we want each body to check for collision
        // accuracy of setting the body position after collision

        //update player
        player.update(dt);

        //TODO: Check for playerwin
        //

        //Check for player lost
        if(cl.isPlayerDead()) {
            System.out.println("dead");
            gsm.setState(GameStateManager.MENU);
        }

        //update crystals
        for (int i=0; i<crystals.size;i++){
            crystals.get(i).update(dt);
        }

        // update mobs
        for(int i = 0; i < mobs.size; i++) {
            mobs.get(i).update(dt);
        }

        //only after world is rendered
        Array<Body> bodies = cl.getBodiesToRemove();
        for (int i=0; i<bodies.size;i++){
            Body b = bodies.get(i);
            crystals.removeValue((Crystal) b.getUserData(), true);
            world.destroyBody(b);
            player.collectCrystals();
        }
        bodies.clear();


    }



    @Override
    public void render() {

        //Clear Screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        cam.position.set(player.getPosition().x*PPM+MyGdxGame.V_WIDTH/3,MyGdxGame.V_HEIGHT/2,0);
        cam.update();


        //draw tiled map
        tmr.setView(cam);
        tmr.render();

        //draw player
        sb.setProjectionMatrix(cam.combined);
        player.render(sb);

        //draw coins
        for (int i=0; i<crystals.size;i++){
            crystals.get(i).render(sb);
        }

        //draw mobs
        for(int i = 0; i < mobs.size; i++) {
            mobs.get(i).render(sb);
        }

        //draw HUD
        sb.setProjectionMatrix(hudCam.combined);
        hud.render(sb);
        if(debug) {
            b2dCam.position.set(player.getPosition().x + MyGdxGame.V_WIDTH / 3 / PPM, MyGdxGame.V_HEIGHT / 2 / PPM,0);
            b2dCam.update();
            b2dr.render(world, b2dCam.combined);

        }
//        sb.setProjectionMatrix(cam.combined);
//        sb.begin();
//        font.draw(sb, "play state", 100, 100);
//        sb.end();
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void dispose() {

    }




}
