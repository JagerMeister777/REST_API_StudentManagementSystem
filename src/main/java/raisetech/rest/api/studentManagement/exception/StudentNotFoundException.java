package raisetech.rest.api.studentManagement.exception;

public class StudentNotFoundException extends RuntimeException{
  public StudentNotFoundException(String message) {
    super(message);
  }
}
