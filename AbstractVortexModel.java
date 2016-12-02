package MiSS;

/**
 * Implementacja modelu wiru
 */
public abstract class AbstractVortexModel {
    class Origins{
        int orig_x;
        int orig_y;
    }
    protected Origins origin;
    protected int X[];
    protected int Y[];
    protected int Z[];
    protected double vf; //predkosc translacji [m/s]
    protected double angle;
    private int[] speedMatrix;
    //private int[] uMatrix;
    //private int[] vMatrix;
    //private int[] wMatrix;

    public AbstractVortexModel() {
        //origin = new Origins();
    }

    /**
     * oblicza macierz predkosci (speedMatrix) i macierze wektorow wiatru (uMatrix i vMatrix)
     * na podstawie aktualnego stanu klasy
     */
    abstract Rankine.Speeds calculateWind(int treeX, int treeY);

    void setX(int[] _X){};
    void setY(int[] _Y){};
    void setZ(int[] _Z){};

    int[] getX(){
        return X;
    };
    int[] getY(){
        return Y;
    };
    int[] getZ(){
        return Z;
    };

    /**
     * przypisuje srodek tornada w ukladzie wspolrzednych
     */
    void setOrigin(int _orig_x, int _orig_y){
        origin.orig_x = _orig_x;
        origin.orig_y = _orig_y;
    };

    /**
     * zwraca srodek tornada w ukladzie wspolrzednych
     */
    Origins getOrigin(){
        return origin;
    };

    /**
     * wyliczenie miejsca nowego srodka tornada po uplywie czasu t
     */
   Origins calculateNewCenter(int t){
        double distance = getVf() * t;
        int distanceX = (int)(distance * Math.cos(getAngle()));
        int distanceY = (int)(distance * Math.sin(getAngle()));
        setOrigin(getOrigin().orig_x + distanceX, getOrigin().orig_y + distanceY);
        return origin;
    };

    /**
     * oblicza predkosc translacji tornada
     * @return
     */
    double getVf(){
        return 0;
    };
    
    double getAngle(){
        return angle;
    };
    
    //void calculateWindSpeedMatrix() {} //??

}
