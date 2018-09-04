package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteFile {
    public List<String> getLink() {

        List<String> list = new ArrayList<>();
        List<String> listLink = new ArrayList<>();
        String url = "https://www.sendo.vn/shop-quan-ao-topshop.htm";
        String link = "https://www.sendo.vn";
        try {
            for (int i = 1; i <= 10; i++) {
                Document docPage = Jsoup.connect(url + "?p=" + i).timeout(5000).get();
                Elements elements = docPage.select("div[class=item]>a");

                //System.out.println(elements.size());
                for (int j = 0; j < elements.size(); j++) {
                    String s = elements.get(j).attr("href");
                    list.add(s);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                String a = link + list.get(i);
                listLink.add(a);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLink;
    }

    public void writeFile() {

        WriteFile m = new WriteFile();
        List<String> list = new ArrayList<>();
        list = m.getLink();
        try {
            FileWriter fw = new FileWriter("D:\\test.txt");
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i) + "\n");

                System.out.println(list.size());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
