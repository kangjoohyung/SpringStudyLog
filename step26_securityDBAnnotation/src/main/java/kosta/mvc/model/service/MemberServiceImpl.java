package kosta.mvc.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kosta.mvc.model.dao.AuthoritiesDAO;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.vo.Authority;
import kosta.mvc.model.vo.Member;
import kosta.mvc.util.RoleConstants;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

	private final MemberDAO memberDAO;
	private final AuthoritiesDAO authoritiesDAO;
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public int joinMember(Member member) {
		
		//전달된 비밀번호(평문)을 암호화해서 저장
		String encPass=passwordEncoder.encode(member.getPassword());
		member.setPassword(encPass); //암호화된 비번으로 Member에 password 수정
		
		// 회원 테이블 insert
		int result=memberDAO.insertMember(member);
		if(result==0) throw new RuntimeException("회원가입되지 않았습니다");
		
		// 회원의 userType에 따라 권한 테이블에 1개 이상 insert 해야함
		if(member.getUserType()==null) {
			throw new RuntimeException("userType오류로 가입되지 않았습니다");
		}
		result=authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_MEMBER));
		if(result==0) throw new RuntimeException("권한 설정 오류로 가입되지 않았습니다");
		
		//userType=1인 경우는 관리자이므로 권한을 한 개 더 설정한다.
		if(member.getUserType().equals("1")) {
			authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_ADMIN));
			if(result==0) throw new RuntimeException("관리자 권한 설정 오류로 가입되지 않았습니다");
		}
		
		return result;
	}
}
