package javaapp.lr10.task5;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    public static void main(String[] args) {
        try {
            readExcelFile("C:\\Users\\милашка228\\IdeaProjects\\JavaLabs-main\\src\\main\\java\\javaapp\\lr10\\lr10.iml");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода при работе с файлом: " + e.getMessage());
        } catch (InvalidFormatException e) {
            System.out.println("Неверный формат файла: " + e.getMessage());
        }
    }

    public static void readExcelFile(String filePath) throws IOException, InvalidFormatException {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheet("Товары");

        if (sheet == null) {
            throw new IllegalArgumentException("Лист 'Товары' не найден в файле.");
        }

        processSheet(sheet);

        workbook.close();
        inputStream.close();
    }

    public static void processSheet(Sheet sheet) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cellToString(cell) + "\t");
            }
            System.out.println();
        }
    }

    public static String cellToString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
