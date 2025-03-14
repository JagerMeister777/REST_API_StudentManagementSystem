package raisetech.rest.api.studentManagement.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 既に登録されているメールアドレスが登録されるときのエラーハンドリング
   * @param ex DuplicateStudentException
   * @return ConflictError
   */
  @ExceptionHandler(DuplicateStudentException.class)
  public ResponseEntity<Map<String,Object>> handleExistStudent(DuplicateStudentException ex) {
    Map<String,Object> errorResponse = new HashMap<>();
    errorResponse.put("status",409);
    errorResponse.put("error","Conflict");
    errorResponse.put("message",ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  /**
   * timestampのフォーマット
   * @return yyyy-MM-dd HH:mm:ss形式の時刻
   */
  public String formattedTimestamp() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return now.format(formatter);
  }
}
