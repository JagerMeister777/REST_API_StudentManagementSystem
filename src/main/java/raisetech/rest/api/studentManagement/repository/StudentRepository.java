package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.Student;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students WHERE isDeleted = false")
  List<Student> getAllStudents();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(int id);
}
