package raisetech.rest.api.studentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.service.StudentService;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@RestController
@RequestMapping("/api")
public class StudentController {

  private final StudentService studentService;
  private final StudentsCoursesService studentsCoursesService;

  @Autowired
  public StudentController(StudentService studentService,
      StudentsCoursesService studentsCoursesService) {
    this.studentService = studentService;
    this.studentsCoursesService = studentsCoursesService;
  }

  /**
   * 受講生情報の全件取得
   *
   * @return 受講生情報のリスト
   */
  @GetMapping("/students")
  public ResponseEntity<List<StudentWithCoursesDTO>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAllStudents());
  }

  /**
   * 特定の受講生情報の取得
   * @param id 受講生ID
   * @return 受講生情報
   */
  @GetMapping("/student/{id}")
  public ResponseEntity<StudentWithCoursesDTO> getOneStudent(@PathVariable int id) {
    return ResponseEntity.ok(studentService.getOneStudent(id));
  }

  @PostMapping("/student/register")
  public ResponseEntity<?> registerStudent(@RequestBody StudentWithCoursesDTO studentWithCoursesDTO) {
    studentService.registerStudent(studentWithCoursesDTO.getStudent());
    studentsCoursesService.registerStudentsCourses(studentWithCoursesDTO.getStudentsCourses());
    return new ResponseEntity<>(studentWithCoursesDTO,HttpStatus.CREATED);
  }
}
