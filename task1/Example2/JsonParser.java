package javaapp.lr10.task1.Example2;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
public static void main(String[] args) {
    try {
        JSONParser parser = new JSONParser();
        Object obj = parser
                .parse(new FileReader("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task1\\Example2\\example.json"));
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println("Корневой элемент: "+ jsonObject.keySet().iterator()
                            .next());
        JSONArray jsonArray = (JSONArray) jsonObject.get("Books");

        for (Object o : jsonArray) {
            JSONObject book = (JSONObject) o;
            System.out.println("\nТекущий элемент: Book");
            System.out.println("Название книги: "+book.get("Title"));
            System.out.println("Автор: "+book.get("Author"));
            System.out.println("Год издания: "+book.get("Year"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
