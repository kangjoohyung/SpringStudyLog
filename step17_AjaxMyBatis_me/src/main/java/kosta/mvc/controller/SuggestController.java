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
@RestController //@Controller+@ResponseBody->ajax전용
@RequiredArgsConstructor //주입용-final
public class SuggestController {
	
	private final SuggestService suggestService; //주입필요 : final이나 autowired
	
	/**
	 * 객체가 생성된 후에 해야할 일이 있다면
	 * 메소드를 만들고 @PostConstruct 선언하면 객체 생성후 자동 호출된다.
	 * (생성이 주입보다 우선이기에, 주입후 생성이 필요할때 사용)
	 */
	@PostConstruct
	public void test() {
		System.out.println("SuggestController의 test");
		System.out.println("SuggestService="+suggestService);
	}

	@RequestMapping("/suggest")
	public List<String> suggest(String keyWord) {
		List<String> list=suggestService.selectByWord(keyWord);
		//jackson라이브러리가 리스트형태로 자동변환해주고 ajax형태로 전송할수있게된다.
		return list;
	}
}
