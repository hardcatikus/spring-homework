package com.netcracker.util;

import com.netcracker.model.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVHandler {

    public static final String CSV_FILE_PATH = "./userdata.csv";

    public static void writeToCSV(String[] data, boolean append) {
        try (
                CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH,append),
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        ) {
            csvWriter.writeNext(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User findUserInCSV(User user) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] currentLine;
            while ((currentLine = csvReader.readNext()) != null) {
                if (user.getLastName().equalsIgnoreCase(currentLine[0])) {
                    if (user.getFirstName().equalsIgnoreCase(currentLine[1])) {
                        user.setLastName(currentLine[0]);
                        user.setFirstName(currentLine[1]);
                        user.setPatronymicName((currentLine[2]));
                        user.setAge(Integer.parseInt(currentLine[3]));
                        user.setSalaryLevel(Float.parseFloat(currentLine[4]));
                        user.setEmail(currentLine[5]);
                        user.setPlaceOfWork(currentLine[6]);
                        return user;
                    }
                }
            }
        }

        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
        }

        return extension;
    }

}
