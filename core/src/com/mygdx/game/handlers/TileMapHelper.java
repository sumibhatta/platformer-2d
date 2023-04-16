package com.mygdx.game.handlers;

import static com.mygdx.game.handlers.Box2DVars.PPM;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.states.Play;

public class TileMapHelper {
    public TiledMap tiledMap;
    private final Play gameScreen;
    private World world;
    public TileMapHelper(Play gameScreen){
        this.gameScreen=gameScreen;
    }
    public OrthogonalTiledMapRenderer setupMap(){
        //load tiled map
        tiledMap= new TmxMapLoader().load("asset/map/tilemap.tmx");
//        System.out.println(tiledMap);
        parseMapObjects(tiledMap.getLayers().get("Objects").getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);

    }
    private void parseMapObjects(MapObjects mapObjects){
        for (MapObject mapObject :mapObjects){
            if (mapObject instanceof PolygonMapObject){
                createStaticBody((PolygonMapObject) mapObject);

            }

        }

    }

    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef= new BodyDef();
        bodyDef.type= BodyDef.BodyType.StaticBody;
        Body body= gameScreen.getWorld().createBody(bodyDef);
        Shape shape= createPolygonShape(polygonMapObject);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(shape,1000).setUserData("ground");
        shape.dispose();
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices= polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices =new Vector2[vertices.length/2];

        for (int i = 0; i < vertices.length / 2; i++){
            Vector2 current = new Vector2(vertices[i * 2]/ PPM, vertices[(i * 2) + 1]/PPM);  //PPM define pixel per meter. so we have new current vertices in x and y position.divide is done to match with box 2d world
            worldVertices[i]=current;
        }

        PolygonShape shape= new PolygonShape();
        shape.set(worldVertices);
        return shape;

    }

//    public void getTiledMap(){
//        tiledMap =
//    }
}
