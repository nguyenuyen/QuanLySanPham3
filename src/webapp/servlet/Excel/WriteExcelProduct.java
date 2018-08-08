package webapp.servlet.Excel;

import dao.ProductDao;
import model.Product;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

import static webapp.servlet.Excel.CreateWriteProduct.autosizeColumn;
import static webapp.servlet.Excel.CreateWriteProduct.createOutputFile;

public class WriteExcelProduct {
    public static  final  int COLUMN_INDEX = 0;
    public static  final  int COLUMN_INDEX_NAME = 1;
    public static  final  int COLUMN_INDEX_PRICE = 2;
    public static  final  int COLUMN_INDEX_TYPE = 3;
    public static  final  int COLUMN_INDEX_CREATE_AT = 4;
    public static CellStyle cellStyleFormatNumber = null;


    public static void writeExcel(List<Product> productList, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook =CreateWorkBook.getWorkbook(excelFilePath);

        // Create sheet

            Sheet sheet = workbook.createSheet("Products"); // Create sheet with sheet name

        

        int rowIndex = 1;

        // Write header
       CreateWriteProduct.writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (Product produt : productList) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            CreateWriteProduct.writeBook(produt, row);
            rowIndex++;
        }

        // Write footer
       // writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

}
