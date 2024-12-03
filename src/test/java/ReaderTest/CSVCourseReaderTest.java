package ReaderTest;

import com.opencsv.exceptions.CsvException;
import org.example.CSVCourseReader;
import org.example.Course;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVCourseReaderTest {

    /**
     * Helper method to create a temporary CSV file for testing.
     *
     * @return the path to the created CSV file.
     * @throws IOException if there is an issue creating the file.
     */
    private String createTestCSVFile() throws IOException {
        // Define headers based on your example
        String[] headers = {
                "CRN", "Subject", "Department", "Number", "Section", "Instructor",
                "Days", "Start Time", "End Time", "Building", "Room", "Campus",
                "Class Type", "Method", "DP or IN", "PoT", "Actual", "Max", "Color"
        };

        // Define rows based on your example
        String[][] rows = {
                {"30208", "A S", "AS", "1000", "251", "Ekren, Brenton J", "__T_R__", "2:00 PM", "5:30 PM",
                        "ASBN", "150", "601 University Dr, San Marcos, TX 78666", "LAB", "FTF", "", "1", "54", "90", "Red"},
                {"40086", "AAS", "DLA", "4388", "251", "Goldstone, Dwonna Naomi", "_______", "", "", "ARR", "ARR",
                        "601 University Dr, San Marcos, TX 78666", "IND", "FTF", "DP", "1", "4", "5", "Blue"},
                {"30126", "ACC", "ACC", "5361", "251", "Rutledge, Robert W", "___W___", "6:30 PM", "9:20 PM",
                        "MCOY", "224", "601 University Dr, San Marcos, TX 78666", "SEM", "FTF", "DP", "1", "18", "20", "Red"},
                {"39216", "ANTH", "ANTH", "3331D", "251", "Fancher, James Patrick", "__T_R__", "11:00 AM", "12:20 PM",
                        "ELA", "226", "601 University Dr, San Marcos, TX 78666", "LEC", "FTF", "", "1", "14", "20", "Green"}
        };

        // Define the path to save the CSV file in the resources folder
        String resourcesPath = Paths.get("src", "main", "resources", "test_courses.csv").toString();
        File csvFile = new File(resourcesPath);

        // Ensure the directory exists
        if (!csvFile.getParentFile().exists()) {
            csvFile.getParentFile().mkdirs();
        }

        // Write the data to the CSV file
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write the headers
            writer.append(String.join(",", headers)).append("\n");

            // Write each row
            for (String[] row : rows) {
                writer.append(String.join(",", row)).append("\n");
            }
        }

        System.out.println("CSV file created successfully at: " + csvFile.getAbsolutePath());
        return "test_courses.csv"; // Return only the file name for reading
    }

    @Test
    void testReadCoursesFromCSV() throws IOException, CsvException {
        // Generate the test CSV file
        String csvFileName = "test_courses.csv";

        // Read courses from the CSV file using the provided reader
        List<Course> courses = CSVCourseReader.readCoursesFromCSV(csvFileName);

        // Assert that the courses list is not null and has the expected size
        assertNotNull(courses, "Courses list should not be null");
        assertEquals(4, courses.size(), "Expected 4 courses in the list");

        // Validate the first course
        Course firstCourse = courses.get(0);
        assertAll("Validate first course",
                () -> assertEquals("30208", firstCourse.getCrn()),
                () -> assertEquals("A S", firstCourse.getSubject()),
                () -> assertEquals("AS", firstCourse.getDepartment()),
                () -> assertEquals("1000", firstCourse.getNumber()),
                () -> assertEquals("251", firstCourse.getSection()),
                () -> assertEquals("Ekren, Brenton J", firstCourse.getInstructor()),
                () -> assertEquals("__T_R__", firstCourse.getDays()),
                () -> assertEquals("2:00 PM", firstCourse.getStartTime()),
                () -> assertEquals("5:30 PM", firstCourse.getEndTime()),
                () -> assertEquals("ASBN", firstCourse.getBuilding()),
                () -> assertEquals("150", firstCourse.getRoom()),
                () -> assertEquals(54, firstCourse.getActual()),
                () -> assertEquals("Red", firstCourse.getColor())               
        
                
        );

        // Validate the second course
        Course secondCourse = courses.get(1);
        assertAll("Validate second course",
                () -> assertEquals("40086", secondCourse.getCrn()),
                () -> assertEquals("AAS", secondCourse.getSubject()),
                () -> assertEquals("DLA", secondCourse.getDepartment()),
                () -> assertEquals("4388", secondCourse.getNumber()),
                () -> assertEquals("251", secondCourse.getSection()),
                () -> assertEquals("Goldstone, Dwonna Naomi", secondCourse.getInstructor()),
                () -> assertEquals("_______", secondCourse.getDays()),
                () -> assertEquals("", secondCourse.getStartTime()),
                () -> assertEquals("", secondCourse.getEndTime()),
                () -> assertEquals("ARR", secondCourse.getBuilding()),
                () -> assertEquals("ARR", secondCourse.getRoom()),
                () -> assertEquals(4, secondCourse.getActual()),
                () -> assertEquals("Blue", secondCourse.getColor())
        );

        // Validate the third course
        Course thirdCourse = courses.get(2);
        assertAll("Validate third course",
                () -> assertEquals("30126", thirdCourse.getCrn()),
                () -> assertEquals("ACC", thirdCourse.getSubject()),
                () -> assertEquals("ACC", thirdCourse.getDepartment()),
                () -> assertEquals("5361", thirdCourse.getNumber()),
                () -> assertEquals("251", thirdCourse.getSection()),
                () -> assertEquals("Rutledge, Robert W", thirdCourse.getInstructor()),
                () -> assertEquals("___W___", thirdCourse.getDays()),
                () -> assertEquals("6:30 PM", thirdCourse.getStartTime()),
                () -> assertEquals("9:20 PM", thirdCourse.getEndTime()),
                () -> assertEquals("MCOY", thirdCourse.getBuilding()),
                () -> assertEquals("224", thirdCourse.getRoom()),
                () -> assertEquals(18, thirdCourse.getActual()),
                () -> assertEquals("Red", thirdCourse.getColor())
        );

        // Validate the fourth course
        Course fourthCourse = courses.get(3);
        assertAll("Validate fourth course",
                () -> assertEquals("39216", fourthCourse.getCrn()),
                () -> assertEquals("ANTH", fourthCourse.getSubject()),
                () -> assertEquals("ANTH", fourthCourse.getDepartment()),
                () -> assertEquals("3331D", fourthCourse.getNumber()),
                () -> assertEquals("251", fourthCourse.getSection()),
                () -> assertEquals("Fancher, James Patrick", fourthCourse.getInstructor()),
                () -> assertEquals("__T_R__", fourthCourse.getDays()),
                () -> assertEquals("11:00 AM", fourthCourse.getStartTime()),
                () -> assertEquals("12:20 PM", fourthCourse.getEndTime()),
                () -> assertEquals("ELA", fourthCourse.getBuilding()),
                () -> assertEquals("226", fourthCourse.getRoom()),
                () -> assertEquals(14, fourthCourse.getActual()),
                () -> assertEquals("Green", fourthCourse.getColor())
        );

        System.out.println("Test passed: All courses were read and validated successfully.");
    }

    @Test
    void testFilterCoursesByColor() throws IOException, CsvException {
        String csvFileName = "test_courses.csv";
        List<Course> courses = CSVCourseReader.readCoursesFromCSV(csvFileName);

        // Filter courses by Red color
        List<Course> redCourses = CSVCourseReader.filterCoursesByColor(courses, "Red");

        // Assert that only the courses with "Red" color are returned
        assertNotNull(redCourses, "Filtered list should not be null");
        assertEquals(2, redCourses.size(), "There should be 2 courses with Red color");

        // Check that all courses in the filtered list have the correct color
        for (Course course : redCourses) {
            assertEquals("Red", course.getColor(), "Course color should be Red");
        }

        // Optionally, test another color (e.g., Blue)
        List<Course> blueCourses = CSVCourseReader.filterCoursesByColor(courses, "Blue");
        assertEquals(1, blueCourses.size(), "There should be 1 course with Blue color");
        assertEquals("Blue", blueCourses.get(0).getColor(), "Course color should be Blue");
    }
}