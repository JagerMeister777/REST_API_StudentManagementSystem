package raisetech.rest.api.studentManagement.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourses {

  private int id;
  private int studentId;
  private int courseId;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

  public StudentsCourses(int studentId, int courseId, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.studentId = studentId;
    this.courseId = courseId;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }

  public StudentsCourses(int id, int studentId, int courseId, LocalDateTime courseStartDate,
      LocalDateTime courseEndDate) {
    this.id = id;
    this.studentId = studentId;
    this.courseId = courseId;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
