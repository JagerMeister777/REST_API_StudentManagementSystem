package raisetech.rest.api.studentManagement.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Getter
@Setter
public class UpdateStudentDTO {
  private Student student;
  private List<StudentsCourses> studentsCoursesList;

  public UpdateStudentDTO(Student student, List<StudentsCourses> studentsCoursesList) {
    this.student = student;
    this.studentsCoursesList = studentsCoursesList;
  }
}
