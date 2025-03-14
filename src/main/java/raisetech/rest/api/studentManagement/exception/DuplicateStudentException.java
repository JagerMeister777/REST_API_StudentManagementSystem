package raisetech.rest.api.studentManagement.exception;

public class DuplicateStudentException extends RuntimeException{
  public DuplicateStudentException(String message) {
    super(message);
  }
}
