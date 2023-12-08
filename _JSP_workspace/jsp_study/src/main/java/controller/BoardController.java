package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileRemoveHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;
import service.CommentService;
import service.CommentServiceImpl;

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
	private String savePath;  // File 저장경로를 저장하는 변수  
	
	// controller <-> service
	private BoardService bsv;  // interface 생성 / sercive 패키지에
	private CommentService csv = new CommentServiceImpl();
	
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
				// 파일을 업로드할 물리적인 경로 설정
				// ServletContext를 얻기 위해 getServletContext() 메서드를 사용합니다.
		        // ServletContext context = config.getServletContext();

		        // 여기에서 ServletContext를 사용하여 웹 애플리케이션의 정보에 접근할 수 있습니다.
				// String appName = context.getServletContextName();
		        // String uploadPath = context.getRealPath("/uploads");
				
				// D:\KDY\_JSP_workspace\jsp_study\src\main\webapp\_fileUpload
				savePath = getServletContext().getRealPath("/_fileUpload");
				// Servlet 의 설정에 대한 정보를 담고 있는 것을 들고오는 메서드
				// 임시 디렉터리 설정 (디스크에 임시 파일을 저장하는 경로)
				File fileDir = new File(savePath);
				// 실제 파일경로를 저장하고 조작, 통제하기 위한 메서드임
				log.info("저장위치 >>>>>> {}", savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);  // 저장할 위치를 file 객체로 지정
				// 매개변수 자체가 파일 객체를 사용함.
				
				fileItemFactory.setSizeThreshold(1024*1024*3);  // 파일 저장을 위한 임시 메모리 설정 : byte 단위
				
				// 미리 Board 객체 설정
				BoardVO bvo = new BoardVO();
				
				// multipart/for-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				// HTTP 요청 파싱 및 파일 아이템 얻기
				List<FileItem> itemList = fileUpload.parseRequest(request); 
				
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						// utf-8로 바꿔줘야 한글이 안 깨짐
						bvo.setTitle(item.getString("utf-8"));
						break;
					case "writer":
						bvo.setWriter(item.getString("utf-8"));
						break;
					case "content":
						bvo.setContent(item.getString("utf-8"));
						break;
					case "image_file":
						// 이미지 있는지 체크
						if(item.getSize() > 0) { // data의 크기를 바이트 단위로 리턴 / 크기가 0보다 큰지 체크
							// 경로를 포함한 이름  ~~~~~ / dog.jpg
							// window : /  ,mac : \  이니까 구분 잘 할 것
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1); // 이름만 분리
							// File.separator : 파일 경로 기호를 저장.
							// 시스템의 시간을 이용하여 파일을 구분 / 시간_dog.jpg
							fileName = System.currentTimeMillis() + "_" + fileName;
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("uploadFilePath >>>>>>>>>> {}" + uploadFilePath.toString());
							
							// 저장
							try {
								item.write(uploadFilePath);  // 자바 객체를 디스크에 쓰기
								bvo.setImageFile(fileName);
								
								// 썸네일 작업 : 리스트 페이지에서 트레픽 과다사용 방지, 필수임!
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"_th_"+fileName));
								
							} catch (Exception e) {
								log.info(">>>> file writer on disk error! <<<<");
								e.printStackTrace();
							}
						} 
						break;
					default: break;
					}
				}
				
				isOk = bsv.register(bvo);
				log.info("insert >>>> {}",bvo);
				log.info("board register", isOk > 0 ? "OK":"Fail");
				
				// 목적지 주소
				destPage = "/index.jsp";
				
				// fileUpload 없을 경우
//				// jsp에서 데이터를 입력 후 => controller로 전송
//				// DB에 등록한 후 => index.jsp로 이동
//				
//				// jsp에서 가져온 title, writer, content를 꺼내오기
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				log.info(">>> insert chek 1");
//				
//				BoardVO bvo = new BoardVO(title, writer, content);
//				log.info("insert bvo >>> {}", bvo);
//				
//				// 만들어진 bvo 를 db에 저장
//				isOk = bsv.register(bvo);
//				log.info("board register", isOk > 0 ? "OK":"Fail");
//				
//				// 목적지 주소
//				destPage = "/index.jsp";
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
					String type = request.getParameter("type");
					String keyWord = request.getParameter("keyWord");
					log.info(">>>> pageNo / qty / type / keyWord : "+ pageNo +" / "+ qty +" / "+ type +" / "+ keyWord);
					pgvo = new PagingVO(pageNo, qty, type, keyWord);
				} 
				// 검색한 값의 게시글 카운트
				
				List<BoardVO> list = bsv.getList(pgvo);
				int totalCount = bsv.boardCount(pgvo);
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
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("저장위치 >>>>>> {}", savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				
				fileItemFactory.setSizeThreshold(1024*1024*3);
				
				BoardVO bvo = new BoardVO();
				
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				String old_file = null;
				
				// bno, title, content, image_file
				for(FileItem item : itemList) {
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
					case "image_file":
						// 이전 파일의 보관을 위해 활용
						old_file = item.getString("utf-8");
						break;
					case "new_file":
						// 새로운 파일은 등록이 될 수도 있고, 안될 수도 있음
						if(item.getSize() > 0) {
							// 새로운 등록파일이 있다면
							if(old_file != null) {
								// old_file 삭제 작업
								// 파일 삭제 핸들러를 톻해서 파일 삭제 작업 진행
								FileRemoveHandler frh = new FileRemoveHandler();
								isOk = frh.deleteFile(old_file, savePath);
							}
							// 새로운 파일에 대한 객체 작업
							String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
							log.info("fileName : "+fileName);
							fileName = System.currentTimeMillis() + "_" + fileName;
							log.info("fileName : "+fileName);
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("uploadFilePath : "+uploadFilePath);
							
							try {
								item.write(uploadFilePath);
								bvo.setImageFile(fileName);
								
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"_th_"+fileName));
							} catch (Exception e) {
								log.info("fileModify writer on disk error!");
								e.printStackTrace();
							}
							
						} else {
							// 기존 이미지 파일은 있지만, 새로운 이미지 파일이 없다면 ...
							// 기존 파일을 담지 않으면 null로 바뀜
							bvo.setImageFile(old_file);  // 기존 객체를 bvo에 담기
						}
						break;
					}
					
				}
				
				isOk = bsv.modify(bvo);
				log.info("edit >> {}", isOk > 0 ? "OK":"Fail");
				destPage="list";
				
				
//				// 파라미터로 받은 bno, title, content 데이터를 DB에 수정하여 넣고, list로 이동
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				
//				BoardVO bvo = new BoardVO(bno, title, content);
//				log.info("edit check 1");
//				log.info(">>>>>>>> edit {}" + bvo);
//				
//				isOk = bsv.modify(bvo);
//				log.info("edit >> {}", isOk > 0 ? "OK":"Fail");
//				destPage="list";
//				// 이렇게 하면 내부 list case로 이동
				
			} catch (Exception e) {
				log.info("edit Error!");
				e.printStackTrace();
			}
			
			break;
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				savePath = getServletContext().getRealPath("/_fileUpload");
				log.info("저장위치 >>>>>> {}", savePath);
				String imageFile = bsv.searchImage(bno);
				
				// 게시판의 파일 먼저 삭제
				if(imageFile != null) {
					FileRemoveHandler frh = new FileRemoveHandler();
					frh.deleteFile(imageFile, savePath);
					log.info(">>>>>  imagefile delete success!!  <<<<<");
				}
				
				// 게시판의 댓글들 삭제
				int isOk = csv.commentDel(bno); 
				log.info(">>>>> "+isOk+"개 댓글 삭제 완료! <<<<<<");
				
				isOk = bsv.remove(bno);
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
