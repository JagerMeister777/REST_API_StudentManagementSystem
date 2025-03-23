package raisetech.rest.api.studentManagement.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 既に登録されているメールアドレスが登録されるときのエラーハンドリング
   *
   * @param ex DuplicateStudentException
   * @return Bad Request
   */
  @ExceptionHandler(DuplicateStudentException.class)
  public ResponseEntity<Map<String, Object>> DuplicateStudentException(
      DuplicateStudentException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 400);
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * バリデーションエラー（@Valid / @Validated）の処理
   * @param ex MethodArgumentNotValidException
   * @return Bad Request
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 400);
    errorResponse.put("error", "Bad Request");
    Map<String, String> fieldErrors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      fieldErrors.put(error.getField(), error.getDefaultMessage());
    }
    errorResponse.put("message", fieldErrors);
    errorResponse.put("timestamp", formattedTimestamp());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * 登録するコースを既に受講しているときのエラーハンドリング
   *
   * @param ex InvalidStudentCourseCombinationException
   * @return Bad Request
   */
  @ExceptionHandler(InvalidStudentCoursesCombinationException.class)
  public ResponseEntity<Map<String, Object>> invalidStudentCourseCombinationException(
      InvalidStudentCoursesCombinationException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 400);
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * パラメータとリクエストのIDが一致していない時のエラーハンドリング
   * @param ex UnMatchIdException
   * @return Bad Request
   */
  @ExceptionHandler(UnMatchIdException.class)
  public ResponseEntity<Map<String, Object>> UnMatchIdException(UnMatchIdException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 400);
    errorResponse.put("error", "Bad Request");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * 受講生情報が見つからなかった時のエラーハンドリング
   *
   * @param ex StudentNotFoundException
   * @return Not Found Error
   */
  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleStudentNotFound(StudentNotFoundException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 404);
    errorResponse.put("error", "Not Found Student");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * コース情報が見つからなかった時のエラーハンドリング
   *
   * @param ex CourseNotFoundException
   * @return Not Found Error
   */
  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleCourseNotFound(CourseNotFoundException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 404);
    errorResponse.put("error", "Not Found Student");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * 受講生情報が論理削除されている時のエラーハンドリング
   * @param ex IsDeletedStudentException
   * @return Not Found Error
   */
  @ExceptionHandler(IsDeletedStudentException.class)
  public ResponseEntity<Map<String, Object>> isDeletedStudent(IsDeletedStudentException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    errorResponse.put("status", 404);
    errorResponse.put("error", "Not Found Student");
    errorResponse.put("message", ex.getMessage());
    errorResponse.put("timestamp", formattedTimestamp());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * timestampのフォーマット
   *
   * @return yyyy-MM-dd HH:mm:ss形式の時刻
   */
  public String formattedTimestamp() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return now.format(formatter);
  }
}
