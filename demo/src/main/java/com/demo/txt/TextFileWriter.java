package com.demo.txt;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileWriter {
    public static void main(String[] args) {
        String fileContent = generateTextFile("|");
        System.out.println("Contenido del archivo:\n" + fileContent);
    }

    public static String generateTextFile(String separator) {
        // Crear datos de ejemplo
        String[] headers = {"Nombre", "Apellido", "Edad"};
        List<Object[]> data = Arrays.asList(
                new Object[]{"Juan", "Pérez", 30},
                new Object[]{"María", "López", 25},
                new Object[]{"Carlos", "González", 35}
        );

        // Escribir los encabezados en el archivo
        String headerLine = writeHeaders(headers, separator);

        // Escribir los datos en el archivo
        String dataLines = writeData(data, separator);

        // Combinar encabezados y datos
        return headerLine + dataLines;
    }

    public static String writeHeaders(String[] headers, String separator) {
        return Arrays.stream(headers)
                .collect(Collectors.joining(separator)) + "\n";
    }

    public static String writeData(List<Object[]> data, String separator) {
        return data.stream()
                .map(row -> Arrays.stream(row)
                        .map(Object::toString)
                        .collect(Collectors.joining(separator)))
                .collect(Collectors.joining("\n"));
    }
}
