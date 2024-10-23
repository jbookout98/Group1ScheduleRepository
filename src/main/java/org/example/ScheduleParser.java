package org.example;

import java.util.ArrayList;
import java.util.List;

public class ScheduleParser {

    private static final int[] FIELD_LENGTHS = {5, 4, 4, 3, -1, 2, 3, 3, -1,2,2,7, 7, 7, 4, 4, 4,Integer.MAX_VALUE};
    // Method to parse the extracted text and convert it into a list of Course objects
    public static List<Course> parseCourses(String scheduleText) {
        List<Course> courses = new ArrayList<>();

        // Split the text into lines
        String[] lines = scheduleText.split("\n");
        boolean inScheduleSection = false; // Flag to indicate if we are in the schedule section

        System.out.println("Starting to parse the schedule...");
        System.out.println(lines[3] );
        for (String line : lines) {
            // Skip empty lines
            if (line.trim().isEmpty()) {
                continue;
            }

            // Check if we are at the beginning of the schedule section
            if (line.contains("Spring 2024")) {
                inScheduleSection = true;

                continue;
            }
            if (line.contains("Schedule of Classes")){
                inScheduleSection = true;

                continue;
            }



            // If we have reached the schedule section, check for header lines and skip them
            if (line.contains("Crn") && line.contains("Subject") && line.contains("Number")) {

                continue;
            }

            // Parse each valid line dynamically and add the course to the list
            Course course = parseCourseFromLine(line);
            if (course != null) {
                courses.add(course);
            } else {
                System.out.println("Failed to parse line: " + line);
            }
        }

        System.out.println("Finished parsing the schedule. Total courses parsed: " + courses.size());
        return courses;
    }

    // Method to parse a single line into a Course object
    // Method to parse a single line into a Course object
    // Method to parse a single line into a Course object using part tracking
    private static Course parseCourseFromLine(String line) {
        // Trim the line to remove leading and trailing whitespace
        line = line.trim();

        // Integer to track the current part index
        int partIndex = 0;

        // Create an array or list to store the parts we extract
        List<String> parts = new ArrayList<>();

        // While there is still line left to parse, continue extracting parts
        while (!line.isEmpty() && partIndex < FIELD_LENGTHS.length) {
            // Skip leading spaces before each part
            line = line.trim();

            // Determine the number of characters to extract based on partIndex
            int lengthToExtract = FIELD_LENGTHS[partIndex];

            String part;

            if (lengthToExtract == -1) {
                // Special case: for partIndex == 4, extract text until the next space
                int firstSpaceIndex = line.indexOf(' ');

                // If there's no space left in the line, take the remaining part
                if (firstSpaceIndex == -1) {
                    part = line;
                    line = "";  // No more line left to process
                } else {
                    part = line.substring(0, firstSpaceIndex);  // Extract until the next space
                    line = line.substring(firstSpaceIndex).trim();  // Update the remaining line
                }
            } else {
                // If the remaining line is shorter than the expected length, take the rest of the line
                if (lengthToExtract == Integer.MAX_VALUE || line.length() <= lengthToExtract) {
                    part = line; // Take the remaining text if it's less than or equal to the expected length
                    line = "";   // No more line left to process
                } else {
                    part = line.substring(0, lengthToExtract);  // Extract the part
                    line = line.substring(lengthToExtract).trim();  // Update the remaining line
                }
            }

            // Add the extracted part to the list and increase the part index
            parts.add(part.trim());

            partIndex++;
        }


        // Assign extracted parts to variables (handle missing fields safely)
        String crn = parts.size() > 0 ? parts.get(0) : "";
        String subject = parts.size() > 1 ? parts.get(1) : "";
        String number = parts.size() > 2 ? parts.get(2) : "";
        String section = parts.size() > 3 ? parts.get(3) : "";
        String campusLoc = parts.size() > 5 ? parts.get(5) : "";  // Special case handled above
        if (campusLoc.contains("M")){
            campusLoc="601 University Dr, San Marcos, TX 78666";
        }else if(campusLoc.contains("R")){
            campusLoc="1555 University Blvd, Round Rock, TX 78665";
        }else{
            campusLoc="Online";
        }
        String days = parts.size() > 11 ? parts.get(11) : "";
        String startTime = parts.size() > 12 ? parts.get(12) : "";
        String endTime = parts.size() > 13 ? parts.get(13) : "";
        String building = parts.size() > 14 ? parts.get(14) : "";
        String room = parts.size() > 15 ? parts.get(15) : "";
        String instructor = parts.size() > 16 ? parts.get(16) : "";

        for (int i = 15; i < parts.size(); i++) {
            instructor += " " + parts.get(i);
        }

        // Return the new course object
        return new Course(crn, subject, number, section, instructor.trim(), days, startTime, endTime, building, room, campusLoc);
    }

}
