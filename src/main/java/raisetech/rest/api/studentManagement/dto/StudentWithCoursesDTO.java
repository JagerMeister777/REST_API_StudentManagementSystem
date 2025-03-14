package raisetech.rest.api.studentManagement.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.data.Student;

@Getter
@Setter
public class StudentWithCoursesDTO {

  private Student student;
  private List<StudentsCoursesDTO> StudentsCoursesDTOS;

  public StudentWithCoursesDTO(Student student, List<StudentsCoursesDTO> StudentsCoursesDTOS) {
    this.student = student;
    this.StudentsCoursesDTOS = StudentsCoursesDTOS;
  }
}
