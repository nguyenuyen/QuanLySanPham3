package webapp.servlet.Excel;

import model.Product;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;

import java.awt.*;
import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static webapp.servlet.Excel.WriteExcelProduct.*;

import static webapp.servlet.Excel.WriteExcelProduct.COLUMN_INDEX_CREATE_AT;


public class CreateWriteProduct {


    public static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);
        //firstCell.setCellValue("List of Customer");

        Row firstRow = sheet.createRow(0);
        Cell cell = firstRow.createCell(0);


        cell.setCellValue("List Product");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");

        cell = row.createCell(COLUMN_INDEX_TYPE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Type");

        cell = row.createCell(COLUMN_INDEX_CREATE_AT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Create_at");

    }


    public static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
     //   cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    public static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 1; columnIndex <= lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
    public static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    public static void writeBook(Product product, Row row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

     //   Cell cell = row.createCell(COLUMN_INDEX_ID);
      //  cell.setCellValue(product.getId());

        Cell cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(product.getName());

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(product.getPrice());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_TYPE);
        cell.setCellValue(product.getType());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_CREATE_AT);
        cell.setCellValue(product.getCreate_at());
        cell.setCellStyle(cellStyleFormatNumber);

        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);

    }


}
