package com.demo.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// <dependency>
//     <groupId>org.apache.poi</groupId>
//     <artifactId>poi</artifactId>
//     <version>5.2.3</version>
// </dependency>
//     <dependency>
//       <groupId>org.apache.poi</groupId>
//       <artifactId>poi-ooxml</artifactId>
//       <version>4.1.0</version>
//     </dependency>


public class ExcelWriter {
    public static void main(String[] args) {
        byte[] excelFile = generateExcelFile();
        System.out.println("Tamaño del archivo: " + excelFile.length + " bytes");
    }

    public static byte[] generateExcelFile() {
        try {
            // Crear un nuevo libro de Excel
            Workbook workbook = new XSSFWorkbook();

            // Crear una hoja de Excel
            Sheet sheet = workbook.createSheet("MiHoja");

            // Crear datos de ejemplo
            String[] headers = { "Nombre", "Apellido", "Edad" };
            List<Object[]> data = Arrays.asList(
                    new Object[] { "Juan", "Pérez", 30 },
                    new Object[] { "María", "López", 25 },
                    new Object[] { "Carlos", "González", 35 });

            // Escribir los encabezados en la hoja
            writeHeaders(sheet, headers);

            // Escribir los datos en la hoja
            writeData(sheet, data);

            // Guardar el libro de Excel en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            // Retornar el contenido del ByteArrayOutputStream como un arreglo de bytes
            return outputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeHeaders(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    public static void writeData(Sheet sheet, List<Object[]> data) {
        for (int i = 0; i < data.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            for (int j = 0; j < data.get(i).length; j++) {
                Cell cell = dataRow.createCell(j);
                Object value = data.get(i)[j];
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }
            }
        }
    }
}
