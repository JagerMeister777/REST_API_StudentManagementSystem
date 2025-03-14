package raisetech.rest.api.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.repository.StudentsCoursesRepository;

@Service
public class StudentsCoursesService {

  private final StudentsCoursesRepository studentsCoursesRepository;

  @Autowired
  public StudentsCoursesService(StudentsCoursesRepository studentsCoursesRepository) {
    this.studentsCoursesRepository = studentsCoursesRepository;
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
}

