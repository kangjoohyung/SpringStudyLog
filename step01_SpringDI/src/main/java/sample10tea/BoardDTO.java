package sample10tea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
//@ToString
//@ToString(exclude = {"content"})
@Component //id="boardDTO"
@Scope("prototype") //지연초기화
public class BoardDTO {
	@Value("10")
  private int no;

   @Value("SpringDI")	
  private String subject;
   
  @Value("신기하고 재미있다.")
  private String content;
}



