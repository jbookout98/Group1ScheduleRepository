package org.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow access from all origins since it is local
public class CourseController {

    private List<Course> courses; // Correct: Should be List<Course>

    // Constructor to load courses once at startup
    public CourseController() {
        System.out.println("Initializing CourseController and loading courses from CSV.");
        courses = CSVCourseReader.readCoursesFromCSV("courses.csv"); // Correct assignment
        System.out.println("Loaded " + courses.size() + " courses.");
    }

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
