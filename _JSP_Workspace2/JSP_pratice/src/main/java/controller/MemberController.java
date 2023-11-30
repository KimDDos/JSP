package controller;

import java.io.IOException;
import java.util.List;

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
import service.memberService;
import service.memberServiceImpl;

@WebServlet("/memb/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	
	private memberService msv;
       
    public MemberController() {
    	msv = new memberServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		switch (path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
		case "signup":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info("signup mvo >>>>>> {}",mvo);
				
				isOk = msv.signup(mvo);
				log.info("signup result >>>> {}", isOk > 0 ? "Ok":"Fail");
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("signup Error!");
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				MemberVO mvo = new MemberVO(id, pwd);
				MemberVO loginvo = msv.login(mvo);
				
				log.info("mvo >>>> {}",mvo);
				log.info("loginvo >>>> {}",loginvo);
				
				if(loginvo != null) {
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginvo);
					ses.setMaxInactiveInterval(20*60);
				} else {
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
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("test1 >>> {}",id);
				log.info("test2 >>> {}",ses.getId());
				isOk = msv.lastloginupdate(id); 
				log.info("logout result >>> {}",isOk > 0 ? "Ok":"Fail");
				ses.setAttribute("msg_logout", 1);
				ses.invalidate();
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("logout Error!");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				HttpSession ses = request.getSession();
				List <MemberVO> list = msv.getList();
				request.setAttribute("list", list);
				destPage = "/member/list.jsp";
			} catch (Exception e) {
				log.info("member list Error!");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				HttpSession ses = request.getSession();
				destPage = "/member/detail.jsp";
			} catch (Exception e) {
				log.info("detail list Error!");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				String id = request.getParameter("id");
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info("mvo >>>> {}", mvo);
				isOk = msv.modify(mvo);
				log.info("modify result >>>> {}", isOk > 0 ? "Ok":"Fail");
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("member modify Error!");
				e.printStackTrace();
			}
			break;
		case "signout":
			try {
				String id = request.getParameter("id");
				isOk = msv.remove(id);
				log.info("remove result >>>> {}",isOk > 0 ? "Ok":"Fail");
				HttpSession ses = request.getSession();
				ses.invalidate();
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("signout Error!");
				e.printStackTrace();
			}
			break;
		default: break;
		}
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
