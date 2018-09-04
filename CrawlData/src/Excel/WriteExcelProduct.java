package Excel;

import com.company.Product;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static Excel.CreateWriteProduct.autosizeColumn;
import static Excel.CreateWriteProduct.createOutputFile;

public class WriteExcelProduct {
    public static final int COLUMN_INDEX = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_PRICE = 3;
    public static final int COLUMN_INDEX_TYPE = 2;
    public static final int COLUMN_INDEX_URL = 4;

    public static CellStyle cellStyleFormatNumber = null;


    public static void writeExcel(Product product, String excelFilePath) throws IOException {
        // Create Workbook
        File file = new File(excelFilePath);
        Sheet sheet = null;
        Workbook workbook = null;
        if (file.exists()) {
            FileInputStream infile = new FileInputStream(file);
            workbook = new XSSFWorkbook(infile);
            sheet = workbook.getSheetAt(0);

            // get sheet with sheet name
            System.out.println(sheet.getLastRowNum());
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("sheet"); // Create sheet with sheet name
        }
        int rowIndex = 1;
        int a = sheet.getLastRowNum();

        // Write header
        CreateWriteProduct.writeHeader(sheet, rowIndex);

        // Create row
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);

        // Write data on row
        CreateWriteProduct.writeBook(product, row);
        int numberOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
        CreateWriteProduct.autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);


        //  System.out.println("Done!!!");

    }
}



