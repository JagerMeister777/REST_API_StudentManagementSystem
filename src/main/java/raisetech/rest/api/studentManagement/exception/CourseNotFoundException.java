package raisetech.rest.api.studentManagement.exception;

public class CourseNotFoundException extends RuntimeException{
  public CourseNotFoundException(String message) {
    super(message);
  }
}
