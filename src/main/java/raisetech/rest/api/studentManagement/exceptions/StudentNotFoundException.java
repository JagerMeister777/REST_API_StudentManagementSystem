package raisetech.rest.api.studentManagement.exceptions;

public class StudentNotFoundException extends RuntimeException{
  public StudentNotFoundException(String message) {
    super(message);
  }
}
