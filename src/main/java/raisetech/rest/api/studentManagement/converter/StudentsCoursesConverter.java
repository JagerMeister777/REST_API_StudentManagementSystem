package raisetech.rest.api.studentManagement.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;
import raisetech.rest.api.studentManagement.repository.CourseRepository;
import raisetech.rest.api.studentManagement.repository.StudentRepository;
import raisetech.rest.api.studentManagement.repository.StudentsCoursesRepository;
import raisetech.rest.api.studentManagement.service.CourseService;
import raisetech.rest.api.studentManagement.service.StudentService;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@Component
public class StudentsCoursesConverter {

  private final StudentsCoursesRepository studentsCoursesRepository;
  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;

  @Autowired
  public StudentsCoursesConverter(StudentsCoursesRepository studentsCoursesRepository,
      StudentRepository studentRepository, CourseRepository courseRepository) {
    this.studentsCoursesRepository = studentsCoursesRepository;
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  /**
   * 受講生情報と受講生コース情報を組み合わせるコンバーター
   * @return 全件受講生情報(List型のStudentWithCoursesDTO)
   */
  public List<StudentWithCoursesDTO> convertStudentWithCoursesDTO() {
    List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();

    studentRepository.getAllStudents().forEach(student -> {
      studentWithCoursesDTOS.add(new StudentWithCoursesDTO(
          student,
          convertStudentsCoursesDetail(
              studentsCoursesRepository.findByStudentId(student.getId())
          )
      ));
    });
    return studentWithCoursesDTOS;
  }

  public List<StudentsCoursesDetail> convertStudentsCoursesDetail(List<StudentsCourses> studentsCoursesList) {
    List<StudentsCoursesDetail> studentsCoursesDetails = new ArrayList<>();
    studentsCoursesList.forEach(studentsCourses -> {
      String studentName = studentRepository.findByStudentId(studentsCourses.getStudentId()).getFullName();
      String courseName = courseRepository.findByCourseId(studentsCourses.getCourseId()).getName();
      StudentsCoursesDetail studentsCoursesDetail = new StudentsCoursesDetail(
          studentName,
          courseName,
          studentsCourses.getCourseStartDate(),
          studentsCourses.getCourseEndDate()
      );
      studentsCoursesDetails.add(studentsCoursesDetail);
    });
    return studentsCoursesDetails;
  }
}
