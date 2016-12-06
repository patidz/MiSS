package MiSS;
import java.lang.*;
public class Rankine extends AbstractVortexModel {
    protected final double Vx;
    protected final double Vy;
    protected final double R_max;
    protected final double Vr_max;
    protected final double Vt_max;
    protected final Speeds speeds;

    public Rankine(double r_max, double vx, double vy, double vr_max, double vt_max, Speeds speeds) {
        R_max = r_max;
        Vx = vx;
        Vy = vy;
        Vr_max = vr_max;
        Vt_max = vt_max;
        this.speeds = speeds;
    }

    @Override
    /*6*/
    public Speeds calculateWind(Tree tree) {
        double Vr=0, Vt=0;
        double r = Math.abs(getOrigin().orig_x - tree.getX())*Math.abs(getOrigin().orig_y - tree.getY())*0.5;
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

    public static class Speeds{
        double Vr;
        double Vt;
    }
}
