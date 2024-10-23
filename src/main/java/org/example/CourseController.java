package org.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow access from all origins since it is local
public class CourseController {

    @GetMapping("/getCourses")
    public Course getCourses(@RequestParam(required = false) String crn) {

        String scheduleText = PDFReader.extractTextFromPDF("Spring2024.pdf");

        List<Course> courses = ScheduleParser.parseCourses(scheduleText);
        System.out.println("Running the Course Controller");
        // Print the full schedule for debugging
        Course toReturnCourse;
        // If a CRN is provided, filter the courses by CRN
        if (crn != null && !crn.isEmpty()) {
            courses = courses.stream()
                    .filter(course -> course.getCrn().equals(crn))
                    .collect(Collectors.toList());
        }
        System.out.println(courses.get(0));
        return courses.get(0);
    }
}
