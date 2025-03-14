package raisetech.rest.api.studentManagement.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private int id;
  private String fullName;
  private String furigana;
  private String nickName;
  private String email;
  private String livingArea;
  private int age;
  private String gender;
  private String remark;
  private boolean isDeleted;
}
