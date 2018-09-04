package com.company;

import SaveImage.SaveImageFromUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static Excel.WriteExcelProduct.writeExcel;

public class WorkerThread implements Runnable {
    //  private  String excelFilePath = "D:\\a2.xlsx";
    String url;
    String name = null;
    private String folderPath = "C:\\Users\\trann\\OneDrive\\Documents\\GitHub\\QuanLySanPham3.1\\QuanLySanPham3\\web\\picture\\product\\";
    //  private  String path ="\\picture\\product\\";
    private String path = "D:\\picture\\";

    //    String excelFilePath = "D:/a2.xlsx";
//    Product product = null;
    public WorkerThread(String url) {
        this.url = url;

    }

    public WorkerThread() {

    }

    public String replaceData(String s) {
        String str = null;
        s = s.replaceAll(",", "");
        s = s.substring(0, s.indexOf(' '));
        return s;
    }

    public Product getData() {
//        QueueData queueData = new QueueData();
//        Queue<String> queue = new LinkedList<>();
//        queue = queueData.pushQueue();
        WorkerThread workerThread = new WorkerThread();
        String price;
        Product product = null;
        if (url != null) {
            try {
                Document document = document = Jsoup.connect(url).get();
                Element elementsType = document.select("a[class=text_TpA7X]").first();
                Element elementsImage = document.select("figure[class=imageSquare_3d75V]>img").first();
                Element elementsName = document.select("h1[class=productName_Dx2F6]").first();
                Element elementsPrice = document.select("strong[class=currentPrice_1aHyi]").first();
                price = workerThread.replaceData(elementsPrice.ownText());
                name = SaveImageFromUrl.saveImage(elementsImage.attr("src"), folderPath);
                if (elementsType != null) {
                    product = new Product(elementsName.ownText(), Integer.parseInt(price), path + name, elementsType.ownText());
                    ;
//                    System.out.println(elementsImage.attr("src"));
//                    System.out.println(elementsName.ownText());
//                    System.out.println(price);
//                    System.out.println(elementsType.ownText());
                } else {
                    Element element_type = document.select("span[class=text_TpA7X undefined]").first();
                    product = new Product(elementsName.ownText(), Integer.parseInt(price), folderPath + name, element_type.ownText());
                    ;
//                    System.out.println(elementsImage.attr("src"));
//                    System.out.println(elementsName.ownText());
//                    System.out.println(price);
//                    System.out.println(element_type.ownText());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public void run() {
        WorkerThread workerThread = new WorkerThread(url);
//        String excelFilePath = "D:/a1.xlsx";

        System.out.println(Thread.currentThread().getName() + " Starting. Task = " + url);

        processCommand();

        System.out.println(Thread.currentThread().getName() + " Finished.");

        // Thread.currentThread().
        //  System.out.println(Thread.currentThread().getName()+url);

    }

    private void processCommand() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
