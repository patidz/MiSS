/**
 * Zarzadzanie klasami zwiazanymi z modelem lamliwosci drzew
 */
public class TreeData {
    protected AbstractTreeModel treeModel;
    protected int[] treeMatrix;
    protected Tree treeType;
    protected double maxTreeHeight;
    protected int distribution; // jednorodne lub losowe wygenerowanie lasu

    public TreeData() {};

    /**
     * generowanie macierzy poczatkowej skladajacej sie z drzew treeType wg opisu w klasie Tree
     * @return macierz poczatkowa reprezentujaca las
     */
    int[] generateTreeMatrix(){
        return null;
    }

    /**
     * obliczanie nowej macierzy stanu drzew po uzyciu metody AbstractTreeModel.calculateTreeForce(),
     * jezeli dane drzewo jest juz uszkodzone, obliczenia nie sa ponownie wykonywane
     * @return
     */
    int[] calculateTreeDamageMatrix(){
        return null;
    }

    /**
     * metoda pomocnicza
     * @return zwraca macierz wysokosci drzew w lesie
     */
    int[] getTreeHeightMatrix(){
        return null;
    }

    //paint(){} // rysowanie zniszczen na wykresie

    /**
     * metoda pomocnicza
     * zwraca macierz lasu wygenerowana przez metody generateTreeMatrix() i calculateTreeDamageMatrix()
     * @return
     */
    int[] getTreeMatrix(){
        return null;
    }

    /**
     * metoda pomocnicza
     * @return ilosc polamanych drzew w macierzy TreeMatrix
     */
    int getBrokenTrees(){
        return 0;
    }

    /**
     * metoda pomocnicza
     * @return ilosc wyrwanych drzew w macierzy treeMatrix
     */
    int getOvertakenTrees(){
        return 0;
    }

    /**
     * metoda pomocnicza
     * @return ilosc wszystkich drzew w macierzy treeMatrix
     */
    int getAllTrees(){
        return 0;
    }

}
