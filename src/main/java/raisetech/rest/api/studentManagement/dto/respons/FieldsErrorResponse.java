package raisetech.rest.api.studentManagement.dto.respons;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "フィールドエラーレスポンス")
public class FieldsErrorResponse {

  @Schema(description = "HTTPステータスコード", example = "400")
  private int status;

  @Schema(description = "エラーの種類", example = "Bad Request")
  private String error;

  @Schema(description = "フィールドエラーメッセージ", example = "{fullName:名前は必須項目です。,furigana:フリガナは必須項目です。}")
  private Map<String, String> messageMap;

  @Schema(description = "エラー発生時のタイムスタンプ", example = "2025-03-27T12:00:00")
  private String timeStamp;

  public FieldsErrorResponse(int status, String error, Map<String, String> messageMap,
      String timeStamp) {
    this.status = status;
    this.error = error;
    this.messageMap = messageMap;
    this.timeStamp = timeStamp;
  }

  /**
   * timestampのフォーマット
   *
   * @return yyyy-MM-dd HH:mm:ss形式の時刻
   */
  public static String formattedTimestamp() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return now.format(formatter);
  }
}
