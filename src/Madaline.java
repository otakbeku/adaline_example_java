import java.util.Random;

/**
 * Created by Kotak Hitam on 4/2/2017.
 */
public class Madaline {
    private double[] input;
    private double[] target;
    private double[][] bobot = new double[3][27];
    private double[][] deltaBobot = new double[3][27];
    private double[] bias = new double[3];
    private double[] deltaBias = new double[3];
    private int jumlahData;
    private Adaline[] hiddenLayer = new Adaline[3];
    private double toleransi;
    private int batasIterasi;
    private double XiWi1 = 0;
    private double XiWi2 = 0;
    private double XiWi3 = 0;
    private double y_in;
    private double y;
    private double biasY, v1, v2, v3;
    private double alpha;
    private double error;
    Random random = new Random();

    public Madaline() {
        for (int i = 0; i < 3; i++) {
            hiddenLayer[i] = new Adaline();
        }
    }

    public void generateRandomWeight() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 27; j++) {
                this.bobot[i][j] = random.nextDouble();
            }
        }
        this.deltaBobot = this.bobot;
    }

    public double getWeightwithIndex(int hiddenLayer, int index) {
        System.out.println("bobot: "+this.bobot[hiddenLayer][index]);
        return this.bobot[hiddenLayer][index];
    }


    public void generateRandomBiasValueForHiddenLayer() {
        for (int i = 0; i < this.bias.length; i++) {
            this.bias[i] = random.nextDouble();
        }
        this.deltaBias = this.bias;
    }

    public double getBiasWithIndex(int hiddenLayer) {
        return this.bias[hiddenLayer];
    }

    public double getBobot1ByIndex(int index) {
        return this.bobot[0][index];
    }

    public double getBobot2ByIndex(int index) {
        return this.bobot[1][index];
    }

    public double getBobot3ByIndex(int index) {
        return this.bobot[2][index];
    }

    public double getNet1() {
        for (int j = 0; j < 9; j++) {
            this.XiWi1 += this.bobot[0][j] * this.input[j];
        }
//        return 0;
        return this.XiWi1;
    }

    public double getNet2() {
        for (int j = 0; j < 9; j++) {
            this.XiWi2 += this.bobot[1][j] * this.input[j];
        }
//        return 0;
        return this.XiWi2;
    }

    public double getNet3() {
        for (int j = 0; j < 9; j++) {
            this.XiWi3 += this.bobot[2][j] * this.input[j];
        }
//        return 0;
        return this.XiWi3;
    }

    public void setHiddenLayer1(double bias, double net1) {
        this.hiddenLayer[0].setNet(net1, bias);
    }

    public void setHiddenLayer2(double bias, double net2) {
        this.hiddenLayer[1].setNet(net2, bias);
    }

    public void setHiddenLayer3(double bias, double net3) {
        this.hiddenLayer[2].setNet(net3, bias);
    }

    public void updateWeightHiddenLayer1(int indexJ, double error, double Xij) {
        this.bobot[0][indexJ] = this.bobot[0][indexJ] + this.alpha * (this.target[indexJ] - this.hiddenLayer[0].getY_in()) * Xij;
    }


    public int getIndexClosestTo0() {
        double[] array = {this.hiddenLayer[0].getY_in(), this.hiddenLayer[1].getY_in(), this.hiddenLayer[2].getY_in()};
        int index = 0;
        double num = array[index];
        double absNum = Math.abs(num);
        for (int i = 1; i < array.length; ++i) {
            double newAbs = Math.abs(array[i]);
            if (newAbs < absNum) {
                index = i;
                absNum = newAbs;
                num = array[index];
            }
        }
        return index;
    }

    public void checkedYtoTarget(double target, int index) {
//        MODEL MRII
//        for (int i = 0; i < 3; i++) {
//            if (this.y_in != target) {
//
//            }
//        }
        int indexClosest0 = this.getIndexClosestTo0();
        //MODEL MRI
        if (target == 1) {
            this.updateVbiasY_1(index, indexClosest0);

//            double biasBaru = this.hiddenLayer[indexClosest0].getBias() + (this.alpha * (1 - this.hiddenLayer[indexClosest0].getY_in()));
////            double bobotBaru = this.hiddenLayer[indexClosest0].getBobotByIndex()+(this.alpha*(1-this.hiddenLayer[indexClosest0].getY_in()));
//
//            this.hiddenLayer[indexClosest0].setBias(biasBaru);
        } else if (target == -1) {
            this.updateVbiasY_2(index);
        }
    }

    public void setBias(double[] bias) {
        this.bias = bias;
    }

    public double[] getBias() {
        return bias;
    }

    public double getMaxWeight() {
        return 0;
    }

    public double getToleransi() {
        return toleransi;
    }

    public void setToleransi(double toleransi) {
        this.toleransi = toleransi;
    }

    public int getBatasIterasi() {
        return batasIterasi;
    }

    public void setBatasIterasi(int batasIterasi) {
        this.batasIterasi = batasIterasi;
    }

    //OPERASI DELTA WEIGHT
    public void setDeltaHiddenLayer1(int index, double xij) {
        System.out.println("delta: "+index+" "+xij);
        this.deltaBobot[0][index] = this.alpha * (this.target[index] - this.getNet1()) * xij;
    }

    public void setDeltaHiddenLayer2(int index, double xij) {

        this.deltaBobot[1][index] = this.alpha * (target[index] - this.getNet2()) * xij;
    }

    public void setDeltaHiddenLayer3(int index, double xij) {

        this.deltaBobot[2][index] = this.alpha * (target[index] - this.getNet3()) * xij;
    }

    public double getDeltaHiddenLayer1(int index) {
        return this.deltaBobot[0][index];
    }

    public double getDeltaHiddenLayer2(int index) {
        return this.deltaBobot[1][index];
    }

    public double getDeltaHiddenLayer3(int index) {
        return this.deltaBobot[2][index];
    }

    //OPERASI DELTA BIAS
    public void setDeltaBias1(int index) {
        this.deltaBias[0] = this.alpha * (target[index] - this.getNet1());
    }

    public void setDeltaBias2(int index) {
        this.deltaBias[1] = this.alpha * (target[index] - this.getNet2());
    }

    public void setDeltaBias3(int index) {
        this.deltaBias[2] = this.alpha * (target[index] - this.getNet3());
    }

    public double getDeltaBias1() {
        return this.deltaBias[0];
    }

    public double getDeltaBias2() {
        return this.deltaBias[1];
    }

    public double getDeltaBias3() {
        return this.deltaBias[2];
    }


    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input) {
        this.input = input;
    }

    public double[] getTarget() {
        return target;
    }

    public void setTarget(double[] target) {
        this.target = target;
    }

    public double[][] getbobot() {
        return bobot;
    }

    public void setbobot(double[][] bobot) {
        bobot = bobot;
    }

    public int getJumlahData() {
        return jumlahData;
    }

    public void setJumlahData(int jumlahData) {
        this.jumlahData = jumlahData;
    }

    public void setBobotVdanBias(double v1, double v2, double v3, double biasY) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.biasY = biasY;
    }

    public void setY_INAkhir() {
        this.y_in = this.biasY + (this.getNet1() * this.v1) + (this.getNet3() * this.v2) + (this.getNet3() * this.v3);
    }

    public void updateVbiasY_1(int index, int hiddenLayerIndex) {
        if (hiddenLayerIndex == 0) {
//            this.v1 = this.v1 + this.alpha * (1 - this.hiddenLayer[0].getY_in()) * this.input[index];
            this.bobot[0][index] = this.bobot[0][index] + this.alpha * (1 - this.hiddenLayer[0].getY_in()) * this.input[index];
            this.bias[0] = this.bias[0] + this.alpha * (1 - this.hiddenLayer[0].getY_in());
//            this.biasY = this.biasY + this.alpha * (1 - this.hiddenLayer[0].getY_in());
        } else if (hiddenLayerIndex == 1) {
//            this.v2 = this.v2 + this.alpha * (1 - this.hiddenLayer[1].getY_in()) * this.input[index];
            this.bobot[1][index] = this.bobot[1][index] + this.alpha * (1 - this.hiddenLayer[1].getY_in()) * this.input[index];
            this.bias[1] = this.bias[1] + this.alpha * (1 - this.hiddenLayer[1].getY_in());
//            this.biasY = this.biasY + this.alpha * (1 - this.hiddenLayer[1].getY_in());
        } else if (hiddenLayerIndex == 2) {
//            this.v3 = this.v3 + this.alpha * (1 - this.hiddenLayer[2].getY_in()) * this.input[index];
            this.bobot[2][index] = this.bobot[2][index] + this.alpha * (1 - this.hiddenLayer[2].getY_in()) * this.input[index];
            this.bias[2] = this.bias[2] + this.alpha * (1 - this.hiddenLayer[2].getY_in());
//            this.biasY = this.biasY + this.alpha * (1 - this.hiddenLayer[2].getY_in());
        }

    }

    public void updateVbiasY_2(int index) {

        if (this.hiddenLayer[0].getY_in() < 0) {
//            this.v1 = this.v1 + this.alpha * (-1 - this.hiddenLayer[0].getY_in()) * this.input[index];
            this.bobot[0][index] = this.bobot[0][index] + this.alpha * (-1 - this.hiddenLayer[0].getY_in()) * this.input[index];
            this.bias[0] = this.bias[0] + this.alpha * (-1 - this.hiddenLayer[0].getY_in());
        }
        if (this.hiddenLayer[1].getY_in() < 0) {
//            this.v2 = this.v1 + this.alpha * (-1 - this.hiddenLayer[1].getY_in()) * this.input[index];
            this.bobot[1][index] = this.bobot[1][index] + this.alpha * (-1 - this.hiddenLayer[1].getY_in()) * this.input[index];
            this.bias[1] = this.bias[1] + this.alpha * (-1 - this.hiddenLayer[1].getY_in());
        }
        if (this.hiddenLayer[2].getY_in() < 0) {
//            this.v3 = this.v3 + this.alpha * (-1 - this.hiddenLayer[2].getY_in()) * this.input[index];
            this.bobot[2][index] = this.bobot[2][index] + this.alpha * (-1 - this.hiddenLayer[2].getY_in()) * this.input[index];
            this.bias[2] = this.bias[2] + this.alpha * (-1 - this.hiddenLayer[2].getY_in());
        }

//        this.biasY = this.biasY + this.alpha * (1 - this.hiddenLayer[0].getY_in());


    }


    public double getY_in() {
        this.setY_INAkhir();
        return this.y_in;
    }

    public double getBiasY() {
        return biasY;
    }

    public double getV1() {
        return v1;
    }

    public double getV2() {
        return v2;
    }

    public double getV3() {
        return v3;
    }

    public void setError(int index) {
        this.error = Math.pow((this.target[index] - this.y_in), 2);
    }

    public double getError() {
        return this.error;
    }
}
