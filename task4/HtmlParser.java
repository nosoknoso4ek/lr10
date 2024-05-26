package javaapp.lr10.task4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HtmlParser {
    public static void main(String[] args) {
            try {
                String url = "http://fat.urfu.ru/index.html";
    
                Document doc = getHtmlDocument(url);
    
                if (doc != null) {
                    String filePath = "C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task4\\news_data.txt";
    
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8));
    
                    Elements newsParent = doc.select("body > table > tbody > tr > td > div > table > " +
                            "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
                            "tr > td:nth-child(1)");
    
                    for (int i = 3; i < 20; i++) {
                        if (!(i % 2 == 0)) {
                            List<Node> nodes = newsParent.get(0).childNodes();
                            String topic = ((Element) nodes.get(i)).getElementsByClass("blocktitle")
                                    .get(0).childNodes().get(0).toString();
                            String date = ((Element) nodes.get(i)).getElementsByClass("blockdate")
                                    .get(0).childNodes().get(0).toString();
    
                            System.out.println("Тема: " + topic);
                            System.out.println("Дата: " + date);
    
                            writer.write("Тема: " + topic + "\n");
                            writer.write("Дата: " + date + "\n");
                        }
                    }
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        private static Document getHtmlDocument(String url) throws IOException {
            Document doc = null;
            int retryCount = 3;
    
            for (int i = 0; i < retryCount; i++) {
                try {
                    doc = Jsoup.connect(url).get();
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка при подключении к сайту: " + e.getMessage());
                    if (i < retryCount - 1) {
                        System.out.println("Попытка переподключения...");
                    }
                }
            }
            return doc;
        }
}
