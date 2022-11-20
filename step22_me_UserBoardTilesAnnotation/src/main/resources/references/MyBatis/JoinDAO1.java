package kosta.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kosta.dto.Commentb;
import kosta.dto.ReplyDTO;
import kosta.dto.UserDTO;
import kosta.util.DbUtil;

public class JoinDAO {

	/**
	 * 1:1 - �Խù� + �ۼ��� ���� �˻�
	 * : ���ι����� DTO������ �����ͼ� ����ϴ� ����
	 */
	public static void association() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<Commentb> commentbList= session.selectList("joinMapper.joinAssociation");
			for(Commentb board : commentbList) {
				System.out.println("��ȣ:"+board.getCommentNo()+"|"+
						"����:"+board.getCommentContent()+"|"+
						"�ۼ���ID:"+board.getUsers().getUserId()+"|"+
						"�ۼ����̸�:"+board.getUsers().getUserName()+"|"+
						"�����:"+board.getRegDate());
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//association() end
	
	/**
	 * 1:���� ���� ����2) Commentb������ �Բ� Reply������ �˻��ϰ� �ʹ�.
	 */
	public static void collection() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<Commentb> commentbList= session.selectList("joinMapper.joinCollection");
			
			System.out.println("�� �Խù� �� : "+commentbList.size());
			
			for(Commentb board : commentbList) {
				System.out.print("��ȣ:"+board.getCommentNo()+"|"+
						"����:"+board.getCommentContent()+"|"+
						"�����:"+board.getRegDate());
				System.out.println(board.getCommentNo()+"�� �������("+board.getReplies().size()+"��)");
				for(ReplyDTO reply : board.getReplies()) {
					System.out.print(">"+reply.getReplyNo()+"|");
					System.out.print(reply.getReplyContent()+"|");
					System.out.print(reply.getUserId()+"|");
					System.out.print(reply.getRegDate()+"\n");
				}
				System.out.println();
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//collection() end
	
	/**
	 * �Էµ� userId�� �˻��ؼ� user�������� ���ι� ����ϱ�(DTO user����)
	 * @param userId
	 */
	public static void userCollection(String userId) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			UserDTO user=session.selectOne("joinMapper.userCollection", userId);
			
			System.out.println(user.getUserId()+"|");
			System.out.print(user.getUserName()+"�� ����ϴ� �Խù� ����");
			for(Commentb board : user.getCommentbList()) {
				System.out.print("\n>"+board.getCommentContent()+"|"
						+board.getCommentContent()+"|"
						+board.getRegDate()+"\n");
			}
			
		}finally {
			DbUtil.sessionClose(session);
		}
	}//userCollection(String userId)
}
