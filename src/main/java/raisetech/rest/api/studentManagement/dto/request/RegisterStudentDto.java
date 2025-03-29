package raisetech.rest.api.studentManagement.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "受講生情報を登録する際に、個人情報がバインドされるクラスです。")
public class RegisterStudentDto {

  @Schema(description = "氏名", type = "String", example = "山田太郎")
  @NotBlank
  private String fullName;

  @Schema(description = "フリガナ", type = "String", example = "ヤマダタロウ")
  @NotBlank
  private String furigana;

  @Schema(description = "ニックネーム", type = "String", example = "タロー")
  @NotBlank
  private String nickName;

  @Schema(description = "メールアドレス", type = "String", example = "taro.yamada@example.com")
  @NotBlank
  @Email
  private String email;

  @Schema(description = "居住地域", type = "String", example = "東京都港区")
  @NotBlank
  private String livingArea;

  @Schema(description = "年齢", type = "int", example = "27")
  @Min(value = 0, message = "年齢は0以上である必要があります。")
  private int age;

  @Schema(description = "性別", type = "String", example = "男")
  @NotBlank
  private String gender;

  @Schema(description = "備考", type = "String", example = "Javaフルコースを完走したら、AWSコースを受講予定")
  private String remark;
}
