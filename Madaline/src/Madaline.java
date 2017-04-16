import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotak Hitam on 4/7/2017.
 */
public class Madaline {
    private List<Adaline> adalineList;
    private double[] vBobotMadaline;
    private double vBiasMadaline;
    private double target;
    private double tolerance_value;
    private double error;
    private double alpha;


    //CONSTRUCTOR
    public Madaline() {

    }

    public Madaline(int jumlahData, int jumlahNodeAdaline) {
        this.adalineList = new ArrayList<>();
        for (int i = 0; i < jumlahNodeAdaline; i++) {
            this.adalineList.add(i, new Adaline(jumlahData));
        }
    }

    //METHOD PELENGKAP
    public void setVBobotAndVBias(double value, int jumlahNodeAdaline) {
        this.vBobotMadaline = new double[jumlahNodeAdaline];
        for (int i = 0; i < jumlahNodeAdaline; i++) {
            this.vBobotMadaline[i] = value;
        }
        this.vBiasMadaline = value;

    }

    //SETTER AND GETTER
    public List<Adaline> getAdalineList() {
        return adalineList;
    }

    public void setAdalineList(List<Adaline> adalineList) {
        this.adalineList = adalineList;
    }

    public double[] getvBobotMadaline() {
        return vBobotMadaline;
    }

    public void setvBobotMadaline(double[] vBobotMadaline) {
        this.vBobotMadaline = vBobotMadaline;
    }

    public double getvBiasMadaline() {
        return vBiasMadaline;
    }

    public void setvBiasMadaline(double vBiasMadaline) {
        this.vBiasMadaline = vBiasMadaline;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getTolerance_value() {
        return tolerance_value;
    }

    public void setTolerance_value(double tolerance_value) {
        this.tolerance_value = tolerance_value;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
