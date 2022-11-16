package sample05tea;

public class ProductBean {
  private int pno; //단어_단어 - > setPno();
  private int price;
  private String pname;
 
    public ProductBean() {
    	System.out.println("ProductBean 생성자 ....");
    }

	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
  
	
	
	
	  
}