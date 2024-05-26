package javaapp.lr10.task2;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
    private Document document = null;

    public Document parse() {
        try {
            File inputFile = new File("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task2\\Animals.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            return document = doc;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printContent() {
        document.getDocumentElement().normalize();
        System.out.println("Корневой элемент: " + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("Animal");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println("\nТекущий элемент: " + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Вид животного: " + element.getElementsByTagName("Type").item(0)
                        .getTextContent());
                System.out.println("Имя: " + element.getElementsByTagName("Name").item(0)
                        .getTextContent());
                System.out.println("Дата рождения: " + element.getElementsByTagName("DateOfBirth").item(0)
                        .getTextContent());
                System.out.println("Пол: " + element.getElementsByTagName("Gender").item(0)
                        .getTextContent());
            }
        }
    }

    public void addAnimal(String type, String name, String dateOfBirth, String gender) throws TransformerException {
        Element animal = document.createElement("Amimal");

        Element type1 = document.createElement("Type");
        type1.appendChild(document.createTextNode(type));
        animal.appendChild(type1);

        Element name1 = document.createElement("Name");
        name1.appendChild(document.createTextNode(name));
        animal.appendChild(name1);

        Element dateOfBirth1 = document.createElement("DateOfBirth");
        dateOfBirth1.appendChild(document.createTextNode(dateOfBirth));
        animal.appendChild(dateOfBirth1);

        Element gender1 = document.createElement("Gender");
        gender1.appendChild(document.createTextNode(gender));
        animal.appendChild(gender1);

        document.getDocumentElement().appendChild(animal);

        document.normalizeDocument();
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task2\\Animals.xml"));
        tf.transform(source, result);
        System.out.println("Запись добавлена!");
        parse();
    }

    public List<Element> findAnimal(String type, String gender) {
        NodeList nodeList = document.getElementsByTagName("Animal");
        List<Element> animals = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(node -> (Element) node)
                .filter(element -> {
                    String type1 = element.getElementsByTagName("Type").item(0).getTextContent();
                    String gender1 = element.getElementsByTagName("Gender").item(0).getTextContent();
                    return type1.equalsIgnoreCase(type) || gender1.equalsIgnoreCase(gender);
                })
                .collect(Collectors.toList());
        return animals;
    }

    public void dropAnimal(int index) throws TransformerException {
        Node dropedAnimal = document.getElementsByTagName("Animal").item(index);
        Node parentNode = dropedAnimal.getParentNode();
        parentNode.removeChild(dropedAnimal);
        document.getDocumentElement().removeChild(dropedAnimal);
        document.normalizeDocument();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource();
        StreamResult result = new StreamResult(new File("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task2\\Animals.xml"));
        transformer.transform(source, result);
        System.out.println("Запись удалена!");
        parse();
    }
}
