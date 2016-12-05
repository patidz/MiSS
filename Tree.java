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
    protected final float x;
    protected final float y;
    protected final float height;   // wysokosc drzewa
    protected final int dbh = 1;  // srednica drzewa na wysokosci piersi człowieka (1.3 m) [m]
    protected final float Crown_mass;   // masa korony [kg]
    protected final float Stem_mass;    // masa pnia [kg]
    protected final float R_mass;   // masa korzeni [kg]
    protected final float R_depth;  // glebokosc korzeni [m]
    protected final float Crown_depth;  // wysokosc korony [m]
    protected final float Crown_width;  // szerokosc korony [m]
    //protected miss.Tree ScotsPines;
    // miss.Tree NorwaySpruces;

    public Tree(float crown_depth, float x, float y, float height, float crown_mass, 
    		float stem_mass, float r_mass, float r_depth, float crown_width) {
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

    
    
}
