package miss;

/**
 * Przeplyw informacji podczas trwania symulacji, przesylanie danych do interfejsu uzytkownika
 */

public class Simulation {
    protected VortexData vortexData;
    protected int x, y;

    void simulation(){}

    /**
     * obliczanie wynikowej macierzy lasu po przejsciu tornada
     * @param
     */
    /*3*/
    /*TreeData doSimulation(/* vortex_axis, forest_axis, broken_label, overtaken_label *){
        treeData.treeMatrix = TreeData.generateTreeMatrix();
        return treeData;
    }*/

    /**
     * ustawia parametry poczatkowe modelu wiru
     * @param vortexModel
     * @param spacing
     */
    /*2*/
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
    /*1*/
    void initializeForest(AbstractTreeModel treeModel, Tree treeType, int distribution, double maxTreeHeight){}
    //void initializeTree(){}

    void setGrid(int x, int y){
        this.x = x;
        this.y = y;
    }
}
