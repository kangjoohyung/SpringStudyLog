package kosta.mvc.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferDTO {
   private String output; //출금계좌번호
   private String input;//입금계좌번호
   private int transferAmount;// 계좌이체 할 금액
   
}
