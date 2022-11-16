package kosta.exam;

import org.springframework.stereotype.Service;

@Service("target")
public class MemberServiceImpl implements MemberService {

	@Override
	public int insert() {
		System.out.println("MemberServiceImpl의 insert()핵심기능");
		return 100;
	}

	@Override
	public String select(int i) {
		System.out.println("MemberServiceImpl의 select(int i)핵심기능");
		return "졸지말기";
	}

	@Override
	public void update(String id, String name) {
		System.out.println("MemberServiceImpl의 update(String id, String name)");
		System.out.println(id+", "+name);
	}

}
