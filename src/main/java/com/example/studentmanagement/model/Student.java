package com.example.studentmanagement.model;


import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="course_class_id")
    public CourseClass courseClass;

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setCourseClass(CourseClass courseClass) {
        this.courseClass = courseClass;
    }

    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str;
        str = "Primary ID: " + getId();
        str += " Name: " + getName();
        return str;
    }
}
