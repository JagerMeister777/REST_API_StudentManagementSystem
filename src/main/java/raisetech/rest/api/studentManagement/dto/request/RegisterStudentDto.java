package raisetech.rest.api.studentManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterStudentDto {
  @NotNull(message = "名前は必須です。")
  @NotBlank(message = "名前は空にできません。")
  private String fullName;

  @NotNull(message = "ふりがなは必須です。")
  @NotBlank(message = "ふりがなは空にできません。")
  private String furigana;

  @NotNull(message = "ニックネームは必須です。")
  @NotBlank(message = "ニックネームは空にできません。")
  private String nickName;

  @NotNull(message = "メールアドレスは必須です。")
  @NotBlank(message = "メールアドレスは空にできません。")
  @Email(message = "正しいメールアドレスを入力してください。")
  private String email;

  @NotNull(message = "居住地は必須です。")
  @NotBlank(message = "居住地は空にできません。")
  private String livingArea;

  @Min(value = 0, message = "年齢は0以上である必要があります。")
  private int age;

  @NotNull(message = "性別は必須です。")
  @NotBlank(message = "性別は空にできません。")
  private String gender;

  private String remark;
}
