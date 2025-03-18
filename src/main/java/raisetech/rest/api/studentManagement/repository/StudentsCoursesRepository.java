package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentsCoursesRepository {

  /**
   * 特定の受講生の受講しているコース情報を取得します。
   * @param id 受講生ID
   * @return 受講生コース情報のリスト
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{id}")
  List<StudentsCourses> findByStudentId(int id);

  /**
   * 受講生IDとコースIDの組み合わせが存在するかどうかチェックします。
   * @param studentId 受講生ID
   * @param courseId コースID
   * @return 受講生コース情報を返し、存在しなければNullを返します。
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId} AND course_id = #{courseId}")
  StudentsCourses isExistingCombination(int studentId, int courseId);

  /**
   * 受講生コース情報の登録をします。
   * @param studentsCourses 登録する受講生コース情報
   */
  @Select("INSERT INTO students_courses (student_id, course_id, course_start_date, course_end_date) VALUES (#{studentId}, #{courseId}, #{courseStartDate}, #{courseEndDate});")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  /**
   * 受講生コース情報の更新
   * @param studentsCourses 受講生コース情報
   */
  @Update("UPDATE students_courses SET id = #{id}, student_id = #{studentId}, course_id = #{courseId}, course_start_date = #{courseStartDate}, course_end_date = #{courseEndDate} WHERE id = #{id}")
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
