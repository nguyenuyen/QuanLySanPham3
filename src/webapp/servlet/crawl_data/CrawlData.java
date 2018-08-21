package webapp.servlet.crawl_data;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class CrawlData {
    public static void main(String[] args) {
        String url = "https://www.sendo.vn/shop-quan-ao-topshop.htm";
        try {
            //Document doc = (Document) Jsoup.connect(url).data("query","Java").userAgent("Chrome").cookie("auth", "token").timeout(5000).post();
            for (int i = 1; i <= 10; i++) {
                Document dogPage = Jsoup.connect(url + "?p=" + i).timeout(5000).get();
                Elements elements = dogPage.select("div[class=productList_EGrtB]");
                for (int j = 0; j < elements.size(); j++) {
                    System.out.println(elements.get(j));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
