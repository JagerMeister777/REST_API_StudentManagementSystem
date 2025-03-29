package raisetech.rest.api.studentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.rest.api.studentManagement.dto.request.RegisterStudentWithCoursesDto;
import raisetech.rest.api.studentManagement.dto.request.UpdateStudentWithCoursesDto;
import raisetech.rest.api.studentManagement.dto.respons.ErrorResponse;
import raisetech.rest.api.studentManagement.dto.respons.FieldsErrorResponse;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDto;
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
      description = "受講生の個人情報と受講しているコース情報の全件取得をします。",
      responses = {
          @ApiResponse(
              content = @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(
                      schema = @Schema(implementation = StudentWithCoursesDto.class)
                  )
              )
          )
      }
  )
  @GetMapping("/students")
  public ResponseEntity<List<StudentWithCoursesDto>> getAllStudents() {
    return ResponseEntity.ok(facade.getAllStudents());
  }

  @Operation(
      summary = "受講生IDで検索",
      description = "特定の受講生情報を受講生IDで検索します。IDが存在しなかったり、情報が削除されていた場合はエラーを発生させます。",
      responses = {
          @ApiResponse(
              responseCode = "200", description = "ok",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentWithCoursesDto.class)
              )
          ),
          @ApiResponse(
              responseCode = "404", description = "IDに紐づく受講生情報が存在しなかった場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404", description = "IDに紐づく受講生情報が論理削除されてる場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  @GetMapping("/students/{id}")
  public ResponseEntity<StudentWithCoursesDto> getOneStudent(@PathVariable int id) {
    return ResponseEntity.ok(facade.getOneStudent(id));
  }

  @Operation(
      summary = "受講生情報の登録処理",
      description = "受講生情報の個人情報と受講するコース情報の登録処理を行います。",
      responses = {
          @ApiResponse(
              responseCode = "200", description = "ok",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentWithCoursesDto.class)
              )
          ),
          @ApiResponse(
              responseCode = "400", description = "登録するメールアドレスが既に使用されていた場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400", description = "登録するコースを既に受講していた場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400", description = "リクエストパラメーターでblankやNullがあった場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = FieldsErrorResponse.class)
              )
          )
      }
  )
  @PostMapping("/students")
  public ResponseEntity<StudentWithCoursesDto> registerStudent(
      @RequestBody @Valid RegisterStudentWithCoursesDto registerStudentWithCoursesDto) {
    return ResponseEntity.ok(facade.registerHandling(registerStudentWithCoursesDto));
  }

  @Operation(
      summary = "受講生情報の更新",
      description = "特定の受講生情報の更新を行います。",
      responses = {
          @ApiResponse(
              responseCode = "200", description = "ok",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = StudentWithCoursesDto.class)
              )
          ),
          @ApiResponse(
              responseCode = "400", description = "更新するメールアドレスが既に使用されていた場合のエラー（自分自身であればエラーは発生しない。）",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400", description = "リクエストパラメーターとパスパラメーターのIDが一致しなかった場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )

      }
  )
  @PutMapping("/students/{id}")
  public ResponseEntity<UpdateStudentWithCoursesDto> updateStudent(@PathVariable int id,
      @RequestBody @Valid UpdateStudentWithCoursesDto updateStudentWithCoursesDto) {
    return ResponseEntity.ok(facade.updateHandling(id, updateStudentWithCoursesDto));
  }

  @Operation(
      summary = "受講生情報の論理削除",
      description = "受講生情報の論理削除を行います。",
      responses = {
          @ApiResponse(
              responseCode = "200", description = "田中太郎の情報を削除しました。"
          ),
          @ApiResponse(
              responseCode = "404", description = "IDに紐づく受講生情報が存在しなかった場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "404", description = "IDに紐づく受講生情報が論理削除されてる場合のエラー",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorResponse.class)
              )
          )
      }
  )
  @DeleteMapping("/students/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable int id) {
    String deleteStudentName = facade.getOneStudent(id).getStudent().getFullName();
    facade.deleteStudent(id);
    return ResponseEntity.ok(deleteStudentName + "の情報を削除しました。");
  }
}
