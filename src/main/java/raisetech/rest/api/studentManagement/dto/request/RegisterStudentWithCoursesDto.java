package raisetech.rest.api.studentManagement.dto.request;

import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;


@Getter
@Setter
public class RegisterStudentWithCoursesDto {

  @Valid
  private RegisterStudentDto student;

  @Valid
  private List<StudentsCoursesDetail> studentsCourses;

  public RegisterStudentWithCoursesDto(RegisterStudentDto student,
      List<StudentsCoursesDetail> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
