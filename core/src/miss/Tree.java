package miss;

import com.mygdx.game.SimulationScreen;

import java.util.ArrayList;

/**
 * Struktura drzew MiSS.miss.Tree
 */
public class Tree extends AbstractTreeModel{ // HWINDData

    protected final double MOR = 39.1;  // wspolczynnik pekania drewna [MPa]
    protected final double MOE = 7000;  // wspolczynnik elastycznosci [MPa]
    protected final double Cd = 0.29;   // wspolczynnik tarcia
    protected final double airDensity = 1.226;
    protected final double f_RW = 0.3;   // stosunek wagi gleby wokol korzeni do masy calego drzewa w [%]
    protected final double g = 9.81;

    protected Tree.states state;         // TODO rozroznienie na broken i fallen
    protected final float x;
    protected final float y;
    protected final float height;   // wysokosc drzewa
    protected final int dbh = 1;  // srednica drzewa na wysokosci piersi cz�owieka (1.3 m) [m]
    protected final float Crown_mass;   // masa korony [kg]
    protected final float Stem_mass;    // masa pnia [kg]
    protected final float R_mass;   // masa korzeni [kg]
    protected final float R_depth;  // glebokosc korzeni [m]
    protected final float Crown_depth;  // wysokosc korony [m]
    protected final float Crown_width;  // szerokosc korony [m]
    //protected miss.Tree ScotsPines;
    // miss.Tree NorwaySpruces;

    public Tree(float crown_depth, float x, float y, float height, float crown_mass, float stem_mass, float r_mass, float r_depth, float crown_width) {
        Crown_depth = crown_depth;
        this.x = x;
        this.y = y;
        this.height = height;
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
        double rootResistance = rootResistance(this)*10;
        double stemResistance = stemResistance(this);
        System.out.println(totalBendingMoment+" > "+ rootResistance);
        if (totalBendingMoment > rootResistance)
            state =  states.FALLEN;
        else if (totalBendingMoment > stemResistance)
            state =  states.BROKEN;
        else
            state =  states.STANDING;
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
            total += gustFactor(tree) * gapFactor() * (verticalWindSpeed(tree, i, speed) * 1 + gravityForce(tree, i)*horizontalDisplacement(tree, i));
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
        return (Math.PI/32)*MOR*Math.pow(tree.dbh,3);
    }

    private double gapFactor() {    //15m
        double gap_mean = (0.001+0.001*Math.pow(15,0.562))/0.00465;
        double gap_max = (0.0072+0.0064*Math.pow(15,0.3467))/0.0214;
        return gap_max/gap_mean;
    }

    private double gustFactor(Tree tree) { //gap=1m
        double gust_mean = (0.68*(1/meanTreeHeight()) - 0.0385) + (-0.68*(1/meanTreeHeight())+0.4875)*
                Math.pow(1.7239*(1/meanTreeHeight()+0.0316),horizontalDisplacement2(tree)/meanTreeHeight());
        double gust_max = (2.7193*(1/meanTreeHeight()) - 0.061) + (-1.273*(1/meanTreeHeight())+9.9701)*
                Math.pow(1.1127*(1/meanTreeHeight()+0.0311),horizontalDisplacement2(tree)/meanTreeHeight());
        return gust_max/gust_mean;
    }

    private double horizontalDisplacement(Tree tree, int z) {
        double a = height - Crown_depth/2;
        double b = Crown_depth/2;
        double l = height - z;
        double I = Math.PI*Math.pow(dbh,4)/64;
        if (z <= a) {
            return (verticalWindSpeed(tree, z, 10)*a*a*height*(3-(a/height)-3*l/height))/(6*MOE*I);
        }
        else if (z > a) {
            return (verticalWindSpeed(tree, z, 10)*a*a*a*(2-3*(l-b)/a+Math.pow(l-b,3)/Math.pow(a,3)))/(6*MOE*I);
        }
        return 0;
    }

    private double horizontalDisplacement2(Tree tree) {
        double ret = 0;
        for (int z = 0; z < (int)height; ++z) {
            double a = height - Crown_depth / 2;
            double b = Crown_depth / 2;
            double l = height - z;
            double I = Math.PI * Math.pow(dbh, 4) / 64;
            if (z <= a) {
                ret =+ (verticalWindSpeed(tree, z, 10) * a * a * height * (3 - (a / height) - 3 * l / height)) / (6 * MOE * I);
            } else if (z > a) {
                ret += (verticalWindSpeed(tree, z, 10) * a * a * a * (2 - 3 * (l - b) / a + Math.pow(l - b, 3) / Math.pow(a, 3))) / (6 * MOE * I);
            }
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
