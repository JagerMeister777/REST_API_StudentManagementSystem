package raisetech.rest.api.studentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.rest.api.studentManagement.dto.request.RegisterStudentWithCoursesDto;
import raisetech.rest.api.studentManagement.dto.request.UpdateStudentWithCoursesDto;
import raisetech.rest.api.studentManagement.dto.respons.ErrorResponse;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDto;
import raisetech.rest.api.studentManagement.exception.GlobalExceptionHandler;
import raisetech.rest.api.studentManagement.exception.IsDeletedStudentException;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;
import raisetech.rest.api.studentManagement.service.StudentManagementFacade;

@RestController
@RequestMapping("/api")
@Validated
public class StudentController {

  /** サービスの統括を行うFacadeクラス */
  private final StudentManagementFacade facade;

  @Autowired
  public StudentController(StudentManagementFacade facade) {
    this.facade = facade;
  }

  @Operation(
      summary = "受講生情報の全件取得",
      description = "データベースに格納されている受講生の個人情報と受講しているコース情報の全件を取得します。",
      responses = {@ApiResponse(
          content = @Content(mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = StudentWithCoursesDto.class)
              )
          )
      )}
  )
  @GetMapping("/students")
  public ResponseEntity<List<StudentWithCoursesDto>> getAllStudents() {
    return ResponseEntity.ok(facade.getAllStudents());
  }

  /**
   * 特定の受講生情報を受講生IDで検索して、返します。
   * @param id 受講生ID
   * @return 受講生情報
   */
  @Operation(
      summary = "受講生IDで検索",
      description = "特定の受講生情報を受講生IDで検索します。IDが存在しなかったり、情報が削除されていた場合はエラーを発生させます。",
      responses = {
          @ApiResponse(
              responseCode = "200",description = "ok",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentWithCoursesDto.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",description = "IDに紐づく受講生情報が存在しなかった場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404",description = "IDに紐づく受講生情報が論理削除されてる場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
      }
  )
  @GetMapping("/students/{id}")
  public ResponseEntity<StudentWithCoursesDto> getOneStudent(@PathVariable int id) {
    return ResponseEntity.ok(facade.getOneStudent(id));
  }

  /**
   * 受講生情報と受講生コース情報の登録処理を受け取ります。
   * @param registerStudentWithCoursesDto 受講生情報と受講生コース情報がバインドされたDTO
   * @return 登録した受講生情報と受講生コース情報
   */
  @PostMapping("/students")
  public ResponseEntity<StudentWithCoursesDto> registerStudent(@RequestBody @Valid RegisterStudentWithCoursesDto registerStudentWithCoursesDto) {
    return ResponseEntity.ok(facade.registerHandling(registerStudentWithCoursesDto));
  }

  /**
   * 特定の受講生情報を更新して、更新結果を返します。
   * @param id 受講生ID
   * @param updateStudentWithCoursesDto 更新する受講生情報
   * @return 更新した受講生情報
   */
  @PutMapping("/students/{id}")
  public ResponseEntity<UpdateStudentWithCoursesDto> updateStudent(@PathVariable int id, @RequestBody @Valid UpdateStudentWithCoursesDto updateStudentWithCoursesDto) {
    return ResponseEntity.ok(facade.updateHandling(id,updateStudentWithCoursesDto));
  }

  /**
   * 受講生情報の論理削除を行います。
   * @param id 受講生ID
   * @return 完了メッセージ
   */
  @DeleteMapping("/students/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable int id) {
    String deleteStudentName = facade.getOneStudent(id).getStudent().getFullName();
    facade.deleteStudent(id);
    return ResponseEntity.ok(deleteStudentName + "の情報を削除しました。");
  }
}
