package miss;

/**
 * Implementacja modeli lamliwosci drzew
 */
public abstract class AbstractTreeModel {

    public enum states {STANDING, BROKEN, FALLEN};
    public AbstractTreeModel() {};

    /**
     * metoda obliczajaca czy predkosc wiatru jest wystarczajaca do przewrocenia drzewa
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    public abstract void calculateTreeForce(double speed);

    /**
     * metoda zwracajaca macierze ukladu odniesienia
     * @return (X,Y) ?
     */
    int getXY(){
        return 0;
    }
}
