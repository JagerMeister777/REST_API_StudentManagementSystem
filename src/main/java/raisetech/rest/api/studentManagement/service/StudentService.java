package raisetech.rest.api.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.converter.StudentsCoursesConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentsCoursesService studentsCoursesService;
  private final StudentsCoursesConverter converter;

  @Autowired
  public StudentService(StudentRepository studentRepository,
      StudentsCoursesService studentsCoursesService, StudentsCoursesConverter converter) {
    this.studentRepository = studentRepository;
    this.studentsCoursesService = studentsCoursesService;
    this.converter = converter;
  }

  /**
   * 受講生の全件取得
   * @return 受講生情報のリスト
   */
  public List<StudentWithCoursesDTO> getAllStudents() {
    return converter.convertStudentWithCoursesDTO();
  }

  /**
   * 特定の受講生情報の取得
   * @param id 受講生ID
   * @return 受講生情報
   */
  public StudentWithCoursesDTO getOneStudent(int id) {
    Student student = studentRepository.findByStudentId(id);
    if (student == null) {
      throw new StudentNotFoundException("受講生情報が存在しませんでした。");
    }
    return new StudentWithCoursesDTO(
        student,
        converter.convertStudentsCoursesDetail(studentsCoursesService.getOneStudentsCoursesList(id)));
  }

  public Student updateStudent(Student updateStudent) {
    return studentRepository.updateStudent(updateStudent);
  }
}
