package miss;

/**
 * Implementacja modelu wiru
 */
public abstract class AbstractVortexModel {
    public class Origins{
        double orig_x;
        double orig_y;

        public double getOrig_x() {
            return orig_x;
        }

        public double getOrig_y() {
            return orig_y;
        }
    }
    protected Origins origin;

    public AbstractVortexModel() {
        origin = new Origins();
    }

    /**
     * przypisuje srodek tornada w ukladzie wspolrzednych
     * @param _orig_x
     * @param _orig_y
     */
    public void setOrigin(double _orig_x, double _orig_y){
        origin.orig_x = _orig_x;
        origin.orig_y = _orig_y;
    };

    /**
     * zwraca srodek tornada w ukladzie wspolrzednych
     */
    public Origins getOrigin(){
        return origin;
    };

    public void calculateNewCenter(double speedx, double speedy){
        setOrigin(getOrigin().orig_x + speedx, getOrigin().orig_y + speedy);
    }

}
