package raisetech.rest.api.studentManagement.constants;


public class CustomExceptionMessageConst {

  public static final String NOT_FOUND_STUDENT_MESSAGE = "受講生情報が存在しません。";
  public static final String IS_DELETED_STUDENT_MESSAGE = "受講生情報が削除されています。";
  public static final String DUPLICATE_STUDENT_EXCEPTION = "既にメールアドレスが使用されています。";
  public static final String INVALID_STUDENT_COURSES_COMBINATION_EXCEPTION = "既に登録するコースを受講しています";
  public static final String COURSE_NOT_FOUND_EXCEPTION = "コース情報が存在しませんでした。";
  public static final String UN_MATCH_ID_EXCEPTION = "パラメータとリクエストのIDが一致しませんでした。";

  private CustomExceptionMessageConst() {

  }
}
