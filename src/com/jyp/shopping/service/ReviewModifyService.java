package com.jyp.shopping.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dao.ReviewDao;
import com.jyp.shopping.dto.AdminDto;
import com.jyp.shopping.dto.MemberDto;
import com.jyp.shopping.dto.ProductDto;
import com.jyp.shopping.dto.ReviewDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("reviewImg");
		int maxSize = 1024*1024*10;
		String[] rFile = {"",""};
		MultipartRequest mRequest = null;
		
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement(); 
				rFile[idx] = mRequest.getFilesystemName(param);
				idx++;				
			}
		} catch (IOException e) {
			System.out.println("첫 번째 예외"+e.getMessage());
		}
		// reviewDto
		String mId=null;
		String aId=null;
		HttpSession session = request.getSession();
		if(((MemberDto)session.getAttribute("member")).getmId()!=null) {
			mId = ((MemberDto)session.getAttribute("member")).getmId();
		}else {
			aId = ((AdminDto)session.getAttribute("admin")).getaId();
		}
		int rId = Integer.parseInt(mRequest.getParameter("rId"));
		String rTitle = mRequest.getParameter("rTitle");
		// 수저할 때 파일을 첨부하지 않는 경우 원래 파일로
		String dbrFile1 = mRequest.getParameter("dbrFile1");
		String dbrFile2 = mRequest.getParameter("dbrFile2");
		String rFile1 = rFile[1]!=null? rFile[1]:dbrFile1;
		String rFile2 = rFile[0]!=null? rFile[0]:dbrFile2;
		String rPw = mRequest.getParameter("rPw");
		String rContent = mRequest.getParameter("rContent");
		int pId = Integer.parseInt(mRequest.getParameter("pId"));
		int oId = Integer.parseInt(mRequest.getParameter("oId"));
		String rIp = request.getRemoteAddr();
		ReviewDao dao = ReviewDao.getInstance();
		ProductDao pDao = ProductDao.getInstance();
		ProductDto pDto = pDao.productContent(pId);
		ReviewDto dto = new ReviewDto(0, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, null, 0, 0, 0, 0, rIp, pDto.getpName(), pDto.getpImage1()); 
		int result = dao.reviewModify(rTitle, rContent, rFile1, rFile2, rPw, rIp, rId);
		if(result==ReviewDao.SUCCESS) {
			request.setAttribute("reviewWrite", dto);
			request.setAttribute("reviewMsg", "리뷰가 수정되었습니다.");
		}else {
			request.setAttribute("reviewMsg", "리뷰 수정 실패. 관리자에게 문의하세요.");
		}
		// 서버에 올린 파일을 폴더에 복사
		for(String file : rFile) {
			if(file!=null) {
				InputStream is = null;
				OutputStream os = null;
				File serverFile = new File(path+"/"+file);
				if(serverFile.exists()) {
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("E:/mega_IT/source/project_jyp/WebContent/reviewImg/"+file);
						byte[] bs = new byte[(int)serverFile.length()];
						while(true) {
							int nReadbyte = is.read(bs);
							if(nReadbyte == -1) break;
							os.write(bs, 0, nReadbyte);							
						}
					} catch (Exception e) {
						System.out.println("두 번째 예외"+e.getMessage());
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						}catch(Exception e){
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}		
	}

}
