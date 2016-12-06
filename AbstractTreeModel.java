package MiSS;

/**
 * Implementacja modeli lamliwosci drzew
 */
public abstract class AbstractTreeModel {
    protected int X[];
    protected int Y[];

    public AbstractTreeModel() {};

    /**
     * metoda obliczajaca czy predkosc wiatru jest wystarczajaca do przewrocenia drzewa
     * @param tree - struktura opisujaca drzewo
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    public abstract double calculateTreeForce(double speed);

    /**
     * metoda zwracajaca macierze ukladu odniesienia
     * @return (X,Y) ?
     */
    int getXY(){
        return 0;
    }
}
