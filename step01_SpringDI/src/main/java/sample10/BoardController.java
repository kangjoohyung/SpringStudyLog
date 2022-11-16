package sample10;

public class BoardController {
	private BoardService boardService;
	private BoardDTO boardDTO;
	private BoardDTO boardDTO2;
	
	public void test() {
		System.out.println("boardDTO="+boardDTO+",subject : "+boardDTO.getSubject());
		System.out.println("boardDTO2="+boardDTO2+",subject : "+boardDTO2.getSubject());
		
		System.out.println("boardService="+boardService);
	}
}
