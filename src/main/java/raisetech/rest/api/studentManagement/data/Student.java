package raisetech.rest.api.studentManagement.data;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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

  public Student(String fullName, String furigana, String nickName, String email, String livingArea,
      int age, String gender, String remark, boolean isDeleted) {
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickName = nickName;
    this.email = email;
    this.livingArea = livingArea;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }

  public Student(int id, String fullName, String furigana, String nickName, String email,
      String livingArea, int age, String gender, String remark, boolean isDeleted) {
    this.id = id;
    this.fullName = fullName;
    this.furigana = furigana;
    this.nickName = nickName;
    this.email = email;
    this.livingArea = livingArea;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }
}
