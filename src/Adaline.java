/**
 * Created by Kotak Hitam on 4/2/2017.
 */
public class Adaline {
    private double bias, w1, w2;
    private double Dbias, Dw1, Dw2;
    private double y_in;
    private double alpha;
    private double error;
    private double net;
    private double tolerance_value;
    private double target;
    private double[] bobot;


//    public double updateY_in(double x1, double x2, double w1, double w2) {
//
//    }

    public Adaline() {
    }

    public void setBobotAwal(double bobotAwal) {
        this.bias = bobotAwal;
        this.w1 = bobotAwal;
        this.w2 = bobotAwal;
    }

    public void setBobotAwal(double[] bobotAwal) {
        this.bobot = bobotAwal;
    }

    public double getBobotByIndex(int index) {
        return this.bobot[index];
    }

    public void updateAllBobot(double[] inputXi) {
        for (int i = 0; i < this.bobot.length; i++) {
            this.bobot[i] = this.bobot[i] + (this.alpha * (1 - this.getY_in()) * inputXi[i]);
        }
    }

    public void updateBobot(double inputXi, int index) {
        this.bobot[index] = this.bobot[index] + (this.alpha * (1 - this.getY_in()) * inputXi);
    }

    public void setLearningRate(double learningRate) {
        this.alpha = learningRate;
    }

    public void setError(double target, double y_in) {
        this.error = Math.pow((target - y_in), 2);
    }

    public double getError() {
        return error;
    }

    public void setNet(double XiWi, double bias) {
        this.net = XiWi + bias;
        // FUNGSI IDENTITAS
        this.y_in = net;
    }

    public double getY_in() {
        return y_in;
    }

    public double getTolerance_value() {
        return tolerance_value;
    }

    public void setTolerance_value(double tolerance_value) {
        this.tolerance_value = tolerance_value;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getDbias() {
        return Dbias;
    }

    public void setDbias(double target, double y_in) {
        Dbias = this.alpha * (target - y_in);
    }

    public double getDw1() {
        return Dw1;
    }

    public void setDw1(double target, double x1, double y_in) {
        this.Dw1 = this.alpha * (target - y_in) * x1;
    }

    public double getDw2() {
        return Dw2;
    }

    public void setDw2(double target, double x2, double y_in) {
        this.Dw2 = this.alpha * (target - y_in) * x2;
    }


    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public void updateW1(double target, double y_in, double x1, double w1) {
        this.w1 = w1 + (this.alpha * (target - y_in) * x1);
    }

    public void updateW2(double target, double y_in, double x2, double w2) {
        this.w2 = w2 + (this.alpha * (target - y_in) * x2);
    }

    public void updateBias(double bias, double target, double y_in) {
        this.bias = bias + (this.alpha * (target - y_in));
    }

    public double DeltaRuleForUpdateWeight1(double alpha, double error, double Xi) {
        return alpha * error * Xi;
    }

    public double DeltaRuleForUpdateWeight2(double alpha, double error, double Xi) {
        return alpha * error * Xi;
    }


    public double getMaxWeight() {
        return Math.max(this.Dw1, this.Dw2);
    }

}
