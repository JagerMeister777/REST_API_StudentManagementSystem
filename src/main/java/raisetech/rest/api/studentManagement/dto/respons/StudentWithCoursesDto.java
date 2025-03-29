package raisetech.rest.api.studentManagement.dto.respons;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.rest.api.studentManagement.data.Student;


@Getter
@Setter
@Schema(description = "GETメソッドが呼び出された時に、受講生情報がバインドされるクラスです。")
public class StudentWithCoursesDto {

  @Schema(description = "受講生の個人情報")
  private Student student;

  @Schema(description = "受講生の受講しているコース情報")
  private List<StudentsCoursesDetail> studentsCourses;

  public StudentWithCoursesDto(Student student, List<StudentsCoursesDetail> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}
