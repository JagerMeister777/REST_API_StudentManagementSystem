package raisetech.rest.api.studentManagement.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetech.rest.api.studentManagement.data.Course;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDto;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;

@Component
public class StudentsCoursesConverter {

  /**
   * 受講生情報と受講生コース情報を組み合わせます。
   *
   * @param student             受講生情報
   * @param studentsCoursesList 受講生コース情報
   * @param courseList          登録されているすべてのコース情報のリスト
   * @return StudentWithCoursesDTO型の受講生情報
   */
  public StudentWithCoursesDto convertStudentWithCoursesDTO(Student student, List<StudentsCourses> studentsCoursesList, List<Course> courseList) {
    List<StudentsCoursesDetail> studentsCoursesDetailList = new ArrayList<>();
    studentsCoursesList.forEach(studentsCourses -> {
      courseList.forEach(course -> {
        if (course.getId() == studentsCourses.getCourseId()) {
          StudentsCoursesDetail studentsCoursesDetail = new StudentsCoursesDetail(
              course.getName(),
              studentsCourses.getCourseStartDate(),
              studentsCourses.getCourseEndDate()
          );
          studentsCoursesDetailList.add(studentsCoursesDetail);
        }
      });
    });
    return new StudentWithCoursesDto(student, studentsCoursesDetailList);
  }
}
