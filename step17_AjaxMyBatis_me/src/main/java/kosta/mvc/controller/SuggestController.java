package kosta.mvc.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.mvc.dto.Member;
import kosta.mvc.service.SuggestService;
import lombok.RequiredArgsConstructor;

/**
 * ajax-SuggestClient
 */
@RestController //@Controller+@ResponseBody->ajax����
@RequiredArgsConstructor //���Կ�-final
public class SuggestController {
	
	private final SuggestService suggestService; //�����ʿ� : final�̳� autowired
	
	/**
	 * ��ü�� ������ �Ŀ� �ؾ��� ���� �ִٸ�
	 * �޼ҵ带 ����� @PostConstruct �����ϸ� ��ü ������ �ڵ� ȣ��ȴ�.
	 * (������ ���Ժ��� �켱�̱⿡, ������ ������ �ʿ��Ҷ� ���)
	 */
	@PostConstruct
	public void test() {
		System.out.println("SuggestController�� test");
		System.out.println("SuggestService="+suggestService);
	}

	@RequestMapping("/suggest")
	public List<String> suggest(String keyWord) {
		List<String> list=suggestService.selectByWord(keyWord);
		//jackson���̺귯���� ����Ʈ���·� �ڵ���ȯ���ְ� ajax���·� �����Ҽ��ְԵȴ�.
		return list;
	}
}
