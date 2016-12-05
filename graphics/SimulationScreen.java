package MiSS.graphics;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import MiSS.Rankine;
import MiSS.Tree;

public class SimulationScreen implements ScreenWithStage {

    private final Stage stage;
    private final Integer maxTreeHeight;
    private final Integer distribution;
    private final Integer forestSize;
    private final Integer startingPointX;
    private final Integer startingPointY;
    private final Integer radius;
    private final Integer speed;

    private final ArrayList<Tree> trees;
    private final Rankine vortex;
    private final Random random;

    private final double speedx;
    private final double speedy;
    
	private final SpriteBatch batch;
	private final Texture treeTexture;
	Texture tex = new Texture(Gdx.files.internal("./forest.jpg"));

	private Vector2 touchFlag;
	private Vector2 camPositionFlag;
	
	private final OrthographicCamera cam;

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

        speedx = random.nextInt(this.speed - MenuScreen.MIN_SPEED)+MenuScreen.MIN_SPEED;
        speedy = random.nextInt(this.speed - MenuScreen.MIN_SPEED)+MenuScreen.MIN_SPEED;

        vortex = new Rankine(radius, speedx, speedy, 0, 0, null);
        trees = new ArrayList<Tree>();

		batch = new SpriteBatch();
		treeTexture = new Texture(Gdx.files.internal("./tree.png"));
		
		cam = new OrthographicCamera(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
		cam.update();
		
		touchFlag = null;
		camPositionFlag = null;
		
		Gdx.input.setInputProcessor(stage);
		
        generateTrees();

    }

    private void generateTrees(){
    	int rowHeight = 50;//szerokosc jednego paska
    	int sideBound = 30;//odleglosc od lewej i prawej krawedzi
    	int minStep = 30;//min odleglosc miedzy rzewami
    	int maxStep = 50;//max odleglosc miedzy rzewami
    	
    	for(int i=0, n=forestSize/rowHeight ;i<=n;++i) {
    		int j=sideBound;
    		while(j<forestSize-sideBound) {
		    	float height = random.nextInt(maxTreeHeight-MenuScreen.MIN_TREE_HEIGHT)+MenuScreen.MIN_TREE_HEIGHT;
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

    private void updateVortex(){
        this.vortex.calculateNewCenter(speedx, speedy);
    }

    private void updateTrees(){
        for(int i=0;i<this.trees.size();++i) {
            trees.get(i);//
        }
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
		for(int i=0 ; i<trees.size() ; ++i) {
			Tree t = trees.get(i);
			this.batch.draw(treeTexture, t.getX(), t.getY());
		}
		this.batch.end();
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
    public void dispose() {
    	stage.dispose();
    	treeTexture.dispose();
    }

    @Override
    public Stage getStage() {
        return stage;
    }

}
