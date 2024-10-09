package org.example;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow access from all origins since it is local
public class CourseController {

    @GetMapping("/getCourses")
    public List<Course> getCourses() {
        System.out.println("Running the Course Controller");
        String scheduleText = PDFReader.extractTextFromPDF("Spring2024.pdf");
        return ScheduleParser.parseCourses(scheduleText);
    }
}
