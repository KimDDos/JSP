package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	private String savePath;
	
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
				// 파일을 업로드할 물리적인 경로 설정
				// ServletContext를 얻기 위해 getServletContext() 메서드를 사용
				// ServletContext context = config.getServletContext();
				
				savePath = getServletContext().getRealPath("/_fileUpload");
				// servlet 의 설정에 대한 정보를 담고 있는 것을 들고오는 메서드
				
				File fileDir = new File(savePath);
				// 실제 파일경로를 저장하고 조작, 통제하기 위한 메서드임
				log.info("저장위치 >>>> {}", savePath);
				// D:\KDY\_JSP_Workspace2\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSP_pratice\_fileUpload
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				// 저장할 위치를 file 객체로 지정, 매개변수 자체가 파일 객체를 사용함
				
				fileItemFactory.setSizeThreshold(1024*1024*3);  // 파일 저장을 위한 임시공간 크기
				
				// 미리 board 객체 설정
				BoardVO bvo = new BoardVO();
				
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주기
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				log.info("itemList >>>> {}", itemList);
				log.info("itemList >>>> {}" + itemList);
				log.info("itemList >>>> {}", itemList.toString());
				
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "imageFile":
						// 이미지가 있는지 체크
						if(item.getSize() > 0) {
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							// C:// ~~~~ / fileName.jpg 에서 이름만 추출하는거임
							// 이름만 분리, File.separator 보다는 File.separatorChar 를 동적으로 처리하는게 좋음
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("uploadFilePath >>>>>>> {}", uploadFilePath);
							try {
								item.write(uploadFilePath); // 자바 객체를 디스크에 쓰기
								bvo.setImageFile(fileName);
								
								Thumbnails.of(uploadFilePath).size(30, 30).toFile(new File(fileDir + File.separator +"_th_"+fileName));
								// File.separatorChar를 쓰니까 적용이 안 됨 
								// => Thumbnauls.of 및 toFile 메서드에서 사용하는 라이브러리가 내부적으로 경로를 다루는데 있어
								// 특정 문자를 기대하는 경우가 있음. 이 경우 라리브러리가 특별한 이유로 플랫폼에 상관없이 특정 문자를 요구하는 경우가 있을 수 있음
								// => 그렇기 때문에 특정 라이브러리나 API에서는 file.separatorChar 를 사용할 수 없는 경우가 있음
								
							} catch (Exception e) {
								log.info(">>>> file writer on disk error!");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				
				isOk = bsv.insert(bvo);
				log.info("insert result >>>> {}", isOk>0?"Ok":"Fail");
				
				destPage = "/index.jsp";
				
				/*
				 * String title = request.getParameter("title"); String writer =
				 * request.getParameter("writer"); String content =
				 * request.getParameter("content"); log.info("insert check 1");
				 * 
				 * BoardVO bvo = new BoardVO(title, writer, content);
				 * log.info("insert bvo >>>>{}",bvo);
				 * 
				 * isOk = bsv.insert(bvo); log.info("insert result >>>> {}",
				 * isOk>0?"Ok":"Fail");
				 * 
				 * if(isOk > 0) { request.setAttribute("msg_insert", 1); } else if(isOk == 0) {
				 * request.setAttribute("msg_insert", -1); } else
				 * {request.setAttribute("msg_insert", 0);}
				 * 
				 * destPage = "/index.jsp";
				 */
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
					log.info(">>>> pageNo / qty / type / keyWord : "+ pageNo +" / "+ qty +" / "+ type +" / "+ keyword);
					pgvo = new PagingVO(pageNo, qty, type, keyword);
				}
				// 검색한 값의 게시글 카운트
				
				List<BoardVO> list = bsv.getlist(pgvo);
				int totalCount = bsv.boardCount(pgvo);
				PagingHandler ph = new PagingHandler(pgvo, totalCount);
				log.info("getlist >>>>>> {}",list);
				
				request.setAttribute("list", list);
				request.setAttribute("ph", ph);
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
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemlist = fileUpload.parseRequest(request);
				String old_file = null;
				
				for(FileItem item : itemlist) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString("utf-8")));
						break;
					case "title":
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "imageFile":
						old_file = item.getString("utf-8");
						break;
					case "new_file":
						// 새로운 파일은 등록이 될 수도 있고, 안 될수도 있음
						if(item.getSize() > 0) {
							if(old_file != null) {
								FileRemoveHandler frh = new FileRemoveHandler();
								isOk = frh.deletFile(old_file, savePath);
							}
							// 새로운 파일에 대한 객체 작업
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir + File.separator + "_th_" + fileName);
							
							try {
								item.write(uploadFilePath);
								bvo.setImageFile(fileName);
								
								Thumbnails.of(uploadFilePath).size(30, 30).toFile(new File(fileDir + File.separator + "_th_" + fileName));
							} catch (Exception e) {
								log.info("modifyDetail new_file Error!");
								e.printStackTrace();
							}
						} else {
							bvo.setImageFile(old_file);
						}
						break;
					}
				}
				isOk = bsv.modify(bvo);
				log.info("modifyDetail isOK? >>>> {}", isOk > 0 ? "Ok":"Fail");
				destPage = "list";
				
				/*
				 * int bno = Integer.parseInt(request.getParameter("bno")); String title =
				 * request.getParameter("title"); String content =
				 * request.getParameter("content");
				 * 
				 * BoardVO bvo = new BoardVO(bno, title, content);
				 * log.info("modifyDetail >>>> {}",bvo);
				 * 
				 * isOk = bsv.modify(bvo); 
				 * log.info("modifyDetail isOK? >>>> {}", isOk > 0 ? "Ok":"Fail");
				 * 
				 * if(isOk > 0) { request.setAttribute("msg_modify", 1); } else if(isOk == 0) {
				 * request.setAttribute("msg_modify", -1); } else
				 * {request.setAttribute("msg_modify", 0);}
				 * 
				 * destPage = "/index.jsp";
				 */
				
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
