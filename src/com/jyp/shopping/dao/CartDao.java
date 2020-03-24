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

import com.jyp.shopping.dto.CartDto;

public class CartDao {

	public static final int SUCCESS = 1; // 장바구니 추가,삭제,수량 수정 성공
	public static final int FAIL = 0; // 실패
	
	private static CartDao instance = new CartDao();
	private CartDao() { }
	public static CartDao getInstance() {
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
	// 카트에 상품 추가
	public int cartInsert(String mId, int pId, int cAmount) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CART (cID, mID, pID, cAMOUNT)" + 
				"    VALUES (CART_SEQ.NEXTVAL, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, pId);
			pstmt.setInt(3, cAmount);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품 추가 성공":"상품 추가 실패");
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
	// 카트에 넣은 상품 정보(수량) 수정
	public int cartUpdate(int cAmount, int cId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CART SET cAMOUNT = ? WHERE cId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cAmount);
			pstmt.setInt(2, cId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"수량 수정 성공":"수량 수정 실패");
		} catch (SQLException e) {
			System.out.println("DAO : " +e.getMessage());
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
	// 카트에 넣은 상품 빼기
	public int cartDelete(int cId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE CART WHERE cId=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"상품 삭제 성공":"상품 삭제 실패");
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
	// 카트에 넣은 상품 빼기(String ver.)
		public int cartDelete(String cId) {
			int result = FAIL;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE CART WHERE cId=?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS?"상품 삭제 성공":"상품 삭제 실패");
			} catch (SQLException e) {
				System.out.println("카드 삭제 예외 : "+e.getMessage());
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
	// 카트에 있는 상품 보기(리스트)
	public ArrayList<CartDto> cartList(String mId, int startRow, int endRow){
		ArrayList<CartDto> dtos = new ArrayList<CartDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT C.*, pIMAGE1, pNAME, pPRICE, pPRICE*cAMOUNT cTOTSUM FROM CART C, PRODUCT P WHERE mID=? AND C.pID=P.pID ORDER BY cDATE DESC)A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cId = rs.getInt("cId");
				int pId = rs.getInt("pId");
				int cAmount = rs.getInt("cAmount");
				Date cDate = rs.getDate("cDate");
				String pImage1 = rs.getString("pImage1");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cTotsum = rs.getInt("cTotsum");
				dtos.add(new CartDto(cId, mId, cAmount, cDate, pId, pImage1, pName, pPrice, cTotsum));
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
	// 카트 전체 상품 개수
	public int cartTotCnt(String mId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM CART WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
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
	// mId로 cart dto 가져오기
	public CartDto cartDto(String mId) {
		CartDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT C.*, pIMAGE1, pNAME, pPRICE, pPRICE*cAMOUNT cTOTSUM FROM CART C, PRODUCT P WHERE mID=? AND C.pID=P.pID ORDER BY cDATE DESC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cId = rs.getInt("cId");
				int pId = rs.getInt("pId");
				int cAmount = rs.getInt("cAmount");
				Date cDate = rs.getDate("cDate");
				String pImage1 = rs.getString("pImage1");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cTotsum = rs.getInt("cTotsum");
				dto = new CartDto(cId, mId, cAmount, cDate, pId, pImage1, pName, pPrice, cTotsum);
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
	// cId로 cart dto 가져오기
	public CartDto cartDto(int cId) {
		CartDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT C.*, pIMAGE1, pNAME, pPRICE, pPRICE*cAMOUNT cTOTSUM FROM CART C, PRODUCT P WHERE cID=? AND C.pID=P.pID ORDER BY cDATE DESC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mId = rs.getString("mId");
				int pId = rs.getInt("pId");
				int cAmount = rs.getInt("cAmount");
				Date cDate = rs.getDate("cDate");
				String pImage1 = rs.getString("pImage1");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int cTotsum = rs.getInt("cTotsum");
				dto = new CartDto(cId, mId, cAmount, cDate, pId, pImage1, pName, pPrice, cTotsum);
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
}
