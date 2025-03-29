package raisetech.rest.api.studentManagement.dto.respons;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "受講生情報を登録する際に、受講するコース情報がバインドされるクラスです。")
public class StudentsCoursesDetail {

  @Schema(description = "コース名", type = "String", example = "Javaフルコース")
  @NotBlank
  private String courseName;

  @Schema(description = "受講開始日", type = "String", example = "2025-02-04T17:21:00")
  @NotNull
  private LocalDateTime courseStartDate;

  @Schema(description = "受講終了日", type = "String", example = "2025-02-28T17:21:00")
  @NotNull
  private LocalDateTime courseEndDate;

  public StudentsCoursesDetail(String courseName,
      LocalDateTime courseStartDate, LocalDateTime courseEndDate) {
    this.courseName = courseName;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
  }
}
