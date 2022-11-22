package kosta.mvc.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kosta.mvc.model.dao.AuthoritiesDAO;
import kosta.mvc.model.dao.MemberDAO;
import kosta.mvc.model.vo.Authority;
import kosta.mvc.model.vo.Member;
import lombok.RequiredArgsConstructor;

/**
 * 인증을 처리할 클래스
 *
 */
@Service //id=memberAuthenticationProvider
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider{

	private final MemberDAO memberDAO;
	private final AuthoritiesDAO authoritiesDAO;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * 로그인 폼에서 전달된 username=id와 password가 UsernamePasswordAuthenticationToken 객체로 만들어져서
	 * 인수 Authentication 객체에 전달된다.
	 * 전달된 인수에서 username과 password를 꺼내서 DB에 있는 정보와 같은지를 확인하고
	 * 다르면 인증실패 예외를 발생시키고,
	 * 만약 정보가 같으면 인증된 사용자의 정보를 Authentication의 구현체인 UsernamePasswordAuthenticationToken를
	 * 생성해서 저장한 후 리턴해준다.
	 * 즉 인수와 리턴값의 객체는 서로 다른 객체이다
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("MemberAuthenticationProvider의 authenticate");
		
		String id=authentication.getName();
		Member member=memberDAO.selectMemberById(id);
		if(member==null) {
			throw new UsernameNotFoundException(id+"가 존재하지 않습니다"); //위의 throws 익셉션의 하위종류중 하나로 보내버리기
		}
		
		//평문과 암호화된 비번을 체크
		String pass=authentication.getCredentials().toString();
		if(!passwordEncoder.matches(pass, member.getPassword())) { 
		//1번째 인수가 평문, 2번재 인수가 암호화(DB에 저장된 비번)
			
			throw new UsernameNotFoundException("비밀번호 오류입니다");//위의 throws 익셉션의 하위종류중 하나로 보내버리기
		}
		
		//인증된 사용자의 권한 조회
		List<Authority> authorityList=authoritiesDAO.selectAuthorityByUserName(id);
		
		//나온 authorityList를 security의 권한 타입(GrantedAuthority)에 맞게 형변환이 필요하다.
		List<SimpleGrantedAuthority> simpleAuthList=new ArrayList<SimpleGrantedAuthority>();
		for(Authority authority : authorityList) {
			simpleAuthList.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		
		//인증성공했으니 인증된 사용자의 정보를 UsernamepasswordAuthenticationToken에 저장
		UsernamePasswordAuthenticationToken auth=
				new UsernamePasswordAuthenticationToken(member, null, simpleAuthList); 
		//password는 노출안해서 null로 사용
		//권한까지 부여할때 3번째 인수까지 넣기(ROLE있을때)
		//3번째는 GrantedAuthority의 하위 타입을 넣어야 함
		
		return auth;
	}

	/**
	 * 인수로 전달된 인증 정보가 인증을 할 수 있는 유효한 객체인지를 판단해주는 메소드
	 * true리턴하면 authenticate메소드를 호출해주고, false를 리턴하면 authenticate 호출하지 않는다.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("MemberAuthenticationProvider의 supports");
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
