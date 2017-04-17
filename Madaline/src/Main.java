import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;


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
            Row epochRow = null;
            ///////////////////////////////////////////
            ////PREPARING SEMUANYA
            ///////////////////////////////////////////
            Madaline madaline = new Madaline(50, 9);
            madaline.setTolerance_value(0.05);
            madaline.setAlpha(0.01);
            madaline.setVBobotAndVBias(0.5);

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
                System.out.println("EPOCH: " + epoch);

                for (int k = 0; k < 50; k++) {
                    dataRow = sheet.createRow((row++));
                    if(k == 0){
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
                    dataRow.createCell(19).setCellValue(madaline.getyInput());
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
                            dataRow.createCell(pointer++).setCellValue(madaline.getBobotAdalineByIndex(adalineNUm, weightNum));
                        }
                        dataRow.createCell(pointer++).setCellValue(madaline.getBiasAdalineByIndex(adalineNUm));
                    }

//                    //INPUT BOBOT MADALINE KE EXCEL (Sudah ada di excelHelper)
//                    for (int i = 0; i < 9; i++) {
//                        dataRow.createCell(202 + i).setCellValue(madaline.getvBobotMadalineByIndex(i));
//                    }
                    dataRow.createCell(211).setCellValue(madaline.getvBiasMadaline());
                    double error = Math.pow(t_y, 2);
                    dataRow.createCell(212).setCellValue(error);
                }
                ++row;
            } while (epoch <= 150);
            file.close();
            FileOutputStream out = new FileOutputStream(new File("Madaline_fertility.xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
