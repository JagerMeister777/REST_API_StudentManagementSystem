package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.converter.StudentsCoursesConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
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
   * 受講生の全件取得
   *
   * @return 受講生情報のリスト
   */
  public List<Student> getAllStudents() {
    return studentRepository.getAllStudents();
  }

  public Student findByStudentId(int id) {
    return studentRepository.findByStudentId(id);
  }

  public Student updateStudent(int id, Student updateStudent) {
    validateDuplicateEmail(updateStudent.getEmail(), id);
    studentRepository.updateStudent(id, updateStudent);
    return updateStudent;
  }

  public int registerStudent(Student registerStudent) {
    validateDuplicateEmail(registerStudent.getEmail(), null);
    studentRepository.registerStudent(registerStudent);
    return findByEmail(registerStudent.getEmail()).getId();
  }

  public Student findByEmail(String email) {
    return studentRepository.findByEmail(email);
  }

  private void validateDuplicateEmail(String email, Integer studentId) {
    Student existEmailStudent = findByEmail(email);
    if (existEmailStudent != null && (studentId == null || !(existEmailStudent.getId() == studentId))) {
      throw new DuplicateStudentException("既にメールアドレスが使用されています。");
    }
  }
}
