package miss;

import com.mygdx.game.SimulationScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Struktura drzew MiSS.miss.Tree
 */
public class Tree extends AbstractTreeModel{ // HWINDData

    protected final double MOR = 39.1;  // wspolczynnik pekania drewna [MPa]
    protected final double MOE = 7000.0;  // wspolczynnik elastycznosci [MPa]
    protected final double Cd = 0.29;   // wspolczynnik tarcia
    protected final double airDensity = 1.226;
    protected final double f_RW = 0.3;   // stosunek wagi gleby wokol korzeni do masy calego drzewa w [%]
    protected final double g = 9.81;

    protected Tree.states state;         // TODO rozroznienie na broken i fallen
    protected final float x;
    protected final float y;
    protected final float height;   // wysokosc drzewa
    protected final float dbh;  // srednica drzewa na wysokosci piersi cz�owieka (1.3 m) [m]
    protected final float Crown_mass;   // masa korony [kg]
    protected final float Stem_mass;    // masa pnia [kg]
    protected final float R_mass;   // masa korzeni [kg]
    protected final float R_depth;  // glebokosc korzeni [m]
    protected final float Crown_depth;  // wysokosc korony [m]
    protected final float Crown_width;  // szerokosc korony [m]
    public float angle = 0;
    public float destAngle;
    private Random random = new Random();
    public final int fallDirection = (random.nextBoolean()) ? 1 : -1 ;
    //protected miss.Tree ScotsPines;
    // miss.Tree NorwaySpruces;

    public Tree(float crown_depth, float x, float y, float height, float radius, float crown_mass, float stem_mass, float r_mass, float r_depth, float crown_width) {
        Crown_depth = crown_depth;
        this.x = x;
        this.y = y;
        this.height = height;
        dbh = radius;
        Crown_mass = crown_mass;
        Stem_mass = stem_mass;
        R_mass = r_mass;
        R_depth = r_depth;
        Crown_width = crown_width;
        state= states.STANDING;
    }

    /**
     *
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    @Override

    public void calculateTreeForce( double speed){
        double totalBendingMoment = Math.abs(totalBendingMoment(this, speed));
        double rootResistance = rootResistance(this);
        double stemResistance = stemResistance(this);

        String s = "";

        if (totalBendingMoment > stemResistance) {
            state =  states.BROKEN;
            this.destAngle = 45;
            s = "B";

            if (totalBendingMoment > rootResistance) {
                state =  states.FALLEN;
                this.destAngle = 90;
                s = "F";
            }
        } else {
            state =  states.STANDING;
            s = " ";
        }
        String result = String.format(" %.3f > %.3f > %.3f", totalBendingMoment, rootResistance, stemResistance);
        System.out.println(s + result);
    }

    public states getState() {
        return state;
    }

    public void setState(states state) {
        this.state = state;
    }

    private double totalBendingMoment(Tree tree, double speed) {
        double total = 0;
        for (int i = 0; i < height; ++i) {
            double gustF = gustFactor(tree);
            double gapF = gapFactor();
            double vWSpeed = verticalWindSpeed(tree, i, speed);
            double gravF = gravityForce(tree, i);
            double h = horizontalDisplacement(tree, i);
            double p = (vWSpeed * 1 + gravF * h);
            total += gustF * gapF * p;
        }
        return total;
    }

    private double verticalWindSpeed(Tree tree, int i, double speed) {
        double Fw = 0;
        double St = 0.044444*speed - 0.28889;
        Fw += 0.5 * Cd * airDensity * Math.pow(speed, 2) * HWIND.triangleSectorArea(tree, i) * St;
        return Fw;
    }

    private double gravityForce(Tree tree, int i) {
        return tree.Crown_mass*g*(HWIND.triangleSectorArea(tree, i)/treeArea());
    }

    private double rootResistance(Tree tree) {
        return (g*tree.R_mass*tree.R_depth)/f_RW;
    }

    private double stemResistance(Tree tree) {
        return (Math.PI/32.0)*MOR*Math.pow(tree.dbh,3);
    }

    private double gapFactor() {    //15m
        double gap_mean = (0.001+0.001*Math.pow(15,0.562))/0.00465;
        double gap_max = (0.0072+0.0064*Math.pow(15,0.3467))/0.0214;
        return gap_max/gap_mean;
    }

    private double gustFactor(Tree tree) { //gap=1m
        double s = 1.0;
        double h = meanTreeHeight();
        double sh = s/h;
        double x = horizontalDisplacement(tree);
        double pow = x/h;

        double gme1 = (0.68*sh - 0.0385);
        double gme2 = (-0.68*sh+0.4875);
        double gme3b = (1.7239*sh+0.0316);
        double gme3p = Math.pow(gme3b,pow);
        double gust_mean = gme1 + gme2 * gme3p;

        double gma1 = (2.7193*sh - 0.061);
        double gma2 = (-1.273*sh+9.9701);
        double gma3b = (1.1127*sh+0.0311);
        double gma3p = Math.pow(gma3b,pow);
        double gust_max = gma1 + gma2 * gma3p;

        return gust_max/gust_mean;
    }

    private double horizontalDisplacement(Tree tree, int z) {
        double a = height - Crown_depth/2;
        double b = Crown_depth/2;
        double lz = height - z;
        double lb = lz-b;
        double I = Math.PI*Math.pow(dbh,4)/64;
        double vWSpeed = verticalWindSpeed(tree, z, 10);
        double denominator = (6*MOE*I);

        double hd;
        if (z <= a) {
            double a2h = Math.pow(a,2) * height;
            double ah = a/height;
            double l3zh = 3*lz/height;
            double p = (3 - ah - l3zh);
            hd = (vWSpeed * a2h * p) / denominator;
        } else {
            double a3 = Math.pow(a,3);
            double lb3a = 3*lb/a;
            double lb3 = Math.pow(lb,3);
            double pp = lb3/a3;
            double p = (2 - lb3a + pp);
            hd = (vWSpeed * a3 * p) / denominator;
        }
        return hd;
    }

    private double horizontalDisplacement(Tree tree) {
        double ret = 0;
        for (int z = 0; z < (int)height; ++z) {
            ret += horizontalDisplacement(tree, z);
        }
        return ret;
    }

    private double meanTreeHeight () {
        double meanTreeHeight = 0;
        double count = SimulationScreen.treeCounter();
        for (int i = 0; i < count; ++i) {
            meanTreeHeight += SimulationScreen.getTree(i).height;
        }
        return meanTreeHeight/count;
    }

    private double treeArea() {
        double area = 0;
        for (int i = 0; i <= height; i++) {
            area += HWIND.triangleSectorArea(this, i);
        }
        return area;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getHeight() {
        return height;
    }
}
