package raisetech.rest.api.studentManagement.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.Course;

@Mapper
public interface CourseRepository {

  @Select("SELECT * FROM courses WHERE id = #{id}")
  Course findByCourseId(int id);

  @Select("SELECT * FROM courses WHERE course_name = #{courseName}")
  Course findByCourseName(String courseName);
}
