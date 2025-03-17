package raisetech.rest.api.studentManagement.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Course {

  private int id;
  private String name;

  public Course(String name) {
    this.name = name;
  }

  public Course(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
