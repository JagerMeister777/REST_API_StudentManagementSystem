package raisetech.rest.api.studentManagement.service;

import static raisetech.rest.api.studentManagement.constants.CustomExceptionMessageConst.DUPLICATE_STUDENT_EXCEPTION;
import static raisetech.rest.api.studentManagement.constants.CustomExceptionMessageConst.IS_DELETED_STUDENT_MESSAGE;
import static raisetech.rest.api.studentManagement.constants.CustomExceptionMessageConst.NOT_FOUND_STUDENT_MESSAGE;
import static raisetech.rest.api.studentManagement.constants.CustomExceptionMessageConst.UN_MATCH_ID_EXCEPTION;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.exception.DuplicateStudentException;
import raisetech.rest.api.studentManagement.exception.IsDeletedStudentException;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;
import raisetech.rest.api.studentManagement.exception.UnMatchIdException;
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
   * 受講生情報が存在しない場合と、論理削除されていたら例外をスローします。
   * @param id 受講生ID
   * @return 受講生情報
   */
  public Optional<Student> findByStudentId(int id) {
    Optional<Student> student = studentRepository.findByStudentId(id);
    if (student.isPresent() && student.get().isDeleted()) {
      throw new IsDeletedStudentException(IS_DELETED_STUDENT_MESSAGE);
    } else if (student.isEmpty()) {
      throw new StudentNotFoundException(NOT_FOUND_STUDENT_MESSAGE);
    }
    return student;
  }

  /**
   * 受講生情報をメールアドレスで検索します。
   * @param email メールアドレス
   * @return 受講生情報
   */
  public Optional<Student> findByEmail(String email) {
    return studentRepository.findByEmail(email);
  }

  /**
   * 受講生情報の更新をします。
   *
   * @param id 受講生ID
   * @param updateStudent 更新する受講生情報
   */
  public void updateStudent(int id, Student updateStudent) {
    if (id != updateStudent.getId()) {
      throw new UnMatchIdException(UN_MATCH_ID_EXCEPTION);
    }
    validateDuplicateEmail(updateStudent.getEmail(), id);
    studentRepository.updateStudent(updateStudent);
  }

  /**
   * 受講生情報の登録をします。
   * @param registerStudent 登録する受講生情報
   * @return 登録した受講生情報
   */
  public int registerStudent(Student registerStudent) {
    validateDuplicateEmail(registerStudent.getEmail(),registerStudent.getId());
    studentRepository.registerStudent(registerStudent);
    return registerStudent.getId();
  }

  /**
   * 受講生情報の論理削除をします。
   * @param id 受講生ID
   */
  public void deleteStudent(int id) {
    Optional<Student> student = findByStudentId(id);
    if (student.isPresent() && student.get().isDeleted()) {
      throw new IsDeletedStudentException(IS_DELETED_STUDENT_MESSAGE);
    } else if (student.isEmpty()) {
      throw new StudentNotFoundException(NOT_FOUND_STUDENT_MESSAGE);
    }
    studentRepository.deleteStudent(id);
  }

  /**
   * 登録または更新する際に、既に登録されているメールアドレスかどうかチェックします。
   * 更新の時にexistEmailStudentで見つかった受講生情報が、更新する受講生情報と同じなら、例外は発生しません。
   * @param email メールアドレス
   * @param studentId 受講生ID
   */
  private void validateDuplicateEmail(String email, int studentId) {
    Optional<Student> existEmailStudent = findByEmail(email);
    if (existEmailStudent.isPresent()) {
      if (existEmailStudent.get().getId() != studentId) {
        throw new DuplicateStudentException(DUPLICATE_STUDENT_EXCEPTION);
      }
    }
  }
}
