package raisetech.rest.api.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.exception.DuplicateStudentException;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  /**
   * 受講生情報の全件取得をします。
   *
   * @return すべての受講生情報リスト
   */
  public List<Student> getAllStudents() {
    return studentRepository.getAllStudents();
  }

  /**
   * 受講生IDで受講生情報を検索をします。
   * @param id 受講生ID
   * @return 受講生情報
   */
  public Student findByStudentId(int id) {
    return studentRepository.findByStudentId(id);
  }

  /**
   * 受講生情報をメールアドレスで検索します。
   * @param email メールアドレス
   * @return 受講生情報
   */
  public Student findByEmail(String email) {
    return studentRepository.findByEmail(email);
  }

  /**
   * 受講生情報の更新をします。
   *
   * @param id 受講生ID
   * @param updateStudent 更新する受講生情報
   */
  public void updateStudent(int id, Student updateStudent) {
    validateDuplicateEmail(updateStudent.getEmail(), id);
    studentRepository.updateStudent(id, updateStudent);
  }

  /**
   * 受講生情報の登録をします。
   * @param registerStudent 登録する受講生情報
   * @return 登録した受講生情報
   */
  public int registerStudent(Student registerStudent) {
    validateDuplicateEmail(registerStudent.getEmail(),null);
    studentRepository.registerStudent(registerStudent);
    return findByEmail(registerStudent.getEmail()).getId();
  }

  /**
   * 登録または更新する際に、既に登録されているメールアドレスかどうかチェックします。
   * 更新の時にexistEmailStudentで見つかった受講生情報が、更新する受講生情報と同じなら、例外は発生しません。
   * @param email メールアドレス
   * @param studentId 受講生ID
   */
  private void validateDuplicateEmail(String email, Integer studentId) {
    Student existEmailStudent = findByEmail(email);
    if (existEmailStudent != null && (studentId == null || !(existEmailStudent.getId()
        == studentId))) {
      throw new DuplicateStudentException("既にメールアドレスが使用されています。");
    }
  }
}
