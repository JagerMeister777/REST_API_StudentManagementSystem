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
}
