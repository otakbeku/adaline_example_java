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
public class Data {
    private double[][] dataTraining = new double[10][51];
    private double[][] dataUji = new double[50][];
    private double[] targetTraining = new double[50];
    FileInputStream file = null;
    HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;

    public Data() throws IOException {

    }


    public void setDataTraining() throws IOException {
        try {
            this.file = new FileInputStream(new File("dataset.xls"));
            this.workbook = new HSSFWorkbook(file);
            this.sheet = workbook.getSheetAt(0);
            Cell cell = null;
            for (int j = 0; j < 9; j++) {
                for (int k = 1; k < 51; k++) {
                    cell = this.sheet.getRow(k).getCell(j);
                    this.dataTraining[j][k-1] = cell.getNumericCellValue();
                    System.out.println("training: "+this.dataTraining[j][k]);
                }
            }
            //NGISI TARGET
            for (int i = 0; i < 50; i++) {
                cell = this.sheet.getRow(i).getCell(9);
                if (cell.getStringCellValue().equalsIgnoreCase("N")) {
                    this.targetTraining[i] = 1;
                    System.out.println("target "+this.targetTraining[i]);
                } else if (cell.getStringCellValue().equalsIgnoreCase("O")) {
                    this.targetTraining[i] = -1;
                    System.out.println("target "+this.targetTraining[i]);
                }

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
}
