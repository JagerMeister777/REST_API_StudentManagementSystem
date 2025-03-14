package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentsCoursesRepository {

  @Select("SELECT * FROM students_courses WHERE student_id = #{id}")
  List<StudentsCourses> findByStudentId(int id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{id} AND course_id = #{courseId}")
  boolean isExistingCombination(int studentId, int courseId);

  /**
   * 受講生コース情報の登録
   * @param studentsCourses 受講生コース情報
   */
  @Insert("INSERT INTO students_courses (student_id, course_id, course_start_date, course_end_date) VALUES (#{studentId}, #{courseId}, #{courseStartDate}, #{courseEndDate})")
  void registerStudentsCourses(StudentsCourses studentsCourses);

}
