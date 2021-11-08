package com.example.studentmanagement.dao;

import com.example.studentmanagement.model.CourseClass;
import org.springframework.data.repository.CrudRepository;

public interface CourseClassDao extends CrudRepository<CourseClass, Long> {
}
