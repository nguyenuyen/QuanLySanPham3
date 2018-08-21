package webapp.servlet.ImportExcel;

import dao.TypeDao;
import model.Type;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelToDatabase {
    TypeDao typeDao = new TypeDao();
    ArrayList<String> listType = new ArrayList<>();
    ArrayList<String> listTypeAfter = new ArrayList<>();
    int k ;
    String type= null;

    public void ReadExcelInsertDatabase() throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\b.xlsx");
        // POIFSFileSystem file = new POIFSFileSystem(inputStream);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            listType.add(sheet.getRow(i).getCell(2).getStringCellValue());
        }
        for(String element : listType){
            if(!listTypeAfter.contains(element)){
                listTypeAfter.add(element);
            }
        }
        for(int i=0; i<listTypeAfter.size();i++){
            k=0;
            for(int j=0;j<typeDao.findAllType().size();j++){
                if(listTypeAfter.get(i).equals(typeDao.findAllType().get(j))){
                    k++;
                }
            }
            if(k<1){
                typeDao.addType(listTypeAfter.get(i));
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ExcelToDatabase excelToDatabase = new ExcelToDatabase();
        excelToDatabase.ReadExcelInsertDatabase();
    }

}
