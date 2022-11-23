package kosta.web.mvc.model.board;

import java.io.IOException;
import java.util.List;

import kosta.web.mvc.model.dto.ElectronicsDTO;

public interface BoardService {
	/**
	  * ���ڵ� ��ü �˻�
	  * */
	  List<ElectronicsDTO> selectAll() ;
	  
	  /**
		  * �𵨹�ȣ�� �ش��ϴ� ���ڵ� �˻�
		  * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
		  * */
	  ElectronicsDTO selectByModelNum(String modelNum , boolean state) ;
	  
	 
	  
	/**
	 * ���ڵ� ����
	 * */
	  int insert(ElectronicsDTO electronics);
	  
	  /**
	   * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	   * */
	   int delete(String modelNum, String password);
	  
	   /**
	    * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	    * */
	   int update(ElectronicsDTO electronics);
}
