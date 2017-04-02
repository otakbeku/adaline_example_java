//import java.util.*;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        Adaline adl1 = new Adaline();
        System.out.println("Program Adaline");
        int epoch =0;

        double[] x1 = {1, 1, -1, -1};
        double[] x2 = {1, -1, 1, -1};
        double[] target = {1, -1, -1, -1};

        adl1.setBobotAwal(0);
        adl1.setTolerance_value(0.05);
        adl1.setLearningRate(0.1);
        do {
            System.out.println("EPOCH: "+(++epoch));
            System.out.println("X1 X2 1 \t t \t net \t f(net) \t t-y \t Dw1 Dw2 Db \t w1 w2 b");
            for (int i = 0; i < 4; i++) {
                double XiWi = (x1[i] * adl1.getW1()) + (x2[i] * adl1.getW2());
                adl1.setNet(XiWi, adl1.getBias());

                //DELTA W & B
                adl1.setDw1(target[i], x1[i], adl1.getY_in());
                adl1.setDw2(target[i], x2[i], adl1.getY_in());
                adl1.setDbias(target[i], adl1.getY_in());
                //set error
                adl1.setError(target[i], adl1.getY_in());


                if (adl1.getY_in() != target[i]) {
                    adl1.updateW1(target[i], adl1.getY_in(), x1[i], adl1.getW1());
                    adl1.updateW2(target[i], adl1.getY_in(), x2[i], adl1.getW2());
                    adl1.updateBias(adl1.getBias(), target[i], adl1.getY_in());
                }
                double t_y = target[i]-adl1.getY_in();
                System.out.println(x1[i]+" "+x2[i]+" "+ 1 +" \t"+ target[i] +"\t"+adl1.getY_in()+" \t"+ adl1.getY_in()+" \t"+ t_y +"\t "+adl1.getDw1()+" "+ adl1.getDw2()+" "+ adl1.getDbias()+" \t"+  adl1.getW1()+" "+ adl1.getW2()+" "+ adl1.getBias());
                System.out.println("ERROR: "+adl1.getError());
                System.out.println("nilai w max: "+adl1.getMaxWeight());
            }
        } while(adl1.getTolerance_value() < adl1.getMaxWeight());

    }

}
