package raisetech.rest.api.studentManagement.dto.respons;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCoursesDetail {

  private String courseName;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

  public StudentsCoursesDetail(String courseName,
      LocalDateTime courseStartDate, LocalDateTime courseEndDate) {
    this.courseName = courseName;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
