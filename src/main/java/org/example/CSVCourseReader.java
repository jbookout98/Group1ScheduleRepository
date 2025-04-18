package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVCourseReader {
    /**
     * Reads course data from a CSV file and returns a list of {@link Course} objects.
     *
     * @param filePath the relative path to the CSV file. The file should have at least 18 columns.
     * @return a {@link List} of {@link Course} objects parsed from the CSV file. If the file cannot be
     *         read or contains no valid records, an empty list is returned.
     * @throws IllegalArgumentException if the filePath is null or empty.
     */

    public static List<Course> readCoursesFromCSV(String filePath) {
        List<Course> courses = new ArrayList<>();

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path is null or empty");
        }
        try (InputStream csvFileStream = CSVCourseReader.class.getResourceAsStream("/" + filePath);
             InputStreamReader reader = new InputStreamReader(csvFileStream);
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();

            boolean isFirstLine = true;
            for (String[] record : records) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip header
                }

                // Ensure the record has the expected number of columns
                if (record.length < 18) {
                    System.err.println("Invalid record length: " + record.length);
                    continue; // Skip incomplete records
                }

                // Create a new course object
                Course course = new Course();

                // Fill fields while handling potential missing data
                course.setCrn(record[0].trim());
                course.setSubject(record[1].trim());
                course.setDepartment(record[2].isEmpty() ? "Unknown" : record[2].trim());
                course.setNumber(record[3].trim());
                course.setSection(record[4].isEmpty() ? "Unknown" : record[4].trim());
                course.setInstructor(record[5].isEmpty() ? "TBD" : record[5].trim());
                course.setDays(record[6].trim());
                course.setStartTime(record[7].trim());
                course.setEndTime(record[8].trim());
                course.setBuilding(record[9].isEmpty() ? "TBD" : record[9].trim());
                course.setRoom(record[10].isEmpty() ? "N/A" : record[10].trim());
                course.setCampus(record[11].trim());
                course.setClassType(record[12].trim());
                course.setMethod(record[13].trim());
                course.setDpOrIn(record[14].trim());
                course.setPot(record[15].trim());

                // Handle potentially missing numeric fields
                try {
                    int actual = record[16].isEmpty() ? 0 : Integer.parseInt(record[16].trim());
                    course.setActual(actual);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format for 'actual' in record: " + e.getMessage());
                    continue; // Skip record if this key field is invalid
                }

                try {
                    int max = record[17].isEmpty() ? 0 : Integer.parseInt(record[17].trim());
                    course.setMax(max);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format for 'max' in record: " + e.getMessage());
                    continue; // Skip record if this key field is invalid
                }

                // Add the successfully parsed course to the list
                courses.add(course);
            }


        } catch (IOException | CsvException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return courses;
    }
}