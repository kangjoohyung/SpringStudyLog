package sample06;

import java.util.List;

public class ListBean {
	private List<Integer> intList;
	private List<String> strList;
	private List<Customer> customerList;
	
	public ListBean() {
		System.out.println("ListBean()생성자");
	}

	public List<Integer> getIntList() {
		return intList;
	}

	public List<String> getStrList() {
		return strList;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setIntList(List<Integer> intList) {
		this.intList = intList;
		System.out.println("setIntList"+intList);
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
		System.out.println("setStrList"+strList);
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
		System.out.println("setCustomerList"+customerList);
	}
	
	
}
