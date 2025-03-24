package raisetech.rest.api.studentManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentDto {

  @Min(value = 1, message = "IDは1以上である必要があります。")
  private int id;

  @NotBlank
  private String fullName;

  @NotBlank
  private String furigana;

  @NotBlank
  private String nickName;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String livingArea;

  @Min(value = 0, message = "年齢は0以上である必要があります。")
  private int age;

  @NotBlank
  private String gender;

  private String remark;
}
