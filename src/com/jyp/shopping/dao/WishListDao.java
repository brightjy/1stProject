package com.jyp.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jyp.shopping.dto.WishListDto;

public class WishListDao {

	public static final int SUCCESS = 1; // 위시리스트 등록 성공
	public static final int FAIL = 0; // 위시리스트 등록 실패
	
	private static WishListDao instance = new WishListDao();
	private WishListDao() {
		
	}
	public static WishListDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}		
		return conn;
	}
	
	// 위시리스트에 상품 추가
	public int wishListInsert(int pId, String mId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO WISHLIST (wID, pID, mID) VALUES (WISHLIST_SEQ.NEXTVAL, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,pId);
			pstmt.setString(2, mId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"위시리스트 추가 성공":"위시리스트 추가 실패");
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
	
	// 회원의 위시리스트에 있는 상품 리스트 가져오기(mID, startRow, endRow)
	public ArrayList<WishListDto> wishList (String mId, int startRow, int endRow){
		ArrayList<WishListDto> dtos = new ArrayList<WishListDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM(SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT pNAME, pIMAGE1, pPRICE, W.* FROM WISHLIST W, PRODUCT P" + 
				"        WHERE W.pID=P.pID AND mID=? ORDER BY wDATE DESC)A)" + 
				"            WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int wId = rs.getInt("wId");
				Date wDate = rs.getDate("wDate");
				int pId = rs.getInt("pId");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				int pPrice = rs.getInt("pPrice");
				dtos.add(new WishListDto(wId, wDate, pId, mId, pName, pImage1, pPrice));
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
	
	// 회원의 위시리스트 총 개수 가져오기(페이징용)
	public int wishListTotCnt(String mId) {
		int totcnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM WISHLIST W, PRODUCT P WHERE W.pID=P.pID AND mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totcnt = rs.getInt("TOTCNT");
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
		return totcnt;
	}
	
	// 위시리스트에서 삭제
	public int wishListDelete(String mId, int pId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE WISHLIST WHERE mID=? AND pID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mId);
			pstmt.setInt(2, pId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"위시리스트 삭제 성공":"위시리스트 삭제 실패");
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
}
