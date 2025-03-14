package raisetech.rest.api.studentManagement.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.StudentsCoursesDTO;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.service.CourseService;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@Component
public class StudentWithCoursesDTOConverter {

  private final CourseService courseService;
  private final StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentWithCoursesDTOConverter(CourseService courseService,
      StudentsCoursesService studentsCoursesService) {
    this.courseService = courseService;
    this.studentsCoursesService = studentsCoursesService;
  }

  /**
   * StudentWithCoursesDTOへ変換するコンバーター
   * @param student 受講生情報
   * @param studentsCourses 受講生コース情報
   * @return StudentWithCoursesDTO
   */
  public StudentWithCoursesDTO convertStudentWithCoursesDTO(Student student, List<StudentsCourses> studentsCourses) {
    List<StudentsCoursesDTO> studentsCoursesDTOS = new ArrayList<>();
    studentsCourses.forEach(s -> {
      StudentsCoursesDTO studentsCoursesDTO = new StudentsCoursesDTO(
          courseService.getOneCourseName(s.getCourseId()),
          s.getCourseStartDate(),
          s.getCourseEndDate()
      );
      studentsCoursesDTOS.add(studentsCoursesDTO);
    });
    return new StudentWithCoursesDTO(student,studentsCoursesDTOS);
  }
}
