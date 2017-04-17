import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;


/**
 * Created by Kotak Hitam on 4/7/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {


        try {
            ExcelHelper excelHelper = new ExcelHelper();
            excelHelper.PROCESSING();
            FileInputStream file = new FileInputStream(new File("Madaline_fertility.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);

            DataHelper dataHelper = new DataHelper();

            Row dataRow = null;
            ///////////////////////////////////////////
            ////PREPARING SEMUANYA
            ///////////////////////////////////////////
            Madaline madaline = new Madaline(50, 9);
            madaline.setTolerance_value(0.05);
            madaline.setAlpha(0.01);
            madaline.setVBobotAndVBias(0.5);
            madaline.setThreshold(0.0012);

            int epoch = 0;
            int row = 2;
            double[] xInput;
            //Dimensi data traning => [10][50] (Untuk 9 input->9 Adaline, 50 dataset)
            double[][] training = dataHelper.getDataTraining();
            double[] target = dataHelper.getTargetTraining();
//            madaline.setTargetEachAdaline(dataHelper.getTargetTraining());


            ///////////////////////////////////////////
            //// TRAINING PHASE
            ///////////////////////////////////////////
            do {
//                dataRow = sheet.createRow(row);
//                epochRow = sheet.createRow((row-1));
//                dataRow.createCell(0).setCellValue(++epoch);
//                epochRow.createCell(0).setCellValue(++epoch);
//                System.out.println("EPOCH: " + epoch);

                for (int k = 0; k < 50; k++) {
                    dataRow = sheet.createRow((row++));
                    if (k == 0) {
                        dataRow.createCell(0).setCellValue(++epoch);
                    }
                    dataRow.createCell(1).setCellValue(training[0][k]);
                    dataRow.createCell(2).setCellValue(training[1][k]);
                    dataRow.createCell(3).setCellValue(training[2][k]);
                    dataRow.createCell(4).setCellValue(training[3][k]);
                    dataRow.createCell(5).setCellValue(training[4][k]);
                    dataRow.createCell(6).setCellValue(training[5][k]);
                    dataRow.createCell(7).setCellValue(training[6][k]);
                    dataRow.createCell(8).setCellValue(training[7][k]);
                    dataRow.createCell(9).setCellValue(training[8][k]);

                    xInput =
                            new double[]{training[0][k], training[1][k], training[2][k], training[3][k], training[4][k], training[5][k], training[6][k], training[7][k], training[8][k]};
                    madaline.setTarget(target[k]);
                    madaline.setNetEachAdaline(xInput);
                    ///INPUT KE EXCEL
                    for (int i = 0; i < 9; i++) {
                        dataRow.createCell(10 + i).setCellValue(madaline.getNetFromAdalineByIndex(i));
                    }
                    madaline.setYInputMadaline();

                    dataRow.createCell(19).setCellValue(madaline.getyInput() + " = " + madaline.getY());
                    ///TARGET
                    dataRow.createCell(20).setCellValue(target[k]);
                    double t_y = target[k] - madaline.getyInput();
                    dataRow.createCell(21).setCellValue(t_y);

                    //UPDATE BOBOT DAN BIAS
                    madaline.updateBobotAndBiasEachAdaline(xInput);

                    //INPUT BOBOT ADALINE KE EXCEL UNTUK BOBOT YANG DI PERBARUI
                    int pointer = 112;
                    for (int adalineNUm = 0; adalineNUm < 9; adalineNUm++) {
                        for (int weightNum = 0; weightNum < 9; weightNum++) {
//                            System.out.println("BOBOT: " + madaline.getBobotAdalineByIndex(adalineNUm, weightNum));
                            dataRow.createCell(pointer++).setCellValue(madaline.getBobotAdalineByIndex(adalineNUm, weightNum));
                        }
//                        System.out.println("BIAS: " + madaline.getBiasAdalineByIndex(adalineNUm));
                        dataRow.createCell(pointer++).setCellValue(madaline.getBiasAdalineByIndex(adalineNUm));
                    }
                    int pointerW = 22;
                    for (int adalineNUm = 0; adalineNUm < 9; adalineNUm++) {
                        for (int weightNum = 0; weightNum < 9; weightNum++) {
//                            System.out.println("BOBOT: " + madaline.getBobotAdalineByIndex(adalineNUm, weightNum));
                            dataRow.createCell(pointerW++).setCellValue(madaline.getDeltaBobotAdalineByIndex(adalineNUm, weightNum));
                        }
//                        System.out.println("BIAS: " + madaline.getBiasAdalineByIndex(adalineNUm));
                        dataRow.createCell(pointerW++).setCellValue(madaline.getDeltaBiasAdalineByIndex(adalineNUm));
                    }


                    //INPUT BOBOT MADALINE KE EXCEL (Sudah ada di excelHelper)
                    for (int i = 0; i < 9; i++) {
                        dataRow.createCell(202 + i).setCellValue(madaline.getvBobotMadalineByIndex(i));
                    }
                    dataRow.createCell(211).setCellValue(madaline.getvBiasMadaline());
                    double error = Math.pow(t_y, 2);
//                    System.out.println("ERROR: " + error);
                    dataRow.createCell(212).setCellValue(error);
                }
                ++row;
            } while (epoch <= 1000); //OUT OF MEMORY ERROR KALAU 1200 ITERASi
            file.close();
            FileOutputStream out = new FileOutputStream(new File("Madaline_fertility.xls"));
            workbook.write(out);
            out.close();

            ///////////////////////////////////////////
            //// TESTING PHASE
            ///////////////////////////////////////////
            dataHelper.setDataUji();
            double[][] uji = dataHelper.getDataUji();
            double[] targetUji = dataHelper.getTargetUji();

            excelHelper.PROCESSINGFORTESTING();

            FileInputStream fileUji = new FileInputStream(new File("Madaline_fertility_Testing.xls"));
            HSSFWorkbook workbookUji = new HSSFWorkbook(fileUji);
            HSSFSheet sheetUji = workbookUji.getSheetAt(0);

            int rowUji = 0;
            double[] xInputUji;
            double counter = 0;
            Row dataRowUji = null;
            //UJI
            for (int i = 0; i < 50; i++) {
                dataRowUji = sheetUji.createRow((rowUji++));
                dataRowUji.createCell(1).setCellValue(uji[0][i]);
                dataRowUji.createCell(2).setCellValue(uji[1][i]);
                dataRowUji.createCell(3).setCellValue(uji[2][i]);
                dataRowUji.createCell(4).setCellValue(uji[3][i]);
                dataRowUji.createCell(5).setCellValue(uji[4][i]);
                dataRowUji.createCell(6).setCellValue(uji[5][i]);
                dataRowUji.createCell(7).setCellValue(uji[6][i]);
                dataRowUji.createCell(8).setCellValue(uji[7][i]);
                dataRowUji.createCell(9).setCellValue(uji[8][i]);

                xInputUji = new double[]{uji[0][i], uji[1][i], uji[2][i], uji[3][i], uji[4][i], uji[5][i], uji[6][i], uji[7][i], uji[8][i]
                };
                madaline.setTarget(targetUji[i]);
                madaline.setNetEachAdaline(xInputUji);
                ///INPUT KE EXCEL
                for (int j = 0; j < 9; j++) {
                    dataRowUji.createCell(10 + j).setCellValue(madaline.getNetFromAdalineByIndex(j));
                }
                madaline.setYInputMadaline();

                dataRowUji.createCell(19).setCellValue(madaline.getyInput() + " = " + madaline.getY());
                ///TARGET
                dataRowUji.createCell(20).setCellValue(target[i]);
                double t_y = target[i] - madaline.getyInput();
                dataRowUji.createCell(21).setCellValue(t_y);
                if ((target[i] - madaline.getY()) == 0) {
                    ++counter;
                    System.out.println("Salah: " + (target[i] - madaline.getY()));
                    System.out.println("COUNTER BENER: " + counter);
                }
                double error = Math.pow(t_y, 2);
                dataRow.createCell(32).setCellValue(error);
            }
            double akurasi = (counter / uji[0].length) * 100;
            System.out.println("AKURASI: " + akurasi + "%");

            fileUji.close();
            FileOutputStream outUji = new FileOutputStream(new File("Madaline_fertility_Testing.xls"));
            workbookUji.write(outUji);
            outUji.close();

//            runOutOfMemory();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }
    //UNTUK MENGATUR MEMORI
    //LEBIH BAIK TIDAK DIGUNAKAN
    private static final int MEGABYTE = (1024 * 1024);

    public static void runOutOfMemory() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        for (int i = 1; i <= 100; i++) {
            try {
                byte[] bytes = new byte[MEGABYTE * 500];
            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e) {
                MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
                long maxMemory = heapUsage.getMax() / MEGABYTE;
                long usedMemory = heapUsage.getUsed() / MEGABYTE;
                System.out.println(i + " : Memory Use :" + usedMemory + "M/" + maxMemory + "M");
            }
        }
    }
}
