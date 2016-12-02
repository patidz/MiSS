package MiSS;

/**
 * Struktura drzew Tree
 */
public class Tree { // HWINDData
    protected int height;   // wysokosc drzewa
    protected int dbh;  // srednica drzewa na wysokosci piersi cz�owieka (1.3 m) [m]
    protected static double MOR = 39.1;  // wspolczynnik pekania drewna [MPa]
    protected static double MOE = 7000;  // wspolczynnik elastycznosci [MPa]
    protected static double Cd = 0.29;   // wspolczynnik tarcia
    protected static double airDensity = 1.226;
    protected int Crown_mass;   // masa korony [kg]
    protected int Stem_mass;    // masa pnia [kg]
    protected static double f_RW = 0.3;   // stosunek wagi gleby wokol korzeni do masy calego drzewa w [%]
    protected int R_mass;   // masa korzeni [kg]
    protected int R_depth;  // glebokosc korzeni [m]
    protected int Crown_depth;  // wysokosc korony [m]
    protected int Crown_width;  // szerokosc korony [m]
    //protected Tree NorwaySpruces;

    public Tree() {

    }
}
