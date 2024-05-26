package javaapp.lr10.task1.Example5;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelFile {
public static void main(String[] args) throws IOException {
    String filePath = "C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\task1\\Example5\\Example5.xlsx";
    FileInputStream inputStream = new FileInputStream(filePath);

    XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

    XSSFSheet sheet = workbook.getSheet("Товары");

    for (Row row : sheet) {
        for (Cell cell : row) {
            System.out.print(cell.toString() + "\t");
        }
        System.out.println();
    }
    workbook.close();
    inputStream.close();
}
}
