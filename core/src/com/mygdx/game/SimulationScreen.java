package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.ArrayList;
import java.util.Random;
import miss.*;

public class SimulationScreen implements ScreenWithStage {

    private final Stage stage;
    private final Integer maxTreeHeight;
    private final Integer distribution;
    private final Integer forestSize;
    private final Integer startingPointX;
    private final Integer startingPointY;
    private final Integer radius;
    private final Integer speed;

    private static ArrayList<Tree> trees;
    private Rankine vortex;
    private Random random;

    private final double speedx;
    private final double speedy;

    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;
    private final Texture treeTexture;
    private final Texture vortexTexture;
    //private Affine2 affine2;
    private TextureRegion textureRegion;
    private TextureRegion treeTextureRegion;

    Texture tex = new Texture(Gdx.files.internal("./forest.jpg"));

    private Vector2 touchFlag;
    private Vector2 camPositionFlag;

    private final OrthographicCamera cam;
    
    private final Texture bgtex;

    public SimulationScreen(Integer maxTreeHeight, Integer distribution, Integer forestSize,
                            Integer startingPointX,	Integer startingPointY, Integer radius, Integer speed) {
        stage = new Stage();
        this.maxTreeHeight = maxTreeHeight;
        this.distribution = distribution;
        this.forestSize = forestSize;
        this.startingPointX = startingPointX;
        this.startingPointY = startingPointY;
        this.radius = radius;
        this.speed = speed;
        this.random = new Random();

        System.out.println("starting simulation with parameters:");
        System.out.println("max tree height " + this.maxTreeHeight);
        System.out.println("distribution " + this.distribution);
        System.out.println("forest size " + this.forestSize);
        System.out.println("starting x " + this.startingPointX);
        System.out.println("starting y " + this.startingPointY);
        System.out.println("radius " + this.radius);
        System.out.println("speed " + this.speed);

        speedx = random.nextInt(this.speed - MenuScreen.MIN_SPEED + 1)+MenuScreen.MIN_SPEED;
        speedy = random.nextInt(this.speed - MenuScreen.MIN_SPEED + 1)+MenuScreen.MIN_SPEED;

        //affine2 = new Affine2();
        double s = Math.sqrt(speedx*speedx+speedy*speedy)/(radius*radius);
        vortex = new Rankine(radius, speedx, speedy, s, 0, new Rankine.Speeds());
        trees = new ArrayList<Tree>();

        vortex.setOrigin(0,0);

        batch = new SpriteBatch();
        treeTexture = new Texture(Gdx.files.internal("./tree.png"));
        vortexTexture = new Texture(Gdx.files.internal("./vortex.png"));
        textureRegion = new TextureRegion(vortexTexture);
        treeTextureRegion = new TextureRegion(treeTexture);

        cam = new OrthographicCamera(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
        cam.update();

        touchFlag = null;
        camPositionFlag = null;

        Gdx.input.setInputProcessor(stage);

        generateTrees();

        shapeRenderer = new ShapeRenderer();
        
        bgtex = new Texture(Gdx.files.internal("./texture.png"));
    }

    private void generateTrees(){
        int rowHeight = 50;//szerokosc jednego paska
        int sideBound = 30;//odleglosc od lewej i prawej krawedzi
        int minStep = 30;//min odleglosc miedzy rzewami
        int maxStep = 50;//max odleglosc miedzy rzewami

        for(int i=0, n=forestSize/rowHeight ;i<=n;++i) {
            int j=sideBound;
            while(j<forestSize-sideBound) {
                float height = random.nextInt(maxTreeHeight-MenuScreen.MIN_TREE_HEIGHT+1)+MenuScreen.MIN_TREE_HEIGHT;
                float x=j;
                float y=forestSize-i*rowHeight-random.nextInt(rowHeight)*.7f;
                Tree t = new Tree(
                        height*.5f,
                        x,
                        y,
                        height,
                        2*height,
                        height*50,
                        height*.02f,
                        height*.04f,
                        height/4);
                trees.add(t);
		        /*
		        Image treeActor = new Image(treeTexture);
		        treeActor.setX(x);
		        treeActor.setY(y);
		        stage.addActor(treeActor);*/
                j += random.nextInt(maxStep-minStep)+minStep;
            }
        }
    }

    private void updateVortex() {
        this.vortex.calculateNewCenter(speedx, speedy);
        this.vortex.currentRotation = (this.vortex.currentRotation - 10) % 360;
    }

    private void updateTrees(){
        /*int state = 0;
        for(int i=0;i<this.trees.size();++i) {
            Tree tree = trees.get(i);
            if (!tree.state()) continue;
            else {
                state = tree.calculateTreeForce(speed);
                if (state == 2)
                    tree.setFallen();
                else if (state == 1)
                    tree.setBroken();
            }
        }*/
        for(int i=0;i<this.trees.size();++i) {
            Tree t = trees.get(i);
            if (t.getState() != Tree.states.FALLEN) {
                double distance = Math.sqrt(Math.pow(t.getX() - this.vortex.getOrigin().getOrig_x(), 2)
                        + Math.pow(t.getY() - this.vortex.getOrigin().getOrig_y(), 2));
                if (distance <= this.radius) {
                    Rankine.Speeds speeds = this.vortex.calculateWind(t,distance);
                    t.calculateTreeForce(speeds.getVr());


                    //System.out.println(speeds.getVr());
                }
            }
        }
    }

    public static int treeCounter() {
        return trees.size();
    }

    public static Tree getTree(int i) {
        return trees.get(i);
    }

    @Override
    public void show() {
        Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void render(float delta) {
        updateVortex();
        updateTrees();
        handleInput();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        //rysowanieeeee
        Gdx.gl.glClearColor(0, 0, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        /*batch.draw(
    			bgtex, 
    			cam.position.x-Gdx.graphics.getWidth()/2, 
    			cam.position.y-Gdx.graphics.getHeight()/2, 
    			Gdx.graphics.getWidth(), 
    			Gdx.graphics.getHeight());*/
        for(float i=0 ; i<=Gdx.graphics.getWidth()+cam.viewportWidth*2 ; i=i+cam.viewportWidth) {
        	for(float j=0 ; j<=Gdx.graphics.getHeight()+cam.viewportHeight*2 ; j=j+cam.viewportHeight) {
        		batch.draw(
            			bgtex, 
            			i, 
            			j, 
            			cam.viewportWidth, 
            			cam.viewportHeight);
        	}
        }
        for(int i=0 ; i<trees.size() ; ++i) {
            Tree t = trees.get(i);
            float angle = 0;
            if (t.getState()== AbstractTreeModel.states.FALLEN)
                angle = 90;
            if (t.getState()== AbstractTreeModel.states.BROKEN)
                angle = 45;
            if(t.angle < t.destAngle) t.angle += 2.5f;
            this.batch.draw(treeTextureRegion, t.getX(), t.getY(), 0, 0,
                    20,35+8*t.getHeight(),1 ,1, t.angle*t.fallDirection);
            /*
                        if (t.isStanding()) {
                double distance = Math.sqrt(Math.pow(t.getX() - this.vortex.getOrigin().getOrig_x(), 2)
                        + Math.pow(t.getY() - this.vortex.getOrigin().getOrig_y(), 2));
                if (distance <= this.radius) {
//                    Rankine.Speeds speeds = this.vortex.calculateWind(t);
//                    int x = t.calculateTreeForce(speeds.getVr());
//                    if (x == 2)
                        t.setStanding(false);
//                    System.out.print(x+" ");
                }
            }
            * */
        }
        this.batch.draw(
                textureRegion,
                (float)this.vortex.getOrigin().getOrig_x(),
                (float)this.vortex.getOrigin().getOrig_y(),
                this.radius/2,
                this.radius/2,
                this.radius,
                this.radius,
                1,
                1,
                this.vortex.currentRotation
        );
        this.batch.end();

/*
        affine2.setToTranslation(new Vector2(
                (float)this.vortex.getOrigin().getOrig_x(),
                (float)this.vortex.getOrigin().getOrig_y()));
        affine2.setToRotation(this.vortex.currentRotation);
        this.batch.draw(
                new TextureRegion(vortexTexture),
                (float)this.radius,
                (float)this.radius,
                affine2);*/
    }

    private void handleInput() {
        if(Gdx.input.isTouched()) {
            if(touchFlag == null && camPositionFlag == null) {
                touchFlag = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                camPositionFlag = new Vector2(cam.position.x,cam.position.y);
            } else {
                float diffX = Gdx.input.getX()-touchFlag.x;
                float diffY = Gdx.input.getY()-touchFlag.y;

                float newX = camPositionFlag.x-diffX;
                if(newX < cam.viewportWidth/2) newX = cam.viewportWidth/2;
                if(newX > forestSize-cam.viewportWidth/2) newX = forestSize-cam.viewportWidth/2;

                float newY = camPositionFlag.y+diffY;
                if(newY < cam.viewportHeight/2) newY = cam.viewportHeight/2;
                if(newY > forestSize-cam.viewportHeight/2) newY = forestSize-cam.viewportHeight/2;

                cam.position.set(newX,newY,0);
            }
        } else {
            touchFlag = null;
            camPositionFlag = null;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()  {
        stage.dispose();
        treeTexture.dispose();
    }

    @Override
    public Stage getStage() {
        return stage;
    }



}