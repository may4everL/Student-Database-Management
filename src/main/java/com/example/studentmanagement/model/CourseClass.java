package com.example.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="course_class")
public class CourseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "courseClass")
    @JsonIgnore
    public List<Student> students;

    public CourseClass(Long id, Integer courseId, String courseName) {
        this.id = id;
        this.year = year;
        this.courseName = courseName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CourseClass() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString(){
        String str;
        str = "Primary ID: " + getId();
        str += "Year: " + getYear();
        str += " Course Name: " + getCourseName();
        return str;
    }
}
