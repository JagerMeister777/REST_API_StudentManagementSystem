package raisetech.rest.api.studentManagement.dto;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@Getter
@Setter
public class StudentWithCoursesDTO {

  private Student student;
  private List<StudentsCourses> studentsCourses;

  public StudentWithCoursesDTO(Student student, List<StudentsCourses> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
