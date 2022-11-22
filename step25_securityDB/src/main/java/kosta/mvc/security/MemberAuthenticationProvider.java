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
 * ������ ó���� Ŭ����
 *
 */
@Service //id=memberAuthenticationProvider
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider{

	private final MemberDAO memberDAO;
	private final AuthoritiesDAO authoritiesDAO;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * �α��� ������ ���޵� username=id�� password�� UsernamePasswordAuthenticationToken ��ü�� ���������
	 * �μ� Authentication ��ü�� ���޵ȴ�.
	 * ���޵� �μ����� username�� password�� ������ DB�� �ִ� ������ �������� Ȯ���ϰ�
	 * �ٸ��� �������� ���ܸ� �߻���Ű��,
	 * ���� ������ ������ ������ ������� ������ Authentication�� ����ü�� UsernamePasswordAuthenticationToken��
	 * �����ؼ� ������ �� �������ش�.
	 * �� �μ��� ���ϰ��� ��ü�� ���� �ٸ� ��ü�̴�
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("MemberAuthenticationProvider�� authenticate");
		
		String id=authentication.getName();
		Member member=memberDAO.selectMemberById(id);
		if(member==null) {
			throw new UsernameNotFoundException(id+"�� �������� �ʽ��ϴ�"); //���� throws �ͼ����� ���������� �ϳ��� ����������
		}
		
		//�򹮰� ��ȣȭ�� ����� üũ
		String pass=authentication.getCredentials().toString();
		if(!passwordEncoder.matches(pass, member.getPassword())) { 
		//1��° �μ��� ��, 2���� �μ��� ��ȣȭ(DB�� ����� ���)
			
			throw new UsernameNotFoundException("��й�ȣ �����Դϴ�");//���� throws �ͼ����� ���������� �ϳ��� ����������
		}
		
		//������ ������� ���� ��ȸ
		List<Authority> authorityList=authoritiesDAO.selectAuthorityByUserName(id);
		
		//���� authorityList�� security�� ���� Ÿ��(GrantedAuthority)�� �°� ����ȯ�� �ʿ��ϴ�.
		List<SimpleGrantedAuthority> simpleAuthList=new ArrayList<SimpleGrantedAuthority>();
		for(Authority authority : authorityList) {
			simpleAuthList.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		
		//�������������� ������ ������� ������ UsernamepasswordAuthenticationToken�� ����
		UsernamePasswordAuthenticationToken auth=
				new UsernamePasswordAuthenticationToken(member, null, simpleAuthList); 
		//password�� ������ؼ� null�� ���
		//���ѱ��� �ο��Ҷ� 3��° �μ����� �ֱ�(ROLE������)
		//3��°�� GrantedAuthority�� ���� Ÿ���� �־�� ��
		
		return auth;
	}

	/**
	 * �μ��� ���޵� ���� ������ ������ �� �� �ִ� ��ȿ�� ��ü������ �Ǵ����ִ� �޼ҵ�
	 * true�����ϸ� authenticate�޼ҵ带 ȣ�����ְ�, false�� �����ϸ� authenticate ȣ������ �ʴ´�.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("MemberAuthenticationProvider�� supports");
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
