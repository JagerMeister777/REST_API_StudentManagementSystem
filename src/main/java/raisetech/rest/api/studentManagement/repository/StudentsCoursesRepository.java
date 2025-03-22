package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import raisetech.rest.api.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentsCoursesRepository {

  /**
   * 特定の受講生の受講しているコース情報を取得します。
   * @param id 受講生ID
   * @return 受講生コース情報のリスト
   */
  List<StudentsCourses> findByStudentId(int id);

  /**
   * 受講生IDとコースIDの組み合わせが存在するかどうかチェックします。
   * @param studentId 受講生ID
   * @param courseId コースID
   * @return 受講生コース情報を返し、存在しなければNullを返します。
   */
  Optional<StudentsCourses> isExistingCombination(int studentId, int courseId);

  /**
   * 受講生コース情報の登録をします。
   * @param studentsCourses 登録する受講生コース情報
   */
  void registerStudentsCourses(StudentsCourses studentsCourses);

  /**
   * 受講生コース情報の更新をします。
   * @param studentsCourses 受講生コース情報
   */
  void updateStudentsCourses(StudentsCourses studentsCourses);
}
