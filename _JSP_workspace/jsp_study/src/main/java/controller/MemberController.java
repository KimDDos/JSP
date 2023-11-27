package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);   
	
	private RequestDispatcher rdp;
	private String destPage;  // 목적지 주소를 저장하는 변수
	private int isOk;  // DB 구문 체크 값 저장 변수
	
	// memberController <=> memberService 인터페이스 연결
	private MemberService msv;
	
	// ServiceImpl과 연결
    public MemberController() {
        msv = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모든 서비스 처리 경로
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(">>> path >>>>> {} ", path);
		log.info(">>> path >>>>> " + path);
		
		switch (path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
		case "register":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				log.info("register check 1");
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info(">>>>>> register mvo >>>>>> {}", mvo);
				
				isOk = msv.register(mvo);
				log.info("register >>> {}", isOk > 0 ? "OK":"Fail");
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("join Error!");
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				// id, pwd를 jsp에서 정송
				// 객체화 하여 DB로 전송
				// 같은 이름의 id/pwd가 있다면 로그인 (세션 객체에 값을 저장)
				// session : 모든 jsp에서 공유되는 객체
				// id가 없으면 , alret 창을 이용하여 로그인 정보가 없습니다. 메시지 띄우기.
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				
				MemberVO mvo = new MemberVO(id, pwd);
				log.info("login check 1 >>>> {}", mvo);
				
				MemberVO loginMvo = msv.login(mvo);  // id와 pwd가 일치하는 데이터의 객체를 리턴 
				log.info("loginMvo >>>>> {}", loginMvo);
				
				// 로그인 객체가 있음을 의미 만약 로그인 객체가 없다면 loginMvo = null
				// 가져온 로그인 객체를 세션에 저장
				if(loginMvo != null) {
					// 세션 객체 가져오기
					// 연결된 세션 객체가 있다면 기존 객체 가져오기, 없으면 생성 [싱글톤임]
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					ses.setMaxInactiveInterval(30*60);  // 로그인 유지시간(초단위로 설정)
				} else {
					// 로그인 객체가 없다면
					request.setAttribute("msg_login", -1);
				}
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("login Error!");
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				HttpSession ses = request.getSession();  // 로그인한 정보가 세션에 입력
				// lastLogin 정보 update
				// ses 에서 mvo 객체로 가져오기
				// session은 object로 반환함, 형변환 필요!!
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				log.info("ses에서 추출한 mvo >>>>> {}",mvo);
				
				// update
				isOk = msv.lastLogin(mvo.getId());
				log.info("lastLogin >>>> {}", isOk > 0 ? "Ok":"Fail");
				ses.invalidate();  // 세션 무효화 (세션 끊기)
				
			} catch (Exception e) {
				log.info("logout Error!");
				e.printStackTrace();
			}
			break;
		default: break;
		
		}
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get으로 오는 요청 처리
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post로 오는 요청 처리
		service(request, response);
	}

}
