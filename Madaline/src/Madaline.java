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
    private double yInput;
    private double threshold;


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
    public void setVBobotAndVBias(double value) {
        this.vBobotMadaline = new double[this.adalineList.size()];
        for (int i = 0; i < this.adalineList.size(); i++) {
            this.vBobotMadaline[i] = value;
        }
        this.vBiasMadaline = value;

    }

    public void setNetEachAdaline(double[] xInput) {
        for (int i = 0; i < this.adalineList.size(); i++) {
            this.adalineList.get(i).setNet(xInput);
        }
    }

    public double getY() {
        double y = 0;
        if (this.getyInput() >= this.threshold) {
            y = 1;
        } else if (this.getyInput() < this.threshold) {
            y = -1;
        }
        return y;
    }

    public void updateBobotAndBiasEachAdaline(double[] xInput) {
        if (getY() - this.target > 0) {
            if (this.target == 1) {
                for (int i = 0; i < this.adalineList.size(); i++) {
//                System.out.println("Dalem: "+i);
                    for (int j = 0; j < xInput.length; j++) {
//                    System.out.println("Dalemdalem: "+j);
                        this.adalineList.get(i).updateBobotPLUSByIndex(j, xInput[j]);
                    }
                    this.adalineList.get(i).updateBiasPLUS();
                }
            } else if (this.target == -1) {
                for (int i = 0; i < this.adalineList.size(); i++) {
                    for (int j = 0; j < xInput.length; j++) {
                        this.adalineList.get(i).updateBobotMINUSByIndex(j, xInput[j]);
                    }
                    this.adalineList.get(i).updateBiasMINUS();
                }
            }
        }
    }

    public double getDeltaBobotAdalineByIndex(int indexAdaline, int indexDeltaBobot) {
//        double bobotAdaline = this.adalineList.get(indexAdaline).getDeltaBobotByIndex(indexDeltaBobot);
        return this.adalineList.get(indexAdaline).getDeltaBobotByIndex(indexDeltaBobot);
    }

    public double getBobotAdalineByIndex(int indexAdaline, int indexDeltaBobot) {
//        double bobotAdaline = this.adalineList.get(indexAdaline).getBobotByIndex(indexDeltaBobot);
        return this.adalineList.get(indexAdaline).getBobotByIndex(indexDeltaBobot);
    }

    public double getBiasAdalineByIndex(int indexAdaline) {
//        double bias = this.adalineList.get(indexAdaline).getBias();
        return this.adalineList.get(indexAdaline).getBias();
    }
    public double getDeltaBiasAdalineByIndex(int indexAdaline) {
//        double bias = this.adalineList.get(indexAdaline).getBias();
        return this.adalineList.get(indexAdaline).getDeltaBias();
    }

    public void setYInputMadaline() {
        for (int i = 0; i < this.adalineList.size(); i++) {
            this.yInput = this.adalineList.get(i).getNet() * this.vBobotMadaline[i];
        }
        this.yInput += yInput + this.vBiasMadaline;
    }

    public double getyInput() {
        return this.yInput;
    }

    public void setTargetEachAdaline(double target) {
        for (int i = 0; i < this.adalineList.size(); i++) {
            this.adalineList.get(i).setTarget(target);
        }
    }

    public double getNetFromAdalineByIndex(int index) {
//        double net = this.adalineList.get(index).getNet();
        return this.adalineList.get(index).getNet();
    }


    //SETTER AND GETTER

    public void setyInput(double yInput) {
        this.yInput = yInput;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public List<Adaline> getAdalineList() {
        return adalineList;
    }

    public void setAdalineList(List<Adaline> adalineList) {
        this.adalineList = adalineList;
    }

    public double[] getvBobotMadaline() {
        return vBobotMadaline;
    }

    public double getvBobotMadalineByIndex(int indexBobot) {
        return vBobotMadaline[indexBobot];
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
        this.setTargetEachAdaline(this.target);
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
        for (int i = 0; i < this.adalineList.size(); i++) {
            this.adalineList.get(i).setAlpha(alpha);
        }
    }

}
