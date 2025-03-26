package raisetech.rest.api.studentManagement.service;

import static raisetech.rest.api.studentManagement.constants.CustomExceptionMessageConst.COURSE_NOT_FOUND_EXCEPTION;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.Course;
import raisetech.rest.api.studentManagement.exception.CourseNotFoundException;
import raisetech.rest.api.studentManagement.repository.CourseRepository;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  @Autowired
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  /**
   * コース名でコース情報を検索します。
   * @param courseName コース名
   * @return コース情報
   */
  public Course findByCourseName(String courseName) {
    return courseRepository.findByCourseName(courseName)
        .orElseThrow(() -> new CourseNotFoundException(COURSE_NOT_FOUND_EXCEPTION));
  }

  /**
   * 登録しているコース情報を全件取得します。
   * @return すべてのコース情報リスト
   */
  public List<Course> getAllCourses() {
    return courseRepository.getAllCourse();
  }
}
