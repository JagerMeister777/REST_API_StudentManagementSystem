package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import raisetech.rest.api.studentManagement.data.Student;

@Mapper
public interface StudentRepository {

  /**
   * 登録しているすべての受講生情報を取得します。
   * @return 受講生情報の全件リスト
   */
  List<Student> getAllStudents();

  /**
   * 特定の受講生情報をIDで検索し、取得します。
   * @param id 受講生ID
   * @return 受講生情報
   */
  Student findByStudentId(int id);

  /**
   * 特定の受講生情報をメールアドレスで検索し、取得します。
   * @param email メールアドレス
   * @return 受講生情報
   */
  Optional<Student> findByEmail(String email);

  /**
   * 受講生情報の登録をします。
   * @param student 登録する受講生情報
   */
  void registerStudent(Student student);

  /**
   * 受講生情報の更新をします。
   * @param student 更新する受講生情報
   */
  void updateStudent(Student student);

}
