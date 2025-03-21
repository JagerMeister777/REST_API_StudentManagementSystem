package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.rest.api.studentManagement.converter.StudentsCoursesConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDto;
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
  public List<StudentWithCoursesDto> getAllStudents() {
    List<Student> studentsList = studentService.getAllStudents();
    List<StudentWithCoursesDto> studentWithCoursesDtoList = new ArrayList<>();
    studentsList.forEach(student -> {
      List<StudentsCourses> studentsCoursesList = studentsCoursesService.getOneStudentsCoursesList(
          student.getId());
      studentWithCoursesDtoList.add(
          converter.convertStudentWithCoursesDTO(student, studentsCoursesList,
              courseService.getAllCourses()));
    });
    return studentWithCoursesDtoList;
  }

  /**
   * 特定の受講生情報の取得をします。
   *
   * @param id 受講生ID
   * @return 受講生情報
   */
  public StudentWithCoursesDto getOneStudent(int id) {
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
   * @param registerStudentWithCoursesDto 受講生情報と受講生コース情報がバインドされたDTO
   * @return 登録した受講生情報と受講生コース情報
   */
  @Transactional
  public StudentWithCoursesDto registerHandling(StudentWithCoursesDto registerStudentWithCoursesDto) {
    int registerStudentId = studentService.registerStudent(registerStudentWithCoursesDto.getStudent());
    studentsCoursesService.registerStudentsCourses(
        registerStudentWithCoursesDto.getStudentsCourses(),
        registerStudentId
    );
    return getOneStudent(registerStudentId);
  }

  /**
   * 受講生情報と受講生コース情報の更新処理をハンドリングします。
   * @param id 受講生ID
   * @param updateStudentWithCoursesDto　受講生情報と受講生コース情報がバインドされたDTO
   * @return 更新した受講生情報と受講生コース情報
   */
  @Transactional
  public StudentWithCoursesDto updateHandling(int id, StudentWithCoursesDto updateStudentWithCoursesDto) {
    studentService.updateStudent(id, updateStudentWithCoursesDto.getStudent());
    List<StudentsCourses> updateStudentsCoursesList = new ArrayList<>();
    updateStudentWithCoursesDto.getStudentsCourses().forEach(studentsCoursesDetail -> {
      int courseId = courseService.findByCourseName(studentsCoursesDetail.getCourseName()).getId();
      StudentsCourses studentsCourses = new StudentsCourses(
          id,
          courseId,
          studentsCoursesDetail.getCourseStartDate(),
          studentsCoursesDetail.getCourseEndDate()
      );
      updateStudentsCoursesList.add(studentsCourses);
    });
    studentsCoursesService.updateStudentsCourses(updateStudentsCoursesList);
    return updateStudentWithCoursesDto;
  }
}
