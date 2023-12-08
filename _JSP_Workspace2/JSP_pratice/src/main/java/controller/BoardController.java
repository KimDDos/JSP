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

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	
	private BoardService bsv;
       
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 처리 메서드
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		log.info("uri >>>>{}",uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path >>>>{}",path);
	
		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
		case "insert":
			try {
				HttpSession ses = request.getSession();
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info("insert check 1");
				
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("insert bvo >>>>{}",bvo);
				
				isOk = bsv.insert(bvo);
				log.info("insert result >>>> {}", isOk>0?"Ok":"Fail");
				
				if(isOk > 0) {
					request.setAttribute("msg_insert", 1);
				} else if(isOk == 0) {
					request.setAttribute("msg_insert", -1);
				} else {request.setAttribute("msg_insert", 0);}
				
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("insert Error!");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				// index에서 list 버튼을 클릭하면 
				// 컨트롤러에서 db로 전체 리스트 요청
				// 전체 리스트를 가지고 list.jsp에 뿌리기 
				log.info("list check 1");
				PagingVO pgvo = new PagingVO();
				
				if(request.getParameter("pageNo") != null) {
					int pageNo = Integer.parseInt(request.getParameter("pageNo"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					String type = request.getParameter("type");
					String keyword = request.getParameter("keyword");
					pgvo = new PagingVO(pageNo, qty, type, keyword);
				}
				// 검색한 값의 게시글 카운트
				
				List<BoardVO> list = bsv.getlist(pgvo);
				int totalCount = bsv.boardCount(pgvo);
				log.info("getlist >>>>>> {}",list);
				
				request.setAttribute("list", list);
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("list Error!");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				HttpSession ses = request.getSession();
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.readcount(bno);
				log.info("readCount result >>>> {}", isOk > 0 ? "Ok":"Fail");
				BoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("detail Error!");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				HttpSession ses = request.getSession();
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.detail(bno);
				request.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				log.info("modify Error!");
				e.printStackTrace();
			}
			break;
			
		case "modifyDetail":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				BoardVO bvo = new BoardVO(bno, title, content);
				log.info("modifyDetail >>>> {}",bvo);
				
				isOk = bsv.modify(bvo);
				log.info("modifyDetail isOK? >>>> {}", isOk > 0 ? "Ok":"Fail");
				
				if(isOk > 0) {
					request.setAttribute("msg_modify", 1);
				} else if(isOk == 0) {
					request.setAttribute("msg_modify", -1);
				} else {request.setAttribute("msg_modify", 0);}
				
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				log.info("modifyDetail Error!");
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				isOk = bsv.remove(bno);
				log.info("remove result >>> {}",isOk > 0 ? "Ok":"Fail");
				
				if(isOk > 0) {
					request.setAttribute("msg_remove", 1);
				} else if(isOk == 0) {
					request.setAttribute("msg_remove", -1);
				} else {request.setAttribute("msg_remove", 0);}
				destPage = "/index.jsp";
			} catch (Exception e) {
				log.info("remove Error!");
				e.printStackTrace();
			}
			break;
		case "myboard":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				
				List<BoardVO> list = bsv.getMylist(mvo.getId());
				request.setAttribute("list", list);
				
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("myboard Error!");
				e.printStackTrace();
			}
			break;
		default: break;
		}
		
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청에 대한 처리
		service(request, response);
	}

}
