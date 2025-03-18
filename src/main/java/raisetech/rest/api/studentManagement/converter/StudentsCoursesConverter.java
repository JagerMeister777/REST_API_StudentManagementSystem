package raisetech.rest.api.studentManagement.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.rest.api.studentManagement.data.Course;
import raisetech.rest.api.studentManagement.data.Student;
import raisetech.rest.api.studentManagement.data.StudentsCourses;
import raisetech.rest.api.studentManagement.dto.respons.StudentWithCoursesDTO;
import raisetech.rest.api.studentManagement.dto.respons.StudentsCoursesDetail;
import raisetech.rest.api.studentManagement.repository.CourseRepository;
import raisetech.rest.api.studentManagement.repository.StudentRepository;
import raisetech.rest.api.studentManagement.repository.StudentsCoursesRepository;
import raisetech.rest.api.studentManagement.service.CourseService;
import raisetech.rest.api.studentManagement.service.StudentService;
import raisetech.rest.api.studentManagement.service.StudentsCoursesService;

@Component
public class StudentsCoursesConverter {

  /**
   * 受講生情報と受講生コース情報を組み合わせます。
   * @param student 受講生情報
   * @param studentsCoursesList 受講生コース情報
   * @param courseList 登録されているすべてのコース情報のリスト
   * @return StudentWithCoursesDTO型の受講生情報
   */
  public StudentWithCoursesDTO convertStudentWithCoursesDTO(Student student,List<StudentsCourses> studentsCoursesList,List<Course> courseList) {
    return new StudentWithCoursesDTO(
        student,
        convertStudentsCoursesDetail(
            studentsCoursesList,
            student.getFullName(),
            courseList
        )
    );
  }

  /**
   * 受講生コース情報のstudentIdとcourseIdを見やすい形式で変換します。
   * @param studentsCoursesList 受講生コース情報リスト
   * @param studentFullName 受講生のフルネーム
   * @param courseList 全件コースリスト
   * @return StudentsCoursesDetail型で変換されたリスト
   */
  public List<StudentsCoursesDetail> convertStudentsCoursesDetail(List<StudentsCourses> studentsCoursesList,String studentFullName,List<Course> courseList) {
    List<StudentsCoursesDetail> studentsCoursesDetails = new ArrayList<>();
    studentsCoursesList.forEach(studentsCourses -> {
      courseList.forEach(course -> {
        if (course.getId() == studentsCourses.getCourseId()) {
          StudentsCoursesDetail studentsCoursesDetail = new StudentsCoursesDetail(
              studentFullName,
              course.getName(),
              studentsCourses.getCourseStartDate(),
              studentsCourses.getCourseEndDate()
          );
          studentsCoursesDetails.add(studentsCoursesDetail);
        }
      });
    });
    return studentsCoursesDetails;
  }
}
