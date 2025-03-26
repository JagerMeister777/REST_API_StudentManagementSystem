package raisetech.rest.api.studentManagement.dto.request;

import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;


@Getter
@Setter
public class UpdateStudentWithCoursesDto {

  @Valid
  private UpdateStudentDto student;

  @Valid
  private List<StudentsCoursesDetail> studentsCourses;

  public UpdateStudentWithCoursesDto(UpdateStudentDto student,
      List<StudentsCoursesDetail> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
