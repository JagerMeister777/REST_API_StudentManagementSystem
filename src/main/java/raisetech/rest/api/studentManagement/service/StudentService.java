package raisetech.rest.api.studentManagement.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.exceptions.DuplicateStudentException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  /**
   * 受講生の全件取得
   * @return 受講生情報のリスト
   */
  public List<Student> getAllStudents() {
    return studentRepository.getAllStudents();
  }

  /**
   * 特定の受講生情報の取得
   * @param id 受講生ID
   * @return 受講生情報
   */
  public Student getOneStudent(Long id) {
    return studentRepository.findByStudentId(id);
  }

  @Transactional
  public Student registerStudent(Student student) {
    Optional<Student> existStudent = studentRepository.findByEmail(student.getEmail());
    if (existStudent.isPresent()) {
      throw new DuplicateStudentException("このメールアドレスは既に使用されています。");
    }
    studentRepository.registerStudent(student);
    return student;
  }
}
