package raisetech.rest.api.studentManagement.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.repository.StudentRepository;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@Component
public class StudentsCoursesConverter {

  private final StudentRepository studentRepository;
  private final StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentsCoursesConverter(StudentRepository studentRepository,
      StudentsCoursesService studentsCoursesService) {
    this.studentRepository = studentRepository;
    this.studentsCoursesService = studentsCoursesService;
  }

  /**
   * 受講生情報と受講生コース情報を組み合わせるコンバーター
   * @return 全件受講生情報(List型のStudentWithCoursesDTO)
   */
  public List<StudentWithCoursesDTO> convertStudentWithCoursesDTO() {
    List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
    studentRepository.getAllStudents().forEach(student -> {
      List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(student.getId());
      studentWithCoursesDTOS.add(new StudentWithCoursesDTO(student,studentsCoursesList));
    });
    return studentWithCoursesDTOS;
  }
}
