package kosta.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service //����
public class TestShareService {

	@Autowired //����
	private TestShareService shareService;
	
	@RequestMapping("/share.do")
	public String share() {
		System.out.println("TestShareCotroller�� share.do");
		System.out.println("shareService="+shareService);
		
		return "result"; //WEB-INF/views/result.jsp�̵�
	}
}
