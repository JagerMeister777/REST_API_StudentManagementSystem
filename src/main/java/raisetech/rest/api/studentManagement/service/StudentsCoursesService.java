package raisetech.rest.api.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;
import raisetech.rest.api.studentManagement.exception.InvalidStudentCoursesCombinationException;
import raisetech.rest.api.studentManagement.repository.StudentsCoursesRepository;

@Service
public class StudentsCoursesService {

  private final StudentsCoursesRepository studentsCoursesRepository;
  private final CourseService courseService;
  private final StudentService studentService;

  @Autowired
  public StudentsCoursesService(StudentsCoursesRepository studentsCoursesRepository,
      CourseService courseService, StudentService studentService) {
    this.studentsCoursesRepository = studentsCoursesRepository;
    this.courseService = courseService;
    this.studentService = studentService;
  }

  /**
   * 特定の受講生の受講しているコース情報を取得
   *
   * @param id 受講生ID
   * @return 受講生コース情報
   */
  public List<StudentsCourses> getOneStudentsCoursesList(int id) {
    return studentsCoursesRepository.findByStudentId(id);
  }

  /**
   * 受講生コース情報の登録を行います。
   * @param registerStudentsCoursesDetailList 登録する受講生コース情報
   * @param registerStudentId 登録した受講生のID
   */
  public void registerStudentsCourses(List<StudentsCoursesDetail> registerStudentsCoursesDetailList,
      int registerStudentId) {
    registerStudentsCoursesDetailList.forEach(studentsCoursesDetail -> {
      int courseId = courseService.findByCourseName(studentsCoursesDetail.getCourseName()).getId();
      StudentsCourses registerStudentsCourses = new StudentsCourses(
          registerStudentId,
          courseId,
          studentsCoursesDetail.getCourseStartDate(),
          studentsCoursesDetail.getCourseEndDate()
      );
      studentsCoursesRepository.registerStudentsCourses(registerStudentsCourses);
    });
  }

  /**
   * 受講生コース情報の更新を行います。
   *
   * @param studentsCoursesList 更新する受講生コース情報
   */
  public void updateStudentsCourses(List<StudentsCourses> studentsCoursesList) {
    studentsCoursesList.forEach(studentsCoursesRepository::updateStudentsCourses);
  }

  // TODO 受講生コース情報の単品登録の時に使えるメソッドまだ使わない
  public void isExistingCombination(int studentId, int courseId) {
    if (studentsCoursesRepository.isExistingCombination(studentId, courseId).isPresent()) {
      throw new InvalidStudentCoursesCombinationException("既に登録するコースを受講しています。");
    }
  }
}

