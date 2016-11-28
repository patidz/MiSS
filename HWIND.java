/**
 * Created by Patrycjaa on 2016-11-28.
 */
public class HWIND extends AbstractTreeModel{
    private double ro;
    private double k;
    private double g;
    private int spacing;
    private int gapSize;
    private double meanTreeHeight;

    /**
     *
     * @param treeHeights - wysokosci drzew w lesie (macierz X
     */
    void initialize(double[] treeHeights) {}

    /**
     *
     * @param tree - struktura opisujaca drzewo
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    @Override
    public double calculateTreeForce(Tree tree, double speed, int treeI, int treeJ){
        return 0;
    }

    /**
     *
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return odleglosc drzewa od brzegu macierzy X
     */
    double calculateDistanceFromForestEdge(int treeI, int treeJ){
        return 0;
    }

}
