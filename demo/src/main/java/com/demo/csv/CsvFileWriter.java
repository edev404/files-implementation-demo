package com.demo.csv;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFileWriter {
    public static void main(String[] args) {
        String filename = "output.csv";
        ResponseEntity<byte[]> responseEntity = generateCSVFile(filename, ",");
        System.out.println("Archivo CSV generado con éxito: " + filename);
    }

    public static ResponseEntity<byte[]> generateCSVFile(String filename, String separator) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Crear datos de ejemplo
            String[] headers = {"Nombre", "Apellido", "Edad"};
            List<Object[]> data = Arrays.asList(
                    new Object[]{"Juan", "Pérez", 30},
                    new Object[]{"María", "López", 25},
                    new Object[]{"Carlos", "González", 35}
            );

            // Escribir los encabezados en el archivo
            writeHeaders(writer, headers, separator);

            // Escribir los datos en el archivo
            writeData(writer, data, separator);

            // Leer el contenido del archivo
            byte[] fileContent = Files.readAllBytes(Paths.get(filename));

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentDispositionFormData("attachment", filename);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void writeHeaders(FileWriter writer, String[] headers, String separator) throws IOException {
        String headerLine = Arrays.stream(headers)
                .collect(Collectors.joining(separator)) + "\n";
        writer.write(headerLine);
    }

    public static void writeData(FileWriter writer, List<Object[]> data, String separator) throws IOException {
        for (Object[] row : data) {
            String dataLine = Arrays.stream(row)
                    .map(Object::toString)
                    .collect(Collectors.joining(separator)) + "\n";
            writer.write(dataLine);
        }
    }
}
