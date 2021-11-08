package com.example.studentmanagement.service;

import com.example.studentmanagement.dao.CourseClassDao;
import com.example.studentmanagement.dao.StudentDao;
import com.example.studentmanagement.exceptions.InvalidCourseClassException;
import com.example.studentmanagement.exceptions.StudentEmptyNameException;
import com.example.studentmanagement.exceptions.StudentNonExistenceException;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.CourseClass;
import com.example.studentmanagement.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;
    private CourseClassDao courseClassDao;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentDao studentDao, CourseClassDao courseClassDao,
                          StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.courseClassDao = courseClassDao;
        this.studentMapper = studentMapper;
    }

    public Student addStudent(Student student) {
        if(student.getName().isEmpty()) {
            throw new StudentEmptyNameException("Student name cannot be empty!");
        }
        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        if(student.getId() == null || !studentDao.existsById(student.getId())) {
            throw new StudentNonExistenceException("Cannot find student ID!");
        }

        return studentDao.save(student);
    }

    public Student assignClass(Long studentId, Long classId) {
        if(!studentDao.existsById(studentId)) {
            throw new StudentNonExistenceException("Cannot find student ID: " + studentId);
        }
        if(!courseClassDao.existsById(classId)) {
            throw new InvalidCourseClassException("Cannot find class ID: " + classId);
        }

        Student student = getStudentById(studentId).get();
        CourseClass courseClass = courseClassDao.findById(classId).get();

        student.setCourseClass(courseClass);
        return studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public List<Student> getStudentsByName(String name) {
        return studentDao.findByName(name);
    }

    public List<Student> getStudentsContainName(String name) {
        return studentMapper.getStudentsContainStrInName("%" + name + "%");
    }

    public List<Student> getStudentsInClass(int year, int id) {
        return studentMapper.getStudentsInClass(year, id);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }
}
