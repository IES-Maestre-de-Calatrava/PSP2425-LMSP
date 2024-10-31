/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.maestre.ej4;

/**
 *
 * @author Usuario
 */
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ej4 {

    public static void main(String[] args) {
        String[] fileNames = {"informatica.txt", "comercio.txt", "administracion.txt", "eso.txt", "bachillerato.txt"};
        ForkJoinPool pool = new ForkJoinPool(fileNames.length);

        try {
            // Crear tareas para cada fichero
            List<SumTask> tasks = new ArrayList<>();
            for (String fileName : fileNames) {
                tasks.add(new SumTask(fileName));
            }

            // Ejecutar tareas en paralelo
            List<Future<Long>> results = pool.invokeAll(tasks);

            // Sumar resultados y escribir en resultado_final.txt
            long totalSum = 0;
            for (Future<Long> result : results) {
                totalSum += result.get();
            }

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("resultado_final.txt"))) {
                writer.write("Total: " + totalSum);
            }

        } catch (Exception e) {
            try (BufferedWriter errorWriter = Files.newBufferedWriter(Paths.get("error.log"))) {
                errorWriter.write("Error: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            pool.shutdown();
        }
    }
}

class SumTask implements Callable<Long> {
    private final String fileName;

    public SumTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Long call() {
        long sum = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sum += Long.parseLong(line);
            }

            // Escribir resultado en fichero .res
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName + ".res"))) {
                writer.write("Sum: " + sum);
            }

        } catch (IOException e) {
            try (BufferedWriter errorWriter = Files.newBufferedWriter(Paths.get("error.log"))) {
                errorWriter.write("Error processing file " + fileName + ": " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return sum;
    }
}

