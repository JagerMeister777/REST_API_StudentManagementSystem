package raisetech.rest.api.studentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.rest.api.studentManagement.data.Course;
import raisetech.rest.api.studentManagement.repository.CourseRepository;

@Service
public class CourseService {

  private final CourseRepository courseRepository;

  @Autowired
  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course getOneCourse(int id) {
    return courseRepository.findByCourseId(id);
  }

  public List<Course> getAllCourses() {
    return courseRepository.getAllCourse();
  }
}
