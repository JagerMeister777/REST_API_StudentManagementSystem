package raisetech.rest.api.studentManagement.data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsCourses {

  private int id;
  private int studentId;
  private int courseId;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;
}
