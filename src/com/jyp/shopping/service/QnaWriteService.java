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

import com.jyp.shopping.dao.QnaDao;
import com.jyp.shopping.dto.QnaDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 첨부파일 받아오기
		String path = request.getRealPath("qnaFile");
		int maxSize = 1024*1024*10;
		String[] qFile = {"",""};
		MultipartRequest mRequest = null;
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement(); 
				qFile[idx] = mRequest.getFilesystemName(param);
				idx++;				
			}
		} catch (IOException e) {
			System.out.println("첫 번째 예외"+e.getMessage());
		}
		// 파라미터 값 받아오고, dao 호출
		String qCategory = mRequest.getParameter("qCategory");
		String mId = mRequest.getParameter("mId");
		String aId = mRequest.getParameter("aId");
		String qPw = mRequest.getParameter("qPw");
		String qTitle = mRequest.getParameter("qTitle");
		String qContent = mRequest.getParameter("qContent");
		String qFile1 = qFile[0]!=null? qFile[0]:null;
		String qFile2 = qFile[1]!=null? qFile[1]:null;
		String qIp = request.getRemoteAddr();
		QnaDao dao = QnaDao.getInstance();
		QnaDto dto = new QnaDto(0, qCategory, mId, aId, qPw, qTitle, qContent, qFile1, qFile2, null, 0, 0, 0, 0, qIp);
		int result = dao.qnaWrite(dto);
		if(result==QnaDao.SUCCESS) {
			request.setAttribute("qnaWriteResult", dto);		
			request.setAttribute("qnaWriteResultMsg", "문의글이 작성되었습니다.");
		}else {
			request.setAttribute("qnaWriteResultMsg", "문의글 작성 실패, 관리자에게 문의하세요.");		
		}
		
		// 서버에 올린 파일을 폴더에 복사
		for(String file : qFile) {
			if(file!=null) {
				InputStream is = null;
				OutputStream os = null;
				File serverFile = new File(path+"/"+file);
				if(serverFile.exists()) {
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("E:/mega_IT/source/project_jyp/WebContent/qnaFile/"+file);
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
