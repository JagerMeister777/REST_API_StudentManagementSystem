package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentsCoursesRepository {

  @Select("SELECT * FROM students_courses WHERE student_id = #{id}")
  List<StudentsCourses> findByStudentId(int id);
}
