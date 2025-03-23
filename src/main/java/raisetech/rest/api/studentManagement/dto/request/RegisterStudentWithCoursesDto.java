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
  private RegisterStudentDto registerStudentDto;
  private List<StudentsCoursesDetail> studentsCourses;

  public RegisterStudentWithCoursesDto(RegisterStudentDto registerStudentDto,
      List<StudentsCoursesDetail> studentsCourses) {
    this.registerStudentDto = registerStudentDto;
    this.studentsCourses = studentsCourses;
  }
}
