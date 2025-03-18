package raisetech.rest.api.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
   * @param email 登録する受講生を特定するためのメールアドレス
   */
  public void registerStudentsCourses(List<StudentsCoursesDetail> registerStudentsCoursesDetailList,
      String email) {
    registerStudentsCoursesDetailList.forEach(studentsCoursesDetail -> {
      int courseId = courseService.findByCourseName(studentsCoursesDetail.getCourseName()).getId();
      int studentId = studentService.findByEmail(email).getId();
      StudentsCourses existStudentCourses = studentsCoursesRepository.isExistingCombination(studentId, courseId);
      if (existStudentCourses != null) {
        throw new InvalidStudentCoursesCombinationException("既に登録するコースを受講しています。");
      }
      StudentsCourses registerStudentsCourses = new StudentsCourses(
          studentId,
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
}

