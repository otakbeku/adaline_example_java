import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Kotak Hitam on 4/16/2017.
 */
public class ExcelHelper {
    public ExcelHelper() throws IOException {
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
            header.createCell(78).setCellValue("v3");
            header.createCell(79).setCellValue("bias y_in");
            header.createCell(80).setCellValue("Dv1");
            header.createCell(81).setCellValue("Dv2");
            header.createCell(82).setCellValue("Dv3");
            header.createCell(83).setCellValue("Delta bias y_in");
            header.createCell(84).setCellValue("ERROR");

            //HEADER BOBOT DAN BIAS
            Row headerBobot = sheet.createRow(1);
            headerBobot.createCell(46).setCellValue(0);
            headerBobot.createCell(47).setCellValue(0);
            headerBobot.createCell(48).setCellValue(0);
            headerBobot.createCell(49).setCellValue(0);
            headerBobot.createCell(50).setCellValue(0);
            headerBobot.createCell(51).setCellValue(0);
            headerBobot.createCell(52).setCellValue(0);
            headerBobot.createCell(53).setCellValue(0);
            headerBobot.createCell(54).setCellValue(0);
            headerBobot.createCell(55).setCellValue(0);
            headerBobot.createCell(56).setCellValue(0);
            headerBobot.createCell(57).setCellValue(0);
            headerBobot.createCell(58).setCellValue(0);
            headerBobot.createCell(59).setCellValue(0);
            headerBobot.createCell(60).setCellValue(0);
            headerBobot.createCell(61).setCellValue(0);
            headerBobot.createCell(62).setCellValue(0);
            headerBobot.createCell(63).setCellValue(0);
            headerBobot.createCell(64).setCellValue(0);
            headerBobot.createCell(65).setCellValue(0);
            headerBobot.createCell(66).setCellValue(0);
            headerBobot.createCell(66).setCellValue(0);
            headerBobot.createCell(67).setCellValue(0);
            headerBobot.createCell(68).setCellValue(0);
            headerBobot.createCell(69).setCellValue(0);
            headerBobot.createCell(70).setCellValue(0);
            headerBobot.createCell(71).setCellValue(0);
            headerBobot.createCell(72).setCellValue(0);
            headerBobot.createCell(73).setCellValue(0);
            headerBobot.createCell(74).setCellValue(0);
            headerBobot.createCell(75).setCellValue(0);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
