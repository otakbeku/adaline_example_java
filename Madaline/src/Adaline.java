import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kotak Hitam on 4/7/2017.
 */
public class Adaline {
    private double bias;
    private double deltaBias;
    private double alpha;
    private double net;
    private List<Double> bobot;
    private List<Double> deltaBobot;
    private double target;
    private double tolerance_value;
    private double error;

    public Adaline() {
        this.bobot = new ArrayList<>();
        this.deltaBobot = new ArrayList<>();
//        this.setBias(0.0);
//        this.setDeltaBias(0.0);
    }

    public Adaline(int jumlahBobot) {
//        this.setJumlahBobot(jumlahBobot);
        this.bobot = new ArrayList<>();
        this.deltaBobot = new ArrayList<>();
        this.setBobotAwalToZero(jumlahBobot);
        this.setBias(0.0);
        this.setDeltaBias(0.0);
    }

    //METHOD PELENGKAP

    //diganti dengan List, ga pakai array lagi
//    public void setJumlahBobot(int jumlahBobot) {
//        this.bobot = new double[jumlahBobot];
//    }

    public Double[] arrayOfPrimitivesToObjNumber(double[] primitives) {
        Double[] dObj = new Double[primitives.length];
        for (int i = 0; i < primitives.length; i++) {
            dObj[i] = new Double(primitives[i]);
        }

        return dObj;
    }

    public double[] arrayOfObjNumberToPrimitives(List<Double> listOfDouble) {
        double[] primDouble = new double[listOfDouble.size()];
        for (int i = 0; i < listOfDouble.size(); i++) {
            primDouble[i] = listOfDouble.get(i).doubleValue();
        }
        return primDouble;
    }

    public void setBobotAwalToZero(int jumlahBobot) {
//        this.bobot.forEach(b -> b = 0.0);
        for (int i = 0; i < jumlahBobot; i++) {
            this.bobot.add(0.0);
        }
    }

    //UPDATE BOBOT
    public void updateBobotByIndex(int index, Double inputXi) {
        this.bobot.set(index, (this.bobot.get(index) + (this.getAlpha() * (this.getTargetAsDoubleObj() - this.getNetAsDoubleObj()) * inputXi)));
    }

    public void updateBias() {
        this.setBias((this.getBias() + (this.getAlpha() * (this.getTargetAsDoubleObj() - this.getNetAsDoubleObj()))));
    }

    //SETTER AND GETTER

    public double[] getBobot() {
        double[] bobot = this.arrayOfObjNumberToPrimitives(this.bobot);
        return bobot;
    }

    public void setBobot(double[] bobot) {

        this.bobot = Arrays.asList(this.arrayOfPrimitivesToObjNumber(bobot));
    }


    public double[] getDeltaBobot() {
        double[] deltaBobot = this.arrayOfObjNumberToPrimitives(this.deltaBobot);
        return deltaBobot;
    }

    public void setDeltaBobot(double[] deltaBobot) {
        this.deltaBobot = Arrays.asList(this.arrayOfPrimitivesToObjNumber(deltaBobot));
    }

    public void setBias(double bias) {
        this.bias = bias;

    }

    public void setError() {
        this.error = Math.pow((this.target - this.net), 2);
    }

    public double getError() {
        return this.error;
    }

    public double getBias() {
        return bias;
    }

    public double getDeltaBias() {
        return deltaBias;
    }

    public void setDeltaBias(double deltaBias) {
        this.deltaBias = deltaBias;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getNet() {
        return net;
    }

    public Double getNetAsDoubleObj() {
        return net;
    }

    public void setNet(double[] inputXi) {
        Double net = 0.0;
        for (int i = 0; i < inputXi.length; i++) {
            net += this.bobot.get(i) * inputXi[i];
        }
        this.net = net;
    }

    public double getTarget() {
        return target;
    }

    public Double getTargetAsDoubleObj() {
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
}
