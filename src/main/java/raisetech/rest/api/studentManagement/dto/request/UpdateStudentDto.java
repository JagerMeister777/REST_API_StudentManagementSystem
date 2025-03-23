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
  @NotNull(message = "IDは必須です。")
  private Integer id;

  @NotBlank(message = "名前は必須です。")
  private String fullName;

  @NotBlank(message = "ふりがなは必須です。")
  private String furigana;

  @NotBlank(message = "ニックネームは必須です。")
  private String nickName;

  @NotBlank(message = "メールアドレスは必須です。")
  @Email(message = "正しいメールアドレスを入力してください。")
  private String email;

  @NotBlank(message = "居住地は必須です。")
  private String livingArea;

  @Min(value = 0, message = "年齢は0以上である必要があります。")
  private Integer age;

  @NotBlank(message = "性別は必須です。")
  private String gender;

  private String remark;
}
