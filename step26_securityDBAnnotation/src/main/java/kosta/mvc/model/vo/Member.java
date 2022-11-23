package kosta.mvc.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Member {
  private String id;
  private String password;
  private String name;
  private String email;
  
  private String userType; //회원가입할때 <input type="radio"
  
}
