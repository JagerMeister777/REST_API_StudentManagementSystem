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
  private UpdateStudentDto updateStudentDto;
  private List<StudentsCoursesDetail> studentsCourses;

  public UpdateStudentWithCoursesDto(UpdateStudentDto updateStudentDto,
      List<StudentsCoursesDetail> studentsCourses) {
    this.updateStudentDto = updateStudentDto;
    this.studentsCourses = studentsCourses;
  }
}
