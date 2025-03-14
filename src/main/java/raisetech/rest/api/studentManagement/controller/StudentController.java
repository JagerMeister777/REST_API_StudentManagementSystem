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
import raisetech.rest.api.studentManagement.converter.StudentWithCoursesDTOConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.dto.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exceptions.DuplicateStudentException;
import raisetech.rest.api.studentManagement.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

  private final StudentService studentService;
  private final StudentWithCoursesDTOConverter converter;

  @Autowired
  public StudentController(StudentService studentService,
      StudentWithCoursesDTOConverter converter) {
    this.studentService = studentService;
    this.converter = converter;
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

  @GetMapping("/student/{id}")
  public ResponseEntity<Student> getOneStudent(@PathVariable int id) {
    return ResponseEntity.ok(studentService.getOneStudent(id));
  }

  @PostMapping("/student/register")
  public ResponseEntity<?> registerStudent(@RequestBody Student student) {
    Student registerStudent = studentService.registerStudent(student);
    return new ResponseEntity<>(registerStudent, HttpStatus.CREATED);
  }
}
