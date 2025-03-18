package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.rest.api.studentManagement.data.Student;

@Mapper
public interface StudentRepository {

  /**
   * 登録しているすべての受講生情報を取得します。
   * @return 受講生情報の全件リスト
   */
  @Select("SELECT * FROM students WHERE isDeleted = false")
  List<Student> getAllStudents();

  /**
   * 特定の受講生情報をIDで検索し、取得します。
   * @param id 受講生ID
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findByStudentId(int id);

  /**
   * 特定の受講生情報をメールアドレスで検索し、取得します。
   * @param email メールアドレス
   * @return 受講生情報
   */
  @Select("SELECT * FROM students WHERE email = #{email}")
  Student findByEmail(String email);

  /**
   * 受講生情報の登録をします。
   * @param student 登録する受講生情報
   */
  @Insert("INSERT INTO students (full_name, furigana, nick_name, email, living_area, age, gender, remark, isDeleted) VALUES (#{fullName}, #{furigana}, #{nickName}, #{email}, #{livingArea}, #{age}, #{gender}, #{remark}, #{isDeleted});")
  void registerStudent(Student student);

  /**
   * 受講生情報の更新をします。
   * @param student 更新する受講生情報
   */
  @Update("UPDATE students SET full_name = #{student.fullName}, furigana = #{student.furigana}, nick_name = #{student.nickName}, email = #{student.email}, living_area = #{student.livingArea}, age = #{student.age}, gender = #{student.gender}, remark = #{student.remark}, isDeleted = #{student.isDeleted} WHERE id = #{id}")
  void updateStudent(int id,Student student);

}
