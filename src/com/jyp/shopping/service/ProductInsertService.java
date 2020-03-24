package com.jyp.shopping.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyp.shopping.dao.ProductDao;
import com.jyp.shopping.dto.ProductDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("productImg");
		int maxSize = 1024*1024*10;
		String[] pImage = {"",""};
		MultipartRequest mRequest = null;
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			int idx = 0;
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				pImage[idx] = mRequest.getFilesystemName(param);
				idx++;				
			}			
		} catch (Exception e) {
			System.out.println("첫 번째 예외"+e.getMessage());
		}	
		String pCategory = mRequest.getParameter("pCategory");
		String pName = mRequest.getParameter("pName");
		int pPrice = Integer.parseInt(mRequest.getParameter("pPrice"));
		int pDiscount = Integer.parseInt(mRequest.getParameter("pDiscount"));
		String pPolicy1 = mRequest.getParameter("pPolicy1");
		String pPolicy2 = mRequest.getParameter("pPolicy2");
		int pStock = Integer.parseInt(mRequest.getParameter("pStock"));
		String pContent = mRequest.getParameter("pContent");
		String pImage1 = pImage[1]!=null? pImage[1]:null;
		String pImage2 = pImage[0]!=null? pImage[0]:null;
		
		ProductDao dao = ProductDao.getInstance();
		ProductDto dto = new ProductDto(0, pCategory, pName, pPrice, pDiscount, pImage1, pImage2, pContent, pPolicy1, pPolicy2, pStock, null, 0); 
		int result = dao.productInsert(dto);
		if(result==ProductDao.SUCCESS) {
			request.setAttribute("product", dto);
			request.setAttribute("productInsertMsg", "상품 등록을 성공했습니다.");
		}else {
			request.setAttribute("productInsertMsg", "상품 등록을 실패했습니다."+dto.toString());
		}
		
		for(String img : pImage) {
			if(img!=null) {
				InputStream is = null;
				OutputStream os = null;
				File serverFile = new File(path+"/"+img);
				if(serverFile.exists()) {
					try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("E:/mega_IT/source/project_jyp/WebContent/productImg/"+img);
						byte[] bs = new byte[(int)serverFile.length()];
						while(true) {
							int nReadbyte = is.read(bs);
							if(nReadbyte==-1) break;
							os.write(bs, 0, nReadbyte);
						}
					} catch (Exception e) {
						System.out.println("두 번째 예외"+e.getMessage());
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
	}
}
