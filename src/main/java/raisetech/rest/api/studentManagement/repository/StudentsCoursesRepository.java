package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentsCoursesRepository {

  @Select("SELECT * FROM students_courses WHERE student_id = #{id}")
  List<StudentsCourses> findByStudentId(int id);

  /**
   * 受講生IDとコースIDを引数でもらい、一致するものを返す
   * @param studentId 受講生ID
   * @param courseId コースID
   * @return 受講生コース情報、存在しなければEmptyを返す
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} AND course_id = #{courseId}")
  StudentsCourses isExistingCombination(int studentId, int courseId);

  /**
   * 受講生コース情報の登録
   * @param studentsCourses 受講生コース情報
   */
  @Select("INSERT INTO students_courses (student_id, course_id, course_start_date, course_end_date) VALUES (#{studentId}, #{courseId}, #{courseStartDate}, #{courseEndDate});")
  void registerStudentsCourses(StudentsCourses studentsCourses);

}
