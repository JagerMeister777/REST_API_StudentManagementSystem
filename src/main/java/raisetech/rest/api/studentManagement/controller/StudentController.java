package raisetech.rest.api.studentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDto;
import raisetech.rest.api.studentManagement.service.StudentManagementFacade;

@RestController
@RequestMapping("/api")
public class StudentController {

  private final StudentManagementFacade facade;

  @Autowired
  public StudentController(StudentManagementFacade facade) {
    this.facade = facade;
  }

  /**
   * 登録されているすべての受講生情報を返します。
   *
   * @return 受講生情報のリスト
   */
  @GetMapping("/students")
  public ResponseEntity<List<StudentWithCoursesDto>> getAllStudents() {
    return ResponseEntity.ok(facade.getAllStudents());
  }

  /**
   * 受講生情報と受講生コース情報の登録処理を受け取ります。
   * @param registerStudentWithCoursesDto 受講生情報と受講生コース情報がバインドされたDTO
   * @return 登録した受講生情報と受講生コース情報
   */
  @PostMapping("/student")
  public ResponseEntity<StudentWithCoursesDto> registerStudentWithCoursesDTO(@RequestBody StudentWithCoursesDto registerStudentWithCoursesDto) {
    return ResponseEntity.ok(facade.registerHandling(registerStudentWithCoursesDto));
  }

  /**
   * 特定の受講生情報を受講生IDで検索して、返します。
   * @param id 受講生ID
   * @return 受講生情報
   */
  @GetMapping("/student/{id}")
  public ResponseEntity<StudentWithCoursesDto> getOneStudent(@PathVariable int id) {
    return ResponseEntity.ok(facade.getOneStudent(id));
  }

  /**
   * 特定の受講生情報を更新して、更新結果を返します。
   * @param id 受講生ID
   * @param studentWithCoursesDTO 更新する受講生情報
   * @return 更新した受講生情報
   */
  @PutMapping("/student/{id}")
  public ResponseEntity<StudentWithCoursesDto> updateStudent(@PathVariable int id,@RequestBody StudentWithCoursesDto studentWithCoursesDTO) {
    return ResponseEntity.ok(facade.updateHandling(id,studentWithCoursesDTO));
  }
}
