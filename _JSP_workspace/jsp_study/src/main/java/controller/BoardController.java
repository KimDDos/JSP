package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private String destPadge;  // 목적지 주소를 저장하는 변수
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
