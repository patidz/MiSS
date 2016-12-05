package MiSS;

/**
 * Implementacja modelu wiru
 */
public abstract class AbstractVortexModel {
    class Origins{
        double orig_x;
        double orig_y;
    }
    protected Origins origin;
    protected int X[];
    protected int Y[];
    protected int Z[];
    protected double vf; //predkosc translacji [m/s]
    protected double angle;
    private int[] speedMatrix;
    private int[] uMatrix;
    private int[] vMatrix;

    public AbstractVortexModel() {
        origin = new Origins();
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
     * @param _orig_x
     * @param _orig_y
     */
    void setOrigin(double _orig_x, double _orig_y){
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
     * wyliczenie miejsca nowego srodka tornada po uplywie czasu t [s]
     */
    /*7*/
    void calculateNewCenter(int t){
        double distance = getVf() * t;
        int distanceX = (int)(distance * Math.cos(getAngle()));
        int distanceY = (int)(distance * Math.sin(getAngle()));
        setOrigin(getOrigin().orig_x + distanceX, getOrigin().orig_y + distanceY);
    };

    public void calculateNewCenter(double speedx, double speedy){
        setOrigin(getOrigin().orig_x + speedx, getOrigin().orig_y + speedy);
    }


    /**
     * oblicza predkosc translacji tornada
     * @return
     */
    double getVf(){
        return vf;
    };
    double getAngle(){
        return angle;
    };
    void calculateWindSpeedMatrix() {}

}
