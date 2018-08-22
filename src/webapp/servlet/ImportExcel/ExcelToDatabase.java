package webapp.servlet.ImportExcel;

import dao.ImageDao;
import dao.ProductDao;
import dao.TypeDao;
import model.Image;
import model.Type;
import model.UserAccount;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.AppUtils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ExcelToDatabase {
    TypeDao typeDao = new TypeDao();
    ImageDao imageDao = new ImageDao();
    ArrayList<String> listType = new ArrayList<>();
    ArrayList<String> listTypeAfter = new ArrayList<>();
    List<Type> list = new ArrayList<>();
    List<String> listImageByExcel = new ArrayList<>();
    List<Image> listImage = new ArrayList<>();
    HashMap listDataByExcel = new HashMap();
    ProductDao productDao = new ProductDao();
    //String mail ="hanh@gmail.com";
    String type = null;

    public int ReadExcelInsertDatabase(String mail) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\b.xlsx");
        // POIFSFileSystem file = new POIFSFileSystem(inputStream);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;
        list = typeDao.findAllType();
        listImage = imageDao.findAllImage();
       // UserAccount loginUser = AppUtils.getLoginUser(request.getSession());

        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            listType.add(sheet.getRow(i).getCell(2).getStringCellValue());
        }
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            listImageByExcel.add(sheet.getRow(i).getCell(4).getStringCellValue());
        }
        for (String element : listType) {
            if (!listTypeAfter.contains(element)) {
                listTypeAfter.add(element);
            }
        }
        for (int i = 0; i < listTypeAfter.size(); i++) {
            int k = 0;
            for (int j = 0; j < list.size(); j++) {
                if (listTypeAfter.get(i).equals(list.get(j).getName())) {
                    k++;
                }
            }
            if (k < 1) {
                typeDao.addType(listTypeAfter.get(i));
            }
        }
        for (int i = 0; i < listImageByExcel.size(); i++) {
            int k = 0;
            for (int j = 0; j < listImage.size(); j++) {
                if (listImageByExcel.get(i).equals(listImage.get(j).getUrl())) {
                    k++;
                }
            }
            if (k < 1) {
                imageDao.addImage(listImageByExcel.get(i));
            }

        }
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String name = row.getCell(1).getStringCellValue();
            String type = row.getCell(2).getStringCellValue();
            int price = (int) row.getCell(3).getNumericCellValue();
            String url = row.getCell(4).getStringCellValue();
            int kq = productDao.importProduct(name,price,mail,type,url);
        }
        return 0;
    }

//    public static void main(String[] args) throws IOException {
//        ExcelToDatabase excelToDatabase = new ExcelToDatabase();
//       // excelToDatabase.ReadExcelInsertDatabase(mail);
//    }

}
