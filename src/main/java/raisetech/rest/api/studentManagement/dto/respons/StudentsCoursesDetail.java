package raisetech.rest.api.studentManagement.dto.respons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCoursesDetail {

  @NotBlank
  private String courseName;

  @NotNull
  private LocalDateTime courseStartDate;

  @NotNull
  private LocalDateTime courseEndDate;

  public StudentsCoursesDetail(String courseName,
      LocalDateTime courseStartDate, LocalDateTime courseEndDate) {
    this.courseName = courseName;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
