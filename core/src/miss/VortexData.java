package miss;

/**
 * Zarzadzanie klasami zwiazanymi z modelem wiru
 */
public class VortexData {
    protected AbstractVortexModel vortexModel;
    protected int[] speedMatrix;
    protected int[] uMatrix;
    protected int[] vMatrix;
    protected int x,y;

    public VortexData(){}

    /**
     * zmienia stan obiaktu poprzez wyliczenie macierzy predkosci i macierzy wiatru
     * po zmienia polozenia srodka tornada po czasie delataT
     */
    /*5*/
    void calculateWindSpeed(){

    }

    /**
     * metoda pomocnicza
     * @return macierz predkosci wiatru
     */
    /*8*/
    int[] getSpeedMatrix(){
        return speedMatrix;
    }

    /**
     * metoda pomocnicza
     * @return macierz wektora wiatru
     */
    /*9*/
    int[] getUMatrix(){
        return uMatrix;
    }

    /**
     * metoda pomocnicza
     * @return macierz wektora wiatru
     */
    /*10*/
    int[] getVMatrix(){
        return vMatrix;
    }

    /**
     * metoda pomocnicza
     * @return true if srodek tornada poza ukl wspolrzednych, false if srodek tornada w ukl wspolrzednych
     */
    boolean origOutOfBound(){
        return false;
    }

    //paint(){} // rysuje tornado 2D na wykresie
}
