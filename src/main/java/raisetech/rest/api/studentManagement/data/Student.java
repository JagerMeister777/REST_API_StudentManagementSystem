package raisetech.rest.api.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Schema(description = "GETメソッドが呼び出された時に、受講生の個人情報がバインドされるクラスです。")
public class Student {

  @Schema(description = "受講生ID", type = "int", example = "1")
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

  @Schema(description = "受講生情報の論理削除フラグ", type = "boolean", example = "false")
  private boolean isDeleted;

  public Student(String fullName, String furigana, String nickName, String email, String livingArea,
      int age, String gender, String remark, boolean isDeleted) {
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickName = nickName;
    this.email = email;
    this.livingArea = livingArea;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }

  public Student(int id, String fullName, String furigana, String nickName, String email,
      String livingArea, int age, String gender, String remark, boolean isDeleted) {
    this.id = id;
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickName = nickName;
    this.email = email;
    this.livingArea = livingArea;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }
}
