package org.example;

import java.util.ArrayList;
import java.util.List;

public class ScheduleParser {

    // Method to parse the extracted text using the column positions
    public static List<Course> parseCourses(String scheduleText) {
        List<Course> courses = new ArrayList<>();

        // Split the text into lines
        String[] lines = scheduleText.split("\n");
        boolean inScheduleSection = false;
        int[] columnPositions = null;

        System.out.println("Starting to parse the schedule...");

        for (String line : lines) {
            // Print the current line being processed for debugging

            // Check if we are at the beginning of the schedule section
            if (line.contains("Spring 2024") && line.contains("Schedule of Classes")) {
                inScheduleSection = true;
                continue;
            }

            // Find the header line and determine the column positions
            if (inScheduleSection && line.contains("Crn") && line.contains("Subject") && line.contains("Number")) {

                columnPositions = getColumnPositions(line);

                continue;
            }

            // If we're in the schedule section and have found the columns, start parsing the course data
            if (inScheduleSection && columnPositions != null) {
                // Extract course data based on the column positions
                String crn = extractColumnData(line, columnPositions[0], columnPositions[1]);
                String subject = extractColumnData(line, columnPositions[1], columnPositions[2]);
                String number = extractColumnData(line, columnPositions[2], columnPositions[3]);
                String section = extractColumnData(line, columnPositions[3], columnPositions[4]);
                String days = extractColumnData(line, columnPositions[7], columnPositions[8]);
                String startTime = extractColumnData(line, columnPositions[8], columnPositions[9]);
                String endTime = extractColumnData(line, columnPositions[9], columnPositions[10]);
                String building = extractColumnData(line, columnPositions[10], columnPositions[11]);
                String room = extractColumnData(line, columnPositions[11], columnPositions[12]);
                String instructor = extractColumnData(line, columnPositions[12], line.length());

                // Debug information for each course data line

                courses.add(new Course(crn, subject, number, section, instructor, days, startTime, endTime, building, room));
            }
        }

        System.out.println("Finished parsing the schedule.");
        return courses;
    }

    // Method to determine the start positions of each column based on the header line
    private static int[] getColumnPositions(String headerLine) {
        int[] positions = new int[13];
        positions[0] = headerLine.indexOf("Crn");
        positions[1] = headerLine.indexOf("Subject");
        positions[2] = headerLine.indexOf("Number");
        positions[3] = headerLine.indexOf("Sect");
        positions[4] = headerLine.indexOf("Dept");
        positions[5] = headerLine.indexOf("Campus");
        positions[6] = headerLine.indexOf("Type");
        positions[7] = headerLine.indexOf("Days");
        positions[8] = headerLine.indexOf("Begin");
        positions[9] = headerLine.indexOf("End");
        positions[10] = headerLine.indexOf("Bldg");
        positions[11] = headerLine.indexOf("Room");
        positions[12] = headerLine.indexOf("Instructor");
        return positions;
    }

    // Method to extract data from a specific column based on its start and end positions
    private static String extractColumnData(String line, int start, int end) {
        if (start < 0 || start >= line.length()) {
            return "";
        }
        end = Math.min(end, line.length());
        return line.substring(start, end).trim();
    }
}
