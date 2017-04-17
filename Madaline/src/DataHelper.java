import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Kotak Hitam on 4/3/2017.
 */
public class DataHelper {
    private double[][] dataTraining = new double[9][51];
    private double[][] dataUji = new double[9][51];
    private double[] targetTraining = new double[50];
    private double[] targetUji = new double[50];
    FileInputStream file = null;
    HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;

    public DataHelper() throws IOException {

    }


    public void setDataTraining() throws IOException {
        try {
            this.file = new FileInputStream(new File("dataset.xls"));
            this.workbook = new HSSFWorkbook(file);
            this.sheet = workbook.getSheetAt(0);
            Cell cell = null;
            for (int j = 0; j < 9; j++) {
                for (int k = 1; k < 50; k++) {
                    cell = this.sheet.getRow(k).getCell(j);
                    this.dataTraining[j][k - 1] = cell.getNumericCellValue();
//                    System.out.println("training: "+this.dataTraining[j][k]);
                }
            }
            //NGISI TARGET
            for (int i = 1; i < 50; i++) {
                cell = this.sheet.getRow(i).getCell(9);
                if (cell.getStringCellValue().equalsIgnoreCase("N")) {
                    this.targetTraining[i - 1] = 1;
//                    System.out.println("target "+this.targetTraining[i]);
                } else if (cell.getStringCellValue().equalsIgnoreCase("O")) {
                    this.targetTraining[i - 1] = -1;
//                    System.out.println("target "+this.targetTraining[i]);
                }
//                System.out.println("target "+this.targetTraining[i-1]);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        this.dataTraining[0] =-0.33,-0.33,-0.33, -0.33, -0.33;
//        int[] a = new int[] {0, 0, 0, 0};
//        this.dataTraining[1] =new double []{0.69,0.94,0.5, 0.75, 0.67};
//        this.dataTraining[2][] =new double {0,1,1, 0, 1};
//        this.dataTraining[3][] =new double {1,0,0, 1, 1};
//        this.dataTraining[4][] =new double {1,1,0, 1, 0};
//        this.dataTraining[5][] =new double {0,0,0, 0, 0};
//        this.dataTraining[6][] =new double {0.8,0.8,1, 1, 0.8};
//        this.dataTraining[7][] =new double {0,1,-1, -1, -1};
//        this.dataTraining[8][] =new double {0.88,0.31,0.5, 0.38, 0.5};

//        //baris
//        for (int i = 0; i < 50; i++) {
        //        }
        //kolom


    }

    public double[][] getDataTraining() throws IOException {
        this.setDataTraining();
        return this.dataTraining;
    }

    public double[] getTargetTraining() throws IOException {
        this.setDataTraining();
        return this.targetTraining;
    }

    public void setDataUji() throws IOException {
        try {
            this.file = new FileInputStream(new File("dataset.xls"));
            this.workbook = new HSSFWorkbook(file);
            this.sheet = workbook.getSheetAt(0);
            Cell cell = null;
            for (int j = 0; j < 9; j++) {
                for (int k = 1; k < 50; k++) {
                    cell = this.sheet.getRow(50+k).getCell(j);
//                    System.out.println(j+"COBA LIHAT: "+cell.getNumericCellValue());
                    this.dataUji[j][k - 1] = cell.getNumericCellValue();
//                    System.out.println("training: "+this.dataTraining[j][k]);
                }
            }
            //NGISI TARGET
            for (int i = 1; i < 50; i++) {
                cell = this.sheet.getRow(50 + i).getCell(9);
                if (cell.getStringCellValue().equalsIgnoreCase("N")) {
                    this.targetUji[i - 1] = 1;
//                    System.out.println("target "+this.targetTraining[i]);
                } else if (cell.getStringCellValue().equalsIgnoreCase("O")) {
                    this.targetUji[i - 1] = -1;
//                    System.out.println("target "+this.targetTraining[i]);
                }
//                System.out.println("target "+this.targetTraining[i-1]);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[][] getDataUji() {
        return this.dataUji;
    }

    public double[] getTargetUji() {
        return this.targetUji;
    }
}
