package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.Course;

@Mapper
public interface CourseRepository {

  @Select("SELECT * FROM courses")
  List<Course> getAllCourse();

  @Select("SELECT * FROM courses WHERE id = #{id}")
  Course findByCourseId(int id);

  @Select("SELECT * FROM courses WHERE name = #{name}")
  Course findByCourseName(String name);
}
