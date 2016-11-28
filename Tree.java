/**
 * Struktura drzew Tree
 */
public class Tree { // HWINDData
    protected int height;   // wysokosc drzewa
    protected int dbh;  // srednica drzewa na wysokosci piersi cz³owieka (1.3 m) [m]
    protected int MOR;  // wspolczynnik pekania drewna [MPa]
    protected int MOE;  // wspolczynnik elastycznosci [MPa]
    protected int Cd;   // wspolczynnik tarcia
    protected int Crown_mass;   // masa korony [kg]
    protected int Stem_mass;    // masa pnia [kg]
    protected short f_RW;   // stosunek wagi gleby wokol korzeni do masy calego drzewa w [%]
    protected int R_mass;   // masa korzeni [kg]
    protected int R_depth;  // glebokosc korzeni [m]
    protected int Crown_depth;  // wysokosc korony [m]
    protected int Crown_width;  // szerokosc korony [m]
    protected Tree ScotsPines;
    protected Tree NorwaySpruces;

    public Tree() {

    }
}
