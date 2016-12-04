package MiSS;

/**
 * Struktura drzew MiSS.miss.Tree
 */
public class Tree extends AbstractTreeModel{ // HWINDData
    protected final double MOR = 39.1;  // wspolczynnik pekania drewna [MPa]
    protected final double MOE = 7000;  // wspolczynnik elastycznosci [MPa]
    protected final double Cd = 0.29;   // wspolczynnik tarcia
    protected final double airDensity = 1.226;
    protected final double f_RW = 0.3;   // stosunek wagi gleby wokol korzeni do masy calego drzewa w [%]

    protected boolean standing;
    protected final double x;
    protected final double y;
    protected final int height;   // wysokosc drzewa
    protected final int dbh = 1;  // srednica drzewa na wysokosci piersi człowieka (1.3 m) [m]
    protected final int Crown_mass;   // masa korony [kg]
    protected final int Stem_mass;    // masa pnia [kg]
    protected final int R_mass;   // masa korzeni [kg]
    protected final int R_depth;  // glebokosc korzeni [m]
    protected final int Crown_depth;  // wysokosc korony [m]
    protected final int Crown_width;  // szerokosc korony [m]
    //protected miss.Tree ScotsPines;
    // miss.Tree NorwaySpruces;

    public Tree(int crown_depth, double x, double y, int height, int crown_mass, int stem_mass, int r_mass, int r_depth, int crown_width) {
        Crown_depth = crown_depth;
        this.x = x;
        this.y = y;
        this.height = height;
        Crown_mass = crown_mass;
        Stem_mass = stem_mass;
        R_mass = r_mass;
        R_depth = r_depth;
        Crown_width = crown_width;
        standing = true;
    }

    /**
     *
     * @param tree - struktura opisujaca drzewo
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    @Override

    public double calculateTreeForce(Tree tree, double speed, int treeI, int treeJ){ //speedMatrix
        return 0;
    }

}
