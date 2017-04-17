import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;

/**
 * Created by Kotak Hitam on 4/16/2017.
 */
public class ExcelHelper {
    /**
     * MASIH STATIC, BELUM BISA DI PAKAI UNTUK KEPERLUAN LAIN
     *
     * @throws IOException
     */
    public ExcelHelper() throws IOException {

    }

    public void PROCESSING() {
        try {
            FileInputStream file = new FileInputStream(new File("Madaline_fertility.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
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
            header.createCell(13).setCellValue("hidden layer 4");
            header.createCell(14).setCellValue("hidden layer 5");
            header.createCell(15).setCellValue("hidden layer 6");
            header.createCell(16).setCellValue("hidden layer 7");
            header.createCell(17).setCellValue("hidden layer 8");
            header.createCell(18).setCellValue("hidden layer 9");


            header.createCell(19).setCellValue("Y_in/NET");
            header.createCell(20).setCellValue("Target");
            header.createCell(21).setCellValue("t-y");
            ////////////////////////////////////////////////
            //HIDDEN LAYER 1
            for (int i = 0; i < 9; i++) {
                header.createCell(22 + i).setCellValue("Delta W1-" + (i + 1));
            }
            header.createCell(31).setCellValue("Delta Bias-1");

            //HIDDEN LAYER 2
            for (int i = 0; i < 9; i++) {
                header.createCell(32 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(41).setCellValue("Delta Bias-2");

            //HIDDEN LAYER 3
            for (int i = 0; i < 9; i++) {
                header.createCell(42 + i).setCellValue("Delta W3-" + (i + 1));
            }
            header.createCell(51).setCellValue("Delta Bias-3");

            //HIDDEN LAYER 4
            for (int i = 0; i < 9; i++) {
                header.createCell(52 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(61).setCellValue("Delta Bias-4");

            //HIDDEN LAYER 5
            for (int i = 0; i < 9; i++) {
                header.createCell(62 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(71).setCellValue("Delta Bias-5");

            //HIDDEN LAYER 6
            for (int i = 0; i < 9; i++) {
                header.createCell(72 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(81).setCellValue("Delta Bias-6");

            //HIDDEN LAYER 7
            for (int i = 0; i < 9; i++) {
                header.createCell(82 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(91).setCellValue("Delta Bias-7");

            //HIDDEN LAYER 8
            for (int i = 0; i < 9; i++) {
                header.createCell(92 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(101).setCellValue("Delta Bias-8");

            //HIDDEN LAYER 9
            for (int i = 0; i < 9; i++) {
                header.createCell(102 + i).setCellValue("Delta W2-" + (i + 1));
            }
            header.createCell(111).setCellValue("Delta Bias-9");

            //CELL 112
////////////////////////////////////////////////////////////////////////
            //BOBOT HIDDEN LAYER 1
            for (int i = 0; i < 9; i++) {
                header.createCell(112 + i).setCellValue("w1-" + (i + 1));
            }
            header.createCell(121).setCellValue("Bias-1");

            //BOBOT HIDDEN LAYER 2
            for (int i = 0; i < 9; i++) {
                header.createCell(122 + i).setCellValue("w2-" + (i + 1));
            }
            header.createCell(131).setCellValue("Bias-2");

            //BOBOT HIDDEN LAYER 3
            for (int i = 0; i < 9; i++) {
                header.createCell(132 + i).setCellValue("w3-" + (i + 1));
            }
            header.createCell(141).setCellValue("Bias-3");

            //BOBOT HIDDEN LAYER 4
            for (int i = 0; i < 9; i++) {
                header.createCell(142 + i).setCellValue("w4-" + (i + 1));
            }
            header.createCell(151).setCellValue("Bias-4");

            //BOBOT HIDDEN LAYER 5
            for (int i = 0; i < 9; i++) {
                header.createCell(152 + i).setCellValue("w5-" + (i + 1));
            }
            header.createCell(161).setCellValue("Bias-5");

            //BOBOT HIDDEN LAYER 6
            for (int i = 0; i < 9; i++) {
                header.createCell(162 + i).setCellValue("w6-" + (i + 1));
            }
            header.createCell(171).setCellValue("Bias-6");

            //BOBOT HIDDEN LAYER 7
            for (int i = 0; i < 9; i++) {
                header.createCell(172 + i).setCellValue("w7-" + (i + 1));
            }
            header.createCell(181).setCellValue("Bias-7");

            //BOBOT HIDDEN LAYER 8
            for (int i = 0; i < 9; i++) {
                header.createCell(182 + i).setCellValue("w8-" + (i + 1));
            }
            header.createCell(191).setCellValue("Bias-8");

            //BOBOT HIDDEN LAYER 9
            for (int i = 0; i < 9; i++) {
                header.createCell(192 + i).setCellValue("w9-" + (i + 1));
            }
            header.createCell(201).setCellValue("Bias-9");
//////////////////////////////////////////////////////////////////////
            //CELL 202
            header.createCell(202).setCellValue("v1");
            header.createCell(203).setCellValue("v2");
            header.createCell(204).setCellValue("v3");
            header.createCell(205).setCellValue("v4");
            header.createCell(206).setCellValue("v5");
            header.createCell(207).setCellValue("v6");
            header.createCell(208).setCellValue("v7");
            header.createCell(209).setCellValue("v8");
            header.createCell(210).setCellValue("v9");
            header.createCell(211).setCellValue("Bias y_in/NET");
            header.createCell(212).setCellValue("ERROR");

            //HEADER BOBOT DAN BIAS
            Row headerBobot = sheet.createRow(1);
            for (int i = 0; i < 90; i++) {
                headerBobot.createCell(112 + i).setCellValue(0);
            }
            for (int i = 0; i < 10; i++) {
                headerBobot.createCell(202 + i).setCellValue(0.5);
            }


            //KALO UDAH SELESAI
            file.close();
            FileOutputStream out = new FileOutputStream(new File("Madaline_fertility.xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }


}
