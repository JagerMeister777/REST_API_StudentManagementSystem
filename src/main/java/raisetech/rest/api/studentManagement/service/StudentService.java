package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exceptions.DuplicateStudentException;
import raisetech.rest.api.studentManagement.exceptions.StudentNotFoundException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentService(StudentRepository studentRepository,
      StudentsCoursesService studentsCoursesService) {
    this.studentRepository = studentRepository;
    this.studentsCoursesService = studentsCoursesService;
  }

  /**
   * 受講生の全件取得
   * @return 受講生情報のリスト
   */
  public List<StudentWithCoursesDTO> getAllStudents() {
    List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
    studentRepository.getAllStudents().forEach(student -> {
      List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(student.getId());
        studentWithCoursesDTOS.add(new StudentWithCoursesDTO(student,studentsCoursesList));
      });
    return studentWithCoursesDTOS;
  }

  /**
   * 特定の受講生情報の取得
   * @param id 受講生ID
   * @return 受講生情報
   */
  public StudentWithCoursesDTO getOneStudent(int id) {
    Student student = studentRepository.findByStudentId(id);
    if (Optional.ofNullable(student).isEmpty()) {
      throw new StudentNotFoundException("受講生情報が存在しませんでした。");
    }
    List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(id);
    return new StudentWithCoursesDTO(student,studentsCoursesList);
  }

  @Transactional
  public void registerStudent(Student student) {
    Student existStudent = studentRepository.findByEmail(student.getEmail());
    if (Optional.ofNullable(existStudent).isPresent()) {
      throw new DuplicateStudentException("このメールアドレスは既に使用されています。");
    }
    studentRepository.registerStudent(student);
  }
}
