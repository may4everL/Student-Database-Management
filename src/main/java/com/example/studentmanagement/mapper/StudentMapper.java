package com.example.studentmanagement.mapper;

import com.example.studentmanagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("SELECT * FROM student where name LIKE #{name}")
    public List<Student> getStudentsContainStrInName(@Param("name") String name);

    @Select("SELECT * FROM student WHERE course_class_id IN " +
            "(SELECT id FROM course_class WHERE year = #{year} AND id = #{id})")
    public List<Student> getStudentsInClass(@Param("year") int year,
                                            @Param("id") int id);
}
