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
@Schema(description = "受講生の個人情報を更新する時に、バインドされるクラスです。")
public class UpdateStudentDto {

  @Schema(description = "受講生ID" ,type = "int" ,example = "1")
  @Min(value = 1, message = "IDは1以上である必要があります。")
  private int id;

  @Schema(description = "氏名", type = "String", example = "山田太郎")
  private String fullName;

  @Schema(description = "フリガナ", type = "String", example = "ヤマダタロウ")
  private String furigana;

  @Schema(description = "ニックネーム", type = "String", example = "タロー")
  private String nickName;

  @Schema(description = "メールアドレス", type = "String", example = "taro.yamada@example.com")
  private String email;

  @Schema(description = "居住地域", type = "String", example = "東京都港区")
  private String livingArea;

  @Schema(description = "年齢", type = "int", example = "27")
  private int age;

  @Schema(description = "性別", type = "String", example = "男")
  private String gender;

  @Schema(description = "備考", type = "String", example = "Javaフルコースを完走したら、AWSコースを受講予定")
  private String remark;
}
