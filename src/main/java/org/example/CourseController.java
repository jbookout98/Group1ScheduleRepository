package org.example;

import java.util.List;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow access from all origins since it is local
public class CourseController {

    private List<Course> courses; // Correct: Should be List<Course>

    /**
     * Initializes the CourseController and loads courses from a CSV file at startup.
     *
     * <p>This constructor reads the CSV file named "courses.csv" using {@link CSVCourseReader} and
     * stores the parsed {@link Course} objects in a list for later use. It also logs the number
     * of loaded courses.</p>
     *
     * <h3>Example CSV File:</h3>
     * The CSV file should contain a header and course data in the expected format.
     */
    public CourseController() {
        System.out.println("Initializing CourseController and loading courses from CSV.");
        courses = CSVCourseReader.readCoursesFromCSV("courses.csv"); // Correct assignment
        System.out.println("Loaded " + courses.size() + " courses.");
    }


    /**
     * Retrieves a course based on the given CRN.
     *
     * <p>This method accepts an optional CRN parameter and returns the first course that matches
     * the CRN. If no CRN is provided, or if no course matches the given CRN, the method returns
     * {@code null}.</p>
     *
     * <h3>Request Parameters:</h3>
     * <ul>
     *   <li>{@code crn} (optional) - The CRN of the course to retrieve.</li>
     * </ul>
     *
     * <h3>Responses:</h3>
     * <ul>
     *   <li>If a valid CRN is provided and a matching course is found, the course is returned.</li>
     *   <li>If no matching course is found, {@code null} is returned.</li>
     *   <li>If no CRN is provided, {@code null} is returned.</li>
     * </ul>
     *
     * @param crn the optional CRN of the course to retrieve.
     * @return the {@link Course} object with the matching CRN, or {@code null} if no match is found.
     *
     * <h3>Example Usage:</h3>
     * <pre>
     * {@code
     * // GET request to /api/getCourses?crn=12345
     * // Returns a Course object with CRN 12345.
     * }
     * </pre>
     */
    @GetMapping("/getCourses")
    public Course getCourses(@RequestParam(required = false) String crn) {
        System.out.println("Running the Course Controller");

        if (crn != null && !crn.trim().isEmpty()) {
            // Find the first course that matches the given CRN
            Course filteredCourse = courses.stream()
                    .filter(course -> course.getCrn().trim().equalsIgnoreCase(crn.trim()))
                    .findFirst()
                    .orElse(null);

            if (filteredCourse == null) {
                System.out.println("No course found with CRN: " + crn);
            } else {
                System.out.println("Filtered Course: " + filteredCourse);
            }

            return filteredCourse;
        }

        // If no CRN is provided, return null or handle it as needed.
        System.out.println("No CRN provided, returning null.");
        return null;
    }


}
