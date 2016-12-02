package MiSS;

/**
 * Implementacja modelu Rankine
 */
public class Rankine extends AbstractVortexModel {
    protected double R_max;
    protected double Vr_max;
    protected double Vt_max;
    protected Speeds speeds;

    public Rankine() {}

    @Override
    Speeds calculateWind(int treeX, int treeY) {
        double Vr=0, Vt=0;
        double r = Math.abs(getOrigin().orig_x - treeX)*Math.abs(getOrigin().orig_y - treeY)*0.5;
        if (r <= R_max){
            Vr = Vr_max * Math.pow((r/R_max),0.6);
            Vt = Vt_max * (r/R_max);
        } else if (r > R_max) {
            Vr = Vr_max * Math.pow((R_max/r),0.6);
            Vt = Vt_max * (R_max/r);
        }
        speeds.Vr = Vr;
        speeds.Vt = Vt;
        return speeds;
    }
    class Speeds{
        double Vr;
        double Vt;
    }
}
