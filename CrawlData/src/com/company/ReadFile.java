package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReadFile {
    public Queue pushQueue() {
        Queue<String> queue = new LinkedList<>();

        try {
            String line;
            BufferedReader bufferreader = new BufferedReader(new FileReader(new File("D:\\test.txt")));
            while ((line = bufferreader.readLine()) != null) {
                queue.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }


}
