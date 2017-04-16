//import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        System.out.println("Hello World!");
//        Madaline_fertility.xls
        try {
            FileInputStream file = new FileInputStream(new File("Madaline_fertility.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);

            Data data = new Data();

//            Cell cell = null;
            //MEMBUAT HEADER PADA EXCEL
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("EPOCH");
            header.createCell(1).setCellValue("MUSIM");
            header.createCell(2).setCellValue("UMUR");
            header.createCell(3).setCellValue("PENYAKIT KETIKA KECIL");
            header.createCell(4).setCellValue("KECELAKAAN/TRAUMA");
            header.createCell(5).setCellValue("BEDAH");
            header.createCell(6).setCellValue("DEMAM DALAM 1 TAHUN TERAKHIR");
            header.createCell(7).setCellValue("KONSUMSI ALKOHOL");
            header.createCell(8).setCellValue("KEBIASAAN MEROKOK");
            header.createCell(9).setCellValue("DURASI DUDUK/HARI");
            header.createCell(10).setCellValue("hidden layer 1");
            header.createCell(11).setCellValue("hidden layer 2");
            header.createCell(12).setCellValue("hidden layer 3");
            header.createCell(13).setCellValue("Y_in");
            header.createCell(14).setCellValue("Target");
            header.createCell(15).setCellValue("t-y");
            //HIDDEN LAYER 1
            header.createCell(16).setCellValue("Dw1-1");
            header.createCell(17).setCellValue("Dw2-1");
            header.createCell(18).setCellValue("Dw3-1");
            header.createCell(19).setCellValue("Dw4-1");
            header.createCell(20).setCellValue("Dw5-1");
            header.createCell(21).setCellValue("Dw6-1");
            header.createCell(22).setCellValue("Dw7-1");
            header.createCell(23).setCellValue("Dw8-1");
            header.createCell(24).setCellValue("Dw9-1");
            header.createCell(25).setCellValue("DBias-1");
            //HIDDEN LAYER 2
            header.createCell(26).setCellValue("Dw1-2");
            header.createCell(27).setCellValue("Dw2-2");
            header.createCell(28).setCellValue("Dw3-2");
            header.createCell(29).setCellValue("Dw4-2");
            header.createCell(30).setCellValue("Dw5-2");
            header.createCell(31).setCellValue("Dw6-2");
            header.createCell(32).setCellValue("Dw7-2");
            header.createCell(33).setCellValue("Dw8-2");
            header.createCell(34).setCellValue("Dw9-2");
            header.createCell(35).setCellValue("Dbias-2");
            // HIDDEN LAYER 3
            header.createCell(36).setCellValue("Dw1-3");
            header.createCell(37).setCellValue("Dw2-3");
            header.createCell(38).setCellValue("Dw3-3");
            header.createCell(39).setCellValue("Dw4-3");
            header.createCell(40).setCellValue("Dw5-3");
            header.createCell(41).setCellValue("Dw6-3");
            header.createCell(42).setCellValue("Dw7-3");
            header.createCell(43).setCellValue("Dw8-3");
            header.createCell(44).setCellValue("Dw9-3");
            header.createCell(45).setCellValue("DBias-3");
            //Bobot, bias dan error
            //HD1
            header.createCell(46).setCellValue("w1-1");
            header.createCell(47).setCellValue("w2-1");
            header.createCell(48).setCellValue("w3-1");
            header.createCell(49).setCellValue("w4-1");
            header.createCell(50).setCellValue("w5-1");
            header.createCell(51).setCellValue("w6-1");
            header.createCell(52).setCellValue("w7-1");
            header.createCell(53).setCellValue("w8-1");
            header.createCell(54).setCellValue("w9-1");
            header.createCell(55).setCellValue("bias-1");
            //HD2
            header.createCell(56).setCellValue("w1-2");
            header.createCell(57).setCellValue("w2-2");
            header.createCell(58).setCellValue("w3-2");
            header.createCell(59).setCellValue("w4-2");
            header.createCell(60).setCellValue("w5-2");
            header.createCell(61).setCellValue("w6-2");
            header.createCell(62).setCellValue("w7-2");
            header.createCell(63).setCellValue("w8-2");
            header.createCell(64).setCellValue("w9-2");
            header.createCell(65).setCellValue("bias-2");
            //HD3
            header.createCell(66).setCellValue("w1-3");
            header.createCell(67).setCellValue("w2-3");
            header.createCell(68).setCellValue("w3-3");
            header.createCell(69).setCellValue("w4-3");
            header.createCell(70).setCellValue("w5-3");
            header.createCell(71).setCellValue("w6-3");
            header.createCell(72).setCellValue("w7-3");
            header.createCell(73).setCellValue("w8-3");
            header.createCell(74).setCellValue("w9-3");
            header.createCell(75).setCellValue("bias-3 ");


            header.createCell(76).setCellValue("v1");
            header.createCell(77).setCellValue("v2");
            header.createCell(79).setCellValue("v3");
            header.createCell(80).setCellValue("bias y_in");
            header.createCell(81).setCellValue("Dv1");
            header.createCell(82).setCellValue("Dv2");
            header.createCell(83).setCellValue("Dv3");
            header.createCell(84).setCellValue("Delta bias y_in");
            header.createCell(85).setCellValue("ERROR");


            Row dataRow = null;

            Madaline madaline = new Madaline();
            System.out.println("PROGRAM MADALINE");
            madaline.setJumlahData(50);
            int epoch = 0;

            //DATA TRAINING
            double[][] training = data.getDataTraining();
            double[] target = data.getTargetTraining();
            madaline.setTarget(data.getTargetTraining());

            //TRAINING
            //INISIALISASI BOBOT AWASL DAN BIAS
            madaline.generateRandomBiasValueForHiddenLayer();
            madaline.generateRandomWeight();
            //HEADER BOBOT DAN BIAS
            Row headerBobot = sheet.createRow(1);
            //HD1
            headerBobot.createCell(46).setCellValue(madaline.getWeightwithIndex(0, 0));
            headerBobot.createCell(47).setCellValue(madaline.getWeightwithIndex(0, 1));
            headerBobot.createCell(48).setCellValue(madaline.getWeightwithIndex(0, 2));
            headerBobot.createCell(49).setCellValue(madaline.getWeightwithIndex(0, 3));
            headerBobot.createCell(50).setCellValue(madaline.getWeightwithIndex(0, 4));
            headerBobot.createCell(51).setCellValue(madaline.getWeightwithIndex(0, 5));
            headerBobot.createCell(52).setCellValue(madaline.getWeightwithIndex(0, 6));
            headerBobot.createCell(53).setCellValue(madaline.getWeightwithIndex(0, 7));
            headerBobot.createCell(54).setCellValue(madaline.getWeightwithIndex(0, 8));
            headerBobot.createCell(55).setCellValue(madaline.getBiasWithIndex(0));//BIAS
            //HD2
            headerBobot.createCell(56).setCellValue(madaline.getWeightwithIndex(1, 0));
            headerBobot.createCell(57).setCellValue(madaline.getWeightwithIndex(1, 1));
            headerBobot.createCell(58).setCellValue(madaline.getWeightwithIndex(1, 2));
            headerBobot.createCell(59).setCellValue(madaline.getWeightwithIndex(1, 3));
            headerBobot.createCell(60).setCellValue(madaline.getWeightwithIndex(1, 4));
            headerBobot.createCell(61).setCellValue(madaline.getWeightwithIndex(1, 5));
            headerBobot.createCell(62).setCellValue(madaline.getWeightwithIndex(1, 6));
            headerBobot.createCell(63).setCellValue(madaline.getWeightwithIndex(1, 7));
            headerBobot.createCell(64).setCellValue(madaline.getWeightwithIndex(1, 8));
            headerBobot.createCell(65).setCellValue(madaline.getBiasWithIndex(1));//BIAS
            //HD3
            headerBobot.createCell(66).setCellValue(madaline.getWeightwithIndex(2, 0));
            headerBobot.createCell(66).setCellValue(madaline.getWeightwithIndex(2, 1));
            headerBobot.createCell(67).setCellValue(madaline.getWeightwithIndex(2, 2));
            headerBobot.createCell(68).setCellValue(madaline.getWeightwithIndex(2, 3));
            headerBobot.createCell(69).setCellValue(madaline.getWeightwithIndex(2, 4));
            headerBobot.createCell(70).setCellValue(madaline.getWeightwithIndex(2, 5));
            headerBobot.createCell(71).setCellValue(madaline.getWeightwithIndex(2, 5));
            headerBobot.createCell(72).setCellValue(madaline.getWeightwithIndex(2, 6));
            headerBobot.createCell(73).setCellValue(madaline.getWeightwithIndex(2, 7));
            headerBobot.createCell(74).setCellValue(madaline.getWeightwithIndex(2, 8));
            headerBobot.createCell(75).setCellValue(madaline.getBiasWithIndex(2));//BIAS

            double[] input;
            int row = 2;

            madaline.setToleransi(0.05);
            madaline.setBatasIterasi(10);
            madaline.setAlpha(0.01);

            //INISIALISASI BOBOT PADA MADALINE
            madaline.setBobotVdanBias(0.5, 0.5, 0.5, 0.5);
            headerBobot.createCell(76).setCellValue(0.5);
            headerBobot.createCell(77).setCellValue(0.5);
            headerBobot.createCell(78).setCellValue(0.5);
            headerBobot.createCell(79).setCellValue(0.5);

            //PROSES TRAINING
            do {
                dataRow = sheet.createRow((row++));
                System.out.println("EPOCH: " + (++epoch));
                dataRow.createCell(0).setCellValue(epoch);
                //NGISI PER ELEMENT PER KOLOM
                for (int k = 0; k < 50; k++) {
                    dataRow = sheet.createRow((row++));
                    dataRow.createCell(1).setCellValue(training[0][k]);
                    dataRow.createCell(2).setCellValue(training[1][k]);
                    dataRow.createCell(3).setCellValue(training[2][k]);
                    dataRow.createCell(4).setCellValue(training[3][k]);
                    dataRow.createCell(5).setCellValue(training[4][k]);
                    dataRow.createCell(6).setCellValue(training[5][k]);
                    dataRow.createCell(7).setCellValue(training[6][k]);
                    dataRow.createCell(8).setCellValue(training[7][k]);
                    dataRow.createCell(9).setCellValue(training[8][k]);


                    input =
                            new double[]{training[0][k], training[1][k], training[2][k], training[3][k], training[4][k], training[5][k], training[6][k], training[7][k], training[8][k], training[9][k]};
                    madaline.setInput(input);
                    for (int i = 0; i < 27; i++) {


                        double[] bias = madaline.getBias();
//                madaline.setInput(input);
                        madaline.setHiddenLayer1(bias[0], madaline.getNet1());
                        madaline.setHiddenLayer2(bias[1], madaline.getNet2());
                        madaline.setHiddenLayer3(bias[2], madaline.getNet3());

                        //SET VALUE
                        dataRow.createCell(10).setCellValue(madaline.getNet1());
                        dataRow.createCell(11).setCellValue(madaline.getNet2());
                        dataRow.createCell(12).setCellValue(madaline.getNet3());

                        //DELTA W dan BIAS

                        madaline.setDeltaBias1(i);
                        madaline.setDeltaBias2(i);
                        madaline.setDeltaBias3(i);
                        //HIDDEN LAYER 1
                        madaline.setDeltaHiddenLayer1(i, training[0][i]);
                        madaline.setDeltaHiddenLayer1(i, training[1][i]);
                        madaline.setDeltaHiddenLayer1(i, training[2][i]);
                        madaline.setDeltaHiddenLayer1(i, training[3][i]);
                        madaline.setDeltaHiddenLayer1(i, training[4][i]);
                        madaline.setDeltaHiddenLayer1(i, training[5][i]);
                        madaline.setDeltaHiddenLayer1(i, training[6][i]);
                        madaline.setDeltaHiddenLayer1(i, training[7][i]);
                        madaline.setDeltaHiddenLayer1(i, training[8][i]);
//                    madaline.setDeltaBias1(i);
                        //SET NILAI DI EXCEL
                        dataRow.createCell(16).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(17).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(18).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(19).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(20).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(21).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(22).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(23).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(24).setCellValue(madaline.getDeltaHiddenLayer1(i));
                        dataRow.createCell(25).setCellValue(madaline.getDeltaBias1());


                        //HIDDEN LAYER 2
                        madaline.setDeltaHiddenLayer2(i, training[0][i]);
                        madaline.setDeltaHiddenLayer2(i, training[1][i]);
                        madaline.setDeltaHiddenLayer2(i, training[2][i]);
                        madaline.setDeltaHiddenLayer2(i, training[3][i]);
                        madaline.setDeltaHiddenLayer2(i, training[4][i]);
                        madaline.setDeltaHiddenLayer2(i, training[5][i]);
                        madaline.setDeltaHiddenLayer2(i, training[6][i]);
                        madaline.setDeltaHiddenLayer2(i, training[7][i]);
                        madaline.setDeltaHiddenLayer2(i, training[8][i]);
//                    madaline.setDeltaBias2(i);
                        //SET NILAI DI EXCEL
                        dataRow.createCell(26).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(27).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(28).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(29).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(30).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(31).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(32).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(33).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(34).setCellValue(madaline.getDeltaHiddenLayer2(i));
                        dataRow.createCell(35).setCellValue(madaline.getDeltaBias2());

                        //HIDDEN LAYER 3
                        madaline.setDeltaHiddenLayer3(i, training[0][i]);
                        madaline.setDeltaHiddenLayer3(i, training[1][i]);
                        madaline.setDeltaHiddenLayer3(i, training[2][i]);
                        madaline.setDeltaHiddenLayer3(i, training[3][i]);
                        madaline.setDeltaHiddenLayer3(i, training[4][i]);
                        madaline.setDeltaHiddenLayer3(i, training[5][i]);
                        madaline.setDeltaHiddenLayer3(i, training[6][i]);
                        madaline.setDeltaHiddenLayer3(i, training[7][i]);
                        madaline.setDeltaHiddenLayer3(i, training[8][i]);
//                    madaline.setDeltaBias3(i);
                        //SET NILAI DI EXCEL
                        dataRow.createCell(36).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(37).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(38).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(39).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(40).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(41).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(42).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(43).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(44).setCellValue(madaline.getDeltaHiddenLayer3(i));
                        dataRow.createCell(45).setCellValue(madaline.getDeltaBias3());

                        //BOBOT DAN BIAS


                        //SET VALUE
                        dataRow.createCell(13).setCellValue(madaline.getY_in());
                        //SET TARGET
                        dataRow.createCell(14).setCellValue(target[i]);
                        double t_y = target[i] - madaline.getY_in();
                        dataRow.createCell(15).setCellValue(t_y);

                        //Cek nilai
                        if (t_y != 0) {
                            //cek nilai nilai yang masuk ke madaline
                            //update bobot nilai
                            madaline.checkedYtoTarget(target[0], i);

                        }


                        //cari error
                        madaline.setError(i);
                        dataRow.createCell(84).setCellValue(madaline.getError());

                    }
                    //lihat v max - tidak perlu
                }

            } while (epoch <= madaline.getBatasIterasi());
            file.close();
            FileOutputStream out = new FileOutputStream(new File("Madaline_fertility.xls"));
            workbook.write(out);
            out.close();


            // DATA UJI


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

}
