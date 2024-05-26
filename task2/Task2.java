package javaapp.lr10.task2;

import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

public class Task2 {
public static void main(String[] args) {
    XmlParser parser = new XmlParser();
    parser.parse();
    parser.printContent();
    List<Element> elements =parser.findAnimal("Собака", "M");
    for (Element element : elements) {
        System.out.println("Вид животного: " + element.getElementsByTagName("Type").item(0)
                        .getTextContent());
                System.out.println("Имя: " + element.getElementsByTagName("Name").item(0)
                        .getTextContent());
                System.out.println("Дата рождения: " + element.getElementsByTagName("DateOfBirth").item(0)
                        .getTextContent());
                System.out.println("Пол: " + element.getElementsByTagName("Gender").item(0)
                        .getTextContent());
    }
    
    try {
        parser.addAnimal("Попугай", "Кеша", "11.12.2022", "М");
    } catch (TransformerException e) {
        e.printStackTrace();
    }
}
}
