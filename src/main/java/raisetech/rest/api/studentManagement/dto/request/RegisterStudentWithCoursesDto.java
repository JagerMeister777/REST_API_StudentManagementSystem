package raisetech.rest.api.studentManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;


@Getter
@Setter
@Schema(description = "受講生情報を登録する際にリクエストボディーがバインドされるクラスです。")
public class RegisterStudentWithCoursesDto {

  @Valid
  @Schema(description = "受講生の個人情報")
  private RegisterStudentDto student;

  @Valid
  @Schema(description = "受講生の受講するコース情報")
  private List<StudentsCoursesDetail> studentsCourses;

  public RegisterStudentWithCoursesDto(RegisterStudentDto student,
      List<StudentsCoursesDetail> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
