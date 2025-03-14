package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.rest.api.studentManagement.data.Student;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students WHERE isDeleted = false")
  List<Student> getAllStudents();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(int id);

  /**
   * 受講生情報の更新
   * @param student 受講生情報
   */
  @Update("UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nick_name = #{nickName}, email = #{email}, living_area = #{livingArea}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE id = #{id}")
  Student updateStudent(Student student);

}
