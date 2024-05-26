package javaapp.lr10.task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class JsonParser {
    private JSONObject jsonObject = null;

    public JSONObject parse() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser
                    .parse(new FileReader("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task3\\Animals.json"));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void printContent() {
        System.out.println("Корневой элемент: " + jsonObject.keySet().iterator()
                .next());
        JSONArray jsonArray = (JSONArray) jsonObject.get("Books");

        for (Object o : jsonArray) {
            JSONObject book = (JSONObject) o;
            System.out.println("\nТекущий элемент: Book");
            System.out.println("Название книги: " + book.get("Title"));
            System.out.println("Автор: " + book.get("Author"));
            System.out.println("Год издания: " + book.get("Year"));
        }
    }

    public void findBookByAuthor(String author) {
        try {
            JSONArray jsonArray = (JSONArray) jsonObject.get("Books");
            jsonArray.stream()
                    .filter(Book -> Book instanceof JSONObject)
                    .map(Book -> (JSONObject) Book)
                    .filter(Book -> author.equals(((JSONObject) Book).get("Author")))
                    .forEach(Book -> {
                        JSONObject bookObj = (JSONObject) Book;
                        System.out.println("\nТекущий элемент: " + bookObj);
                        System.out.println("Название книги: " + bookObj.get("Title"));
                        System.out.println("Автор: " + bookObj.get("Author"));
                        System.out.println("Год издания: " + bookObj.get("Year"));
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(String title, String author, String year) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("books");
        JSONObject newBook = new JSONObject();
        newBook.put("title", title);
        newBook.put("author", author);
        newBook.put("year", year);
        jsonArray.add(newBook);

        try (FileWriter file = new FileWriter("src\\main\\java\\javaapp\\lr10\\task3\\example.json")) {
            file.write(jsonArray.toString());
            System.out.println("Json файл успешно обновлен!");
            parse();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dropBook(String title) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("books");
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject book = (JSONObject) iterator.next();
            if (title.equals(book.get("title"))) {
                iterator.remove();
            }
        }

        try (FileWriter file = new FileWriter("src\\main\\java\\javaapp\\lr10\\task3\\example.json")) {
            file.write(jsonArray.toString());
            System.out.println("Json файл успешно обновлен!");
            parse();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
