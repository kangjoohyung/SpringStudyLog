package kosta.web.mvc.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
   private String userId; //Db user_id
   private String pwd;
   private String name;
}
