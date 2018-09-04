package com.company;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static Excel.WriteExcelProduct.writeExcel;

public class Main {

    public static void main(String[] args) throws IOException {

//        WriteFile writeFile = new WriteFile();
//        writeFile.writeFile();
        // List<Product> productList = new ArrayList<>();
        String excelFilePath = "D:/demo.xlsx";
        Product product = null;
        ReadFile readFile = new ReadFile();
        Queue<String> queue = new LinkedList<>();
        queue = readFile.pushQueue();
        System.out.println(queue.size());
        Iterator<String> iterator = queue.iterator();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        while (iterator.hasNext()) {
            Runnable worker = new WorkerThread(iterator.next());
            executor.execute(worker);
            // System.out.println(executor. +"hgjh");
            product = ((WorkerThread) worker).getData();
            try {
                writeExcel(product, excelFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");


    }
}
