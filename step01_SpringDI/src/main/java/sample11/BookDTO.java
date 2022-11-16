package sample11;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component //생성
@Scope("prototype") //지연초기화
public class BookDTO {
  @Value("Spring공부")
  private String subject;
  
  @Value("장희정")
  private String writer;
  
  @Value("25000")
  private int price;
  
  @Value("2022-11-04")
  private String date;
}
