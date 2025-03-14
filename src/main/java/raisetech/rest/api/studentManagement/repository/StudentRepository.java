package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.Student;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> getAllStudents();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(Long id);

  @Select("SELECT * FROM students WHERE email = #{email}")
  Optional<Student> findByEmail(String email);

  /**
   * 受講生情報の登録
   * @param student 受講生情報
   */
  @Insert("INSERT INTO students (full_name, furigana, nick_name, email, living_area, age, gender, remark, isDeleted) VALUES (#{fullName}, #{furigana}, #{nickName}, #{email}, #{livingArea}, #{age}, #{gender}, #{remark}, #{isDeleted});")
  void registerStudent(Student student);


}
