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
  @Update("UPDATE students SET full_name = #{student.fullName}, furigana = #{student.furigana}, nick_name = #{student.nickName}, email = #{student.email}, living_area = #{student.livingArea}, age = #{student.age}, gender = #{student.gender}, remark = #{student.remark}, isDeleted = #{student.isDeleted} WHERE id = #{id}")
  void updateStudent(int id,Student student);

}
