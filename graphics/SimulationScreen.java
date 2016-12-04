package MiSS.graphics;
import com.badlogic.gdx.scenes.scene2d.Stage;
import miss.Rankine;
import miss.Tree;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class SimulationScreen implements ScreenWithStage {

    private final Stage stage;
    private final Integer maxTreeHeight;
    private final Integer distribution;
    private final Integer forestSize;
    private final Integer startingPointX;
    private final Integer startingPointY;
    private final Integer radius;
    private final Integer speed;

    private ArrayList<Tree> trees;
    private Rankine vortex;
    private Random random;

    private final double speedx;
    private final double speedy;

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

        generateTrees();

    }

    private void generateTrees(){
        double x,y =0;///random

        //Tree t = new Tree(int crown_depth, double x, double y, int height, int dbh, int crown_mass, int stem_mass, int r_mass, int r_depth, int crown_width);
        /*
Dane do klasy Tree
wysokość = x [m]
średnica = x [cm]
masa korony = 2*x [kg]
wysokość korony = 0.5*x [m]
szerokość korony = 0.25*x [m]
masa pnia = x*50
masa korzenia = x*0.02
wysokosc korzenia r_depth = x*0.04
predkosc przewrocenia = 36 [m/s]
predkosc zlamania = 39 [m/s]*/
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

    }

    @Override
    public void render(float delta) {
        updateVortex();
        updateTrees();
        //rysowanieeeee
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

    }

    @Override
    public Stage getStage() {
        return stage;
    }



}
