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
		
		//���޵� ��й�ȣ(��)�� ��ȣȭ�ؼ� ����
		String encPass=passwordEncoder.encode(member.getPassword());
		member.setPassword(encPass); //��ȣȭ�� ������� Member�� password ����
		
		// ȸ�� ���̺� insert
		int result=memberDAO.insertMember(member);
		if(result==0) throw new RuntimeException("ȸ�����Ե��� �ʾҽ��ϴ�");
		
		// ȸ���� userType�� ���� ���� ���̺� 1�� �̻� insert �ؾ���
		if(member.getUserType()==null) {
			throw new RuntimeException("userType������ ���Ե��� �ʾҽ��ϴ�");
		}
		result=authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_MEMBER));
		if(result==0) throw new RuntimeException("���� ���� ������ ���Ե��� �ʾҽ��ϴ�");
		
		//userType=1�� ���� �������̹Ƿ� ������ �� �� �� �����Ѵ�.
		if(member.getUserType().equals("1")) {
			authoritiesDAO.insertAuthority(new Authority(member.getId(), RoleConstants.ROLE_ADMIN));
			if(result==0) throw new RuntimeException("������ ���� ���� ������ ���Ե��� �ʾҽ��ϴ�");
		}
		
		return result;
	}
}
