package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.converter.StudentsCoursesConverter;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.exception.DuplicateStudentException;
import raisetech.rest.api.studentManagement.exception.StudentNotFoundException;
import raisetech.rest.api.studentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private final StudentRepository studentRepository;
  private final StudentsCoursesService studentsCoursesService;
  private final CourseService courseService;
  private final StudentsCoursesConverter converter;

  @Autowired
  public StudentService(StudentRepository studentRepository,
      StudentsCoursesService studentsCoursesService, CourseService courseService,
      StudentsCoursesConverter converter) {
    this.studentRepository = studentRepository;
    this.studentsCoursesService = studentsCoursesService;
    this.courseService = courseService;
    this.converter = converter;
  }

  /**
   * 受講生の全件取得
   *
   * @return 受講生情報のリスト
   */
  public List<StudentWithCoursesDTO> getAllStudents() {
    List<Student> studentList = studentRepository.getAllStudents();
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
   * 特定の受講生情報の取得
   *
   * @param id 受講生ID
   * @return 受講生情報
   */
  public StudentWithCoursesDTO getOneStudent(int id) {
    Student student = studentRepository.findByStudentId(id);
    if (student == null) {
      throw new StudentNotFoundException("受講生情報が存在しませんでした。");
    }
    return new StudentWithCoursesDTO(
        student,
        converter.convertStudentsCoursesDetail(
            studentsCoursesService.getOneStudentsCoursesList(id),
        student.getFullName(),
        courseService.getAllCourses()));
  }

  public Student updateStudent(int id, Student updateStudent) {
    Student existEmailStudent = studentRepository.findByEmail(updateStudent.getEmail());
    if (existEmailStudent != null && updateStudent.getId() != existEmailStudent.getId()) {
      throw new DuplicateStudentException("既にメールアドレスが使用されています。");
    }
    studentRepository.updateStudent(id, updateStudent);
    return updateStudent;
  }
}
