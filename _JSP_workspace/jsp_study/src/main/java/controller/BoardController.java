package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.PagingHandler;
import service.BoardService;
import service.BoardServiceImpl;

/**
 * 화면에 있는 요소들을 받을수 있는 servlet controller임
 */

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 로그 기록 객체 생성
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	// slf4j의 로거를 임포트 해야함
	
	// jsp에서 받은 요청을 처리, 처리결과를 다른 jsp로 보내는 역할
	private RequestDispatcher rdp;
	private String destPage;  // 목적지 주소를 저장하는 변수
	private int isOk;  // DB 구문 체크 값 저장 변수
	
	// controller <-> service
	private BoardService bsv;  // interface 생성 / sercive 패키지에
	
    public BoardController() {
    	bsv = new BoardServiceImpl();  // class로 생성 bsv를 구현할 객체
    	// 생성자
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 처리 메서드
		log.info("필요한 로그 띄우기 가능");
		// 좀더 빨리 처리하고, DB저장, 콘솔에만 띄우는게 아닌 별도 처리도 가능
		request.setCharacterEncoding("utf-8"); // 요청
		response.setCharacterEncoding("utf-8"); // 응답
		
		response.setContentType("text/html; charset=UTF-8");
		// 동기방식으로 진행되기에 이렇게 응답을 보냄
		
		String uri = request.getRequestURI(); // jsp에서 오는 요청주소를 받는 설정
		System.out.println("sysout을 통한 로그 >>" + uri);
		log.info("log 객체를 통한 로그"+uri);  // /brd/register
		
		String path = uri.substring(uri.lastIndexOf("/")+1);  // register 만 추출
		log.info("실 요청경로 >>> "+path);  
		
		switch(path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
		case "insert":
			try {
				// jsp에서 데이터를 입력 후 => controller로 전송
				// DB에 등록한 후 => index.jsp로 이동
				
				// jsp에서 가져온 title, writer, content를 꺼내오기
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info(">>> insert chek 1");
				
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("insert bvo >>> {}", bvo);
				
				// 만들어진 bvo 를 db에 저장
				isOk = bsv.register(bvo);
				log.info("board register", isOk > 0 ? "OK":"Fail");
				
				// 목적지 주소
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("insert error!");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				// index에서 list 버튼을 클릭하면
				// 컨트롤러에서 db로 전체 리스트 요청
				// 전체 리스트를 가지고 list.jsp에 뿌리기
				log.info("list check 1");
				PagingVO pgvo = new PagingVO();  //   1 / 10 / 0
				// 아래처럼 하면 에러가 발생함
				// int pageNo = Integer.parseInt(request.getParameter("pageNo"));
				if(request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					log.info(">>>> pageNo / qty "+ pageNo + qty );
					pgvo = new PagingVO(pageNo, qty);
				}
				
				List<BoardVO> list = bsv.getList(pgvo);
				int totalCount = bsv.boardCount();
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				
				// list를 jsp로 전송
				request.setAttribute("list", list);
				request.setAttribute("ph", ph);
				destPage="/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list error!");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				// list.jsp에서 title, bno를 클릭하면
				// jsp에서 보낸 bno를 받아서 
				// 해당 번호의 전체 값을 조회하여 detail.jsp에 뿌리기
				// 모든 파라미터는 String으로 리턴됨.
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>>>> detail chack 1");
				
				BoardVO bvo = bsv.getDetail(bno);
				log.info(">>>>>> detail bvo >>>>>> {}" + bvo);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
				
			} catch (Exception e) {
				log.info("detail error!");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				// 수정할 데이터의 bno를 받아서 수정 페이지로 보내서 
				// modify.jsp로 이동
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>>>> modify check 1");
				
				BoardVO bvo = bsv.getDetail(bno);
				
				request.setAttribute("bvo", bvo);
				destPage="/board/modify.jsp";
			
			} catch (Exception e) {
				log.info("Modify Error!");
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				// 파라미터로 받은 bno, title, content 데이터를 DB에 수정하여 넣고, list로 이동
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				BoardVO bvo = new BoardVO(bno, title, content);
				log.info("edit check 1");
				log.info(">>>>>>>> edit {}" + bvo);
				
				isOk = bsv.modify(bvo);
				log.info("edit >> {}", isOk > 0 ? "OK":"Fail");
				destPage="list";
				// 이렇게 하면 내부 list case로 이동
				
			} catch (Exception e) {
				log.info("edit Error!");
				e.printStackTrace();
			}
			
			break;
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.remove(bno);
				log.info("remove check 1");
				log.info(">>>>>>>> edit {}" + bno);
				
				log.info("remove >>>>>>>> {}", isOk > 0 ? "Ok":"Fail");
				
				destPage = "list";
				
			} catch (Exception e) {
				log.info("Remove Error!");
				e.printStackTrace();
			}
			break;
		default: break;
		}
		
		// 목적지 주소로 데이터를 전달(requestDispatcher)
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);  // 요청에 필요한 객체를 가지고 경로로 전송
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get으로 오는 요청 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post로 오는 요청을 처리
		service(request, response);
	}

}
