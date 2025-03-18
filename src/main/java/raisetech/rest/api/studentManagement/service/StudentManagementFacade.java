package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.rest.api.studentManagement.converter.StudentsCoursesConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;

/**
 * 各サービスを統括して管理するためのサービスです。
 * 循環依存防止のために実装しました。
 */
@Service
public class StudentManagementFacade {

  private final StudentService studentService;
  private final StudentsCoursesService studentsCoursesService;
  private final CourseService courseService;
  private final StudentsCoursesConverter converter;

  @Autowired
  public StudentManagementFacade(StudentService studentService,
      StudentsCoursesService studentsCoursesService, CourseService courseService,
      StudentsCoursesConverter converter) {
    this.studentService = studentService;
    this.studentsCoursesService = studentsCoursesService;
    this.courseService = courseService;
    this.converter = converter;
  }

  /**
   * 受講生情報の全件取得をします。
   *
   * @return 受講生情報のリスト
   */
  public List<StudentWithCoursesDTO> getAllStudents() {
    List<Student> studentList = studentService.getAllStudents();
    List<StudentWithCoursesDTO> studentWithCoursesDTOS = new ArrayList<>();
    studentList.forEach(student -> {
      List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(
          student.getId());
      studentWithCoursesDTOS.add(
          converter.convertStudentWithCoursesDTO(student, studentsCoursesList,
              courseService.getAllCourses()));
    });
    return studentWithCoursesDTOS;
  }

  /**
   * 特定の受講生情報の取得をします。
   *
   * @param id 受講生ID
   * @return 受講生情報
   */
  public StudentWithCoursesDTO getOneStudent(int id) {
    Student student = studentService.findByStudentId(id);
    if (student == null) {
      throw new StudentNotFoundException("受講生情報が存在しませんでした。");
    }
    return converter.convertStudentWithCoursesDTO(
            student,
            studentsCoursesService.getOneStudentsCoursesList(id),
            courseService.getAllCourses()
    );
  }

  /**
   * 受講生情報と受講生コース情報の登録処理をハンドリングします。
   * @param registerStudentWithCoursesDTO 受講生情報と受講生コース情報がバインドされたDTO
   * @return 登録した受講生情報と受講生コース情報
   */
  @Transactional
  public StudentWithCoursesDTO registerHandling(StudentWithCoursesDTO registerStudentWithCoursesDTO) {
    int studentId = studentService.registerStudent(registerStudentWithCoursesDTO.getStudent());
    studentsCoursesService.registerStudentsCourses(
        registerStudentWithCoursesDTO.getStudentsCourses(),
        registerStudentWithCoursesDTO.getStudent().getEmail()
    );
    return getOneStudent(studentId);
  }
}
