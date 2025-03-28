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
import raisetech.rest.api.studentManagement.dto.respons.ErrorResponse;
import raisetech.rest.api.studentManagement.dto.respons.FieldsErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 既に登録されているメールアドレスが登録されるときのエラーハンドリング
   *
   * @param ex DuplicateStudentException
   * @return Bad Request
   */
  @ExceptionHandler(DuplicateStudentException.class)
  public ResponseEntity<ErrorResponse> DuplicateStudentException(
      DuplicateStudentException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        400,
        "Bad Request",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * バリデーションエラー（@Valid / @Validated）の処理
   *
   * @param ex MethodArgumentNotValidException
   * @return Bad Request
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<FieldsErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> fieldErrors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      fieldErrors.put(error.getField(), error.getDefaultMessage());
    }
    FieldsErrorResponse fieldsErrorResponse = new FieldsErrorResponse(
        400,
        "Bad Request",
        fieldErrors,
        FieldsErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(fieldsErrorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * 登録するコースを既に受講しているときのエラーハンドリング
   *
   * @param ex InvalidStudentCourseCombinationException
   * @return Bad Request
   */
  @ExceptionHandler(InvalidStudentCoursesCombinationException.class)
  public ResponseEntity<ErrorResponse> invalidStudentCourseCombinationException(
      InvalidStudentCoursesCombinationException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        400,
        "Bad Request",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * パラメータとリクエストのIDが一致していない時のエラーハンドリング
   *
   * @param ex UnMatchIdException
   * @return Bad Request
   */
  @ExceptionHandler(UnMatchIdException.class)
  public ResponseEntity<ErrorResponse> UnMatchIdException(UnMatchIdException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        400,
        "Bad Request",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * 受講生情報が見つからなかった時のエラーハンドリング
   *
   * @param ex StudentNotFoundException
   * @return Not Found Error
   */
  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        404,
        "Not Found Student",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * コース情報が見つからなかった時のエラーハンドリング
   *
   * @param ex CourseNotFoundException
   * @return Not Found Error
   */
  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleCourseNotFound(CourseNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        404,
        "Not Found Course",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * 受講生情報が論理削除されている時のエラーハンドリング
   *
   * @param ex IsDeletedStudentException
   * @return Not Found Error
   */
  @ExceptionHandler(IsDeletedStudentException.class)
  public ResponseEntity<ErrorResponse> isDeletedStudent(IsDeletedStudentException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        404,
        "Not Found Student",
        ex.getMessage(),
        ErrorResponse.formattedTimestamp()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
