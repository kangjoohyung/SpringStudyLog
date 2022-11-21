package kosta.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kosta.dto.Commentb;
import kosta.dto.ReplyDTO;
import kosta.dto.UserDTO;
import kosta.util.DbUtil;

public class JoinDAO {

	/**
	 * 1:1 - 게시물 + 작성자 정보 검색
	 * : 조인문에서 DTO여러개 가져와서 사용하는 예제
	 */
	public static void association() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<Commentb> commentbList= session.selectList("joinMapper.joinAssociation");
			for(Commentb board : commentbList) {
				System.out.println("번호:"+board.getCommentNo()+"|"+
						"내용:"+board.getCommentContent()+"|"+
						"작성자ID:"+board.getUsers().getUserId()+"|"+
						"작성자이름:"+board.getUsers().getUserName()+"|"+
						"등록일:"+board.getRegDate());
			}
		}finally {
			DbUtil.sessionClose(session);
		}
	}//association() end
	
	/**
	 * 1:다의 관계 문제2) Commentb정보와 함께 Reply정보를 검색하고 싶다.
	 */
	public static void collection() {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			List<Commentb> commentbList= session.selectList("joinMapper.joinCollection");
			
			System.out.println("총 게시물 수 : "+commentbList.size());
			
			for(Commentb board : commentbList) {
				System.out.print("번호:"+board.getCommentNo()+"|"+
						"내용:"+board.getCommentContent()+"|"+
						"등록일:"+board.getRegDate());
				System.out.println(board.getCommentNo()+"의 댓글정보("+board.getReplies().size()+"개)");
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
	 * 입력된 userId로 검색해서 user기준으로 조인문 출력하기(DTO user기준)
	 * @param userId
	 */
	public static void userCollection(String userId) {
		SqlSession session=null;
		try {
			session=DbUtil.getSession();
			UserDTO user=session.selectOne("joinMapper.userCollection", userId);
			
			System.out.println(user.getUserId()+"|");
			System.out.print(user.getUserName()+"이 등록하는 게시물 정보");
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
