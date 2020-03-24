package com.jyp.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jyp.shopping.dto.ProductDto;

public class ProductDao {
	
	public static final int SUCCESS = 1; // 상품 등록 성공
	public static final int FAIL = 0; // 상품 등록 실패
	
	private static ProductDao instance = new ProductDao();
	private ProductDao() {
		
	}
	public static ProductDao getInstance() {
		return instance;
	}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}		
		return conn;
	}
	// 상품 리스트(startRow, endRow)
	public ArrayList<ProductDto> productList(int startRow, int endRow){
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM PRODUCT ORDER BY pDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pId = rs.getInt("pId");
				String pCategory = rs.getString("pCategory");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int pDiscount = rs.getInt("pDiscount");
				String pImage1 = rs.getString("pImage1");
				String pImage2 = rs.getString("pImage2");
				String pContent = rs.getString("pContent");
				String pPolicy1 = rs.getString("pPolicy1");
				String pPolicy2 = rs.getString("pPolicy2");
				int pStock = rs.getInt("pStock");
				Date pDate = rs.getDate("pDate");
				int pHit = rs.getInt("pHit");
				dtos.add(new ProductDto(pId, pCategory, pName, pPrice, pDiscount, pImage1, pImage2, pContent, pPolicy1, pPolicy2, pStock, pDate, pHit));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 전체 상품 개수
	public int productTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM PRODUCT";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt("TOTCNT");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return totCnt;
	}
	// 상품 클릭 할 때마다 히트 수 하나 씩 올리기
	public int productHitUp(int pId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET pHIT = pHIT+1 WHERE pID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"히트 업 성공":"히트 업 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}			
		
		
		return result;
	}
	
	
	// 상품 상세보기
	public ProductDto productContent(int pId) {
		
		productHitUp(pId);
		
		ProductDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM PRODUCT WHERE pID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pCategory = rs.getString("pCategory");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int pDiscount = rs.getInt("pDiscount");
				String pImage1 = rs.getString("pImage1");
				String pImage2 = rs.getString("pImage2");
				String pContent = rs.getString("pContent");
				String pPolicy1 = rs.getString("pPolicy1");
				String pPolicy2 = rs.getString("pPolicy2");
				int pStock = rs.getInt("pStock");
				Date pDate = rs.getDate("pDate");
				int pHit = rs.getInt("pHit");
				dto = new ProductDto(pId, pCategory, pName, pPrice, pDiscount, pImage1, pImage2, pContent, pPolicy1, pPolicy2, pStock, pDate, pHit);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}				
		return dto;
	}
	// 상품 등록
	public int productInsert(ProductDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCT (pID, pCATEGORY, pNAME, pPRICE, pDISCOUNT," + 
				"    pIMAGE1, pIMAGE2, pCONTENT, pPOLICY1, pPOLICY2, pSTOCK)" + 
				"    VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getpCategory());
			pstmt.setString(2, dto.getpName());
			pstmt.setInt(3, dto.getpPrice());
			pstmt.setInt(4, dto.getpDiscount());
			pstmt.setString(5, dto.getpImage1());
			pstmt.setString(6, dto.getpImage2());
			pstmt.setString(7, dto.getpContent());
			pstmt.setString(8, dto.getpPolicy1());
			pstmt.setString(9, dto.getpPolicy2());
			pstmt.setInt(10, dto.getpStock());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품등록 성공":"상품등록 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("상품등록 실패"+dto.toString());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}						
		return result;
	}
	// 상품 삭제
	public int productDelete(int pId) {
		int result = FAIL; // 상품 삭제 실패
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE PRODUCT WHERE pID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품삭제 성공":"상품삭제 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}								
		return result;
	}
	// 상품 정보 수정
	public int productModify(ProductDto dto) {
		int result = FAIL; // 상품 정보 수정 실패
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT" + 
				"    SET pCATEGORY = ?," + 
				"        pNAME = ?," + 
				"        pPRICE = ?," + 
				"        pDISCOUNT = ?," + 
				"        pIMAGE1 = ?," + 
				"        pIMAGE2 = ?," + 
				"        pCONTENT = ?," + 
				"        pPOLICY1 = ?," + 
				"        pPOLICY2 = ?," + 
				"        pSTOCK = ?" + 
				"    WHERE pID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getpCategory());
			pstmt.setString(2, dto.getpName());
			pstmt.setInt(3, dto.getpPrice());
			pstmt.setInt(4, dto.getpDiscount());
			pstmt.setString(5, dto.getpImage1());
			pstmt.setString(6, dto.getpImage2());
			pstmt.setString(7, dto.getpContent());
			pstmt.setString(8, dto.getpPolicy1());
			pstmt.setString(9, dto.getpPolicy2());
			pstmt.setInt(10, dto.getpStock());
			pstmt.setInt(11, dto.getpId());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품 정보수정 성공":"상품 정보수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("상품 정보수정 실패"+dto.toString());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}								
		return result;
	}	
	// 주문 시 실재 재고에 반영
	public int afterOrdersStock(String odAmount, String pId) {
		int result = FAIL; // 상품 정보 수정 실패
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET pSTOCK = pSTOCK - ? WHERE pID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, odAmount);
			pstmt.setString(2, pId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품 재고반영 성공":"상품 재고반영 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}								
		return result;
	}
	
	// 베스트 30 가져오기
	public ArrayList<ProductDto> productListBest(){
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM PRODUCT ORDER BY pHIT DESC) A) WHERE RN BETWEEN 1 AND 30";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int pId = rs.getInt("pId");
				String pCategory = rs.getString("pCategory");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int pDiscount = rs.getInt("pDiscount");
				String pImage1 = rs.getString("pImage1");
				String pImage2 = rs.getString("pImage2");
				String pContent = rs.getString("pContent");
				String pPolicy1 = rs.getString("pPolicy1");
				String pPolicy2 = rs.getString("pPolicy2");
				int pStock = rs.getInt("pStock");
				Date pDate = rs.getDate("pDate");
				int pHit = rs.getInt("pHit");
				dtos.add(new ProductDto(pId, pCategory, pName, pPrice, pDiscount, pImage1, pImage2, pContent, pPolicy1, pPolicy2, pStock, pDate, pHit));
			}			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}				
		
		
		return dtos;
	}
}
