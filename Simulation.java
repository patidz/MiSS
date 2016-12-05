package MiSS;

/**
 * Przeplyw informacji podczas trwania symulacji, przesylanie danych do interfejsu uzytkownika
 */
public class Simulation {
    protected VortexData vortexData;
    //protected TreeData treeData;

    void simulation(){}

    /**
     * obliczanie wynikowej macierzy lasu po przejsciu tornada
     * @param
     */
    /*TreeData doSimulation(/* vortex_axis, forest_axis, broken_label, overtaken_label *){
        return treeData;
    }*/

    /**
     * ustawia parametry poczatkowe modelu wiru
     * @param vortexModel
     * @param spacing
     */
    void initializeVortex(AbstractVortexModel vortexModel, int spacing){}

    /**
     * ustawia parametry poczatkowe modelu lamliwosci drzew
     * @param treeModel
     * @param treeType
     * @param distribution sposob losowania wielkosci i grubosci drzew w lesie
     *                     1 - rozne srednice i wysokosc = maxTreeHeight
     *                     0 - rozne srednice i wysokosc < maxTreeHeight
     * @param maxTreeHeight maksymalna wysokosc drzew
     */
    void initializeForest(AbstractTreeModel treeModel, Tree treeType, int distribution, double maxTreeHeight){}
    void setGrid(){}
}
