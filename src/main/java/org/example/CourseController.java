package org.example;

import java.util.List;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow access from all origins since it is local
public class CourseController {

    private List<Course> courses; // Correct: Should be List<Course>
    public CourseDAO courseDAO;

    public CourseController() {
        System.out.println("Initializing CourseController and loading courses from CSV.");
        courses = CSVCourseReader.readCoursesFromCSV("courses.csv"); // Correct assignment
        System.out.println("Loaded " + courses.size() + " courses.");
    }


    /**
     * Retrieves a course based on the provided CRN.
     *
     * @param crn the optional CRN of the course to retrieve; can be null or empty.
     * @return the {@link Course} object with the matching CRN, or {@code null} if no match is found.
     */
    @GetMapping("/getCourses")
    public Course getCourses(@RequestParam(required = false) String crn) {
        System.out.println("Running the Course Controller");

        if (crn != null && !crn.trim().isEmpty()) {
            // Find the first course that matches the given CRN
            Course filteredCourse = CourseDAO.getCourseByCRN(crn);
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
