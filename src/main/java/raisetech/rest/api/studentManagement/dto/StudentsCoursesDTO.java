package raisetech.rest.api.studentManagement.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCoursesDTO {

  private String courseName;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

  public StudentsCoursesDTO(String courseName, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.courseName = courseName;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
