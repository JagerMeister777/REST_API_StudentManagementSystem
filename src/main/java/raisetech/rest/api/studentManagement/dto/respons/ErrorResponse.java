package raisetech.rest.api.studentManagement.dto.respons;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "エラーレスポンス")
public class ErrorResponse {

  @Schema(description = "HTTPステータスコード", example = "404")
  private int status;

  @Schema(description = "エラーの種類", example = "Not Found Student")
  private String error;

  @Schema(description = "エラーメッセージ", example = "受講生情報が存在しません。")
  private String message;

  @Schema(description = "エラー発生時のタイムスタンプ", example = "2025-03-27T12:00:00")
  private String timeStamp;

  public ErrorResponse(int status, String error, String message, String timeStamp) {
    this.status = status;
    this.error = error;
    this.message = message;
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
