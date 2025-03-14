package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.rest.api.studentManagement.converter.StudentWithCoursesDTOConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exceptions.DuplicateStudentException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentsCoursesService studentsCoursesService;
  private final StudentWithCoursesDTOConverter converter;

  @Autowired
  public StudentService(StudentRepository studentRepository,
      StudentsCoursesService studentsCoursesService, StudentWithCoursesDTOConverter converter) {
    this.studentRepository = studentRepository;
    this.studentsCoursesService = studentsCoursesService;
    this.converter = converter;
  }

  /**
   * 受講生の全件取得
   * @return 受講生情報のリスト
   */
  public List<StudentWithCoursesDTO> getAllStudents() {
    List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
    studentRepository.getAllStudents().forEach(student -> {
      List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(student.getId());
        studentWithCoursesDTOS.add(converter.convertStudentWithCoursesDTO(student,studentsCoursesList));
      });
    return studentWithCoursesDTOS;
  }

  /**
   * 特定の受講生情報の取得
   * @param id 受講生ID
   * @return 受講生情報
   */
  public Student getOneStudent(int id) {
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
