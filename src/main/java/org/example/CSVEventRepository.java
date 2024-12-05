package org.example;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;

@Repository
public class CSVEventRepository implements EventRepository {

    // Path to the CSV file
    private static final String CSV_FILE_PATH = "src/main/resources/courses.csv";  // Adjust path if needed

    @Override
    public List<Course> getUpcomingEvents(LocalDateTime now, int intervalInMinutes) {
        List<Course> upcomingCourses = new ArrayList<>();

        // Calculate the end time of the interval (current time + interval in minutes)
        LocalDateTime endInterval = now.plusMinutes(intervalInMinutes);

        try (CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            boolean isFirstLine = true;

            // Read the CSV file line by line
            while ((nextLine = csvReader.readNext()) != null) {
                // Skip the header row
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Create a Course object from the CSV data
                Course course = new Course();
                course.setCrn(nextLine[0]);
                course.setSubject(nextLine[1]);
                course.setDepartment(nextLine[2]);
                course.setNumber(nextLine[3]);
                course.setSection(nextLine[4]);
                course.setInstructor(nextLine[5]);
                course.setDays(nextLine[6]);
                course.setStartTime(nextLine[7]);
                course.setEndTime(nextLine[8]);
                course.setBuilding(nextLine[9]);
                course.setRoom(nextLine[10]);
                course.setCampus(nextLine[11]);
                course.setClassType(nextLine[12]);
                course.setMethod(nextLine[13]);
                course.setDpOrIn(nextLine[14]);
                course.setPot(nextLine[15]);
                course.setActual(Integer.parseInt(nextLine[16]));
                course.setMax(Integer.parseInt(nextLine[17]));
                course.setColor(nextLine[18]);

                // Convert the start time to LocalDateTime and compare it with current time
                LocalDateTime courseStartTime = convertToLocalDateTime(course.getStartTime());

                // If the course's start time is within the specified interval, add it to the list
                if (courseStartTime.isAfter(now) && courseStartTime.isBefore(endInterval)) {
                    upcomingCourses.add(course);
                }
            }
        } catch (IOException | CsvValidationException e) {
            // Handle both IOException and CsvValidationException
            e.printStackTrace();
        }

        return upcomingCourses;
    }

    // Helper method to convert start time string (e.g., "2:00 PM") to LocalDateTime
    private LocalDateTime convertToLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a"); // 12-hour format like "2:00 PM"
        // Assuming the date for the course is today (this can be modified if needed)
        String dateTimeString = LocalDateTime.now().toLocalDate() + " " + time;
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
    }
}
