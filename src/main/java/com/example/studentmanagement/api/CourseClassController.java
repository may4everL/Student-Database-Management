package com.example.studentmanagement.api;


import com.example.studentmanagement.exceptions.InvalidCourseClassException;
import com.example.studentmanagement.model.CourseClass;
import com.example.studentmanagement.service.CourseClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class CourseClassController {
    private CourseClassService courseClassService;

    @Autowired
    public CourseClassController(CourseClassService courseClassService) {
        this.courseClassService = courseClassService;
    }

    @GetMapping
    public List<CourseClass> getAllClasses() {
        return courseClassService.getAllClass();
    }

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody CourseClass courseClass) {
        try {
            CourseClass savedCourseClass = courseClassService.addCourseClass(courseClass);
            return ResponseEntity.ok("Added class: " + savedCourseClass.toString());
        } catch(InvalidCourseClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
