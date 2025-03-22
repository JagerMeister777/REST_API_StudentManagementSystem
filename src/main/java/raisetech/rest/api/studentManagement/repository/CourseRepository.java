package raisetech.rest.api.studentManagement.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import raisetech.rest.api.studentManagement.data.Course;

@Mapper
public interface CourseRepository {

  /**
   * 登録しているすべてのコース情報を取得します。
   * @return コース情報の全件リスト
   */
  List<Course> getAllCourse();

  /**
   * 特定のコース情報をコース名で検索します。
   * @param name コース名
   * @return コース情報
   */
  Optional<Course> findByCourseName(String name);
}
