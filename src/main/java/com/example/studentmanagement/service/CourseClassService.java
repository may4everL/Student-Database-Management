package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.CourseClassDao;
import com.example.studentmanagement.exceptions.InvalidCourseClassException;
import com.example.studentmanagement.model.CourseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CourseClassService {

    private CourseClassDao courseClassDao;

    @Autowired
    public CourseClassService(CourseClassDao courseClassDao) {
        this.courseClassDao = courseClassDao;
    }

    public List<CourseClass> getAllClass() {
        return (List<CourseClass>) courseClassDao.findAll();
    }

    public CourseClass addCourseClass(CourseClass courseClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if(courseClass.getYear() < currentYear) {
            throw new InvalidCourseClassException("Cannot add class in the past");
        }
        if(courseClass.getYear() > currentYear + 1) {
            throw new InvalidCourseClassException("Cannot add class in the far future");
        }

        return courseClassDao.save(courseClass);
    }
}
