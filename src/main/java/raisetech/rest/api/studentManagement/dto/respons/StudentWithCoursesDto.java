package raisetech.rest.api.studentManagement.dto.respons;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.data.Student;


@Getter
@Setter
public class StudentWithCoursesDto {

  private Student student;
  private List<StudentsCoursesDetail> studentsCourses;

  public StudentWithCoursesDto(Student student, List<StudentsCoursesDetail> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
