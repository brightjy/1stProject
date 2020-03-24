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

import com.jyp.shopping.dto.OrdersDetailDto;

public class OrdersDetailDao {

	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static OrdersDetailDao instance = new OrdersDetailDao();
	private OrdersDetailDao() { }
	public static OrdersDetailDao getInstance() {
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
	
	// 오더 디테일 생성하기 
	public int ordersDetailInsert(String pId, String odAmount) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO ORDERS_DETAIL(odID, oID, pID, odAMOUNT) VALUES (ORDERS_DETAIL_SEQ.nextval, ORDERS_SEQ.CURRVAL, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pId);
			pstmt.setString(2, odAmount);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"오더디테일생성성공":"오더디테일생성실패");
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
	// oID로 odID dto 불러오기
	public ArrayList<OrdersDetailDto> ordersDetailsDto(int oId) {
		ArrayList<OrdersDetailDto>  dtos = new ArrayList<OrdersDetailDto>() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT oDATE, OD.*, pIMAGE1, pNAME, pPRICE, pPRICE*odAMOUNT ODTOTSUM FROM ORDERS O, ORDERS_DETAIL OD, PRODUCT P WHERE O.OID=OD.OID AND OD.pID=P.pID AND od.oID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int odId = rs.getInt("odId") ;
				int pId = rs.getInt("pId");
				int odAmount = rs.getInt("odAmount");
				String pImage1 = rs.getString("pImage1");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int odTotsum = rs.getInt("odTotsum");
				Date oDate = rs.getDate("oDate");
				dtos.add(new OrdersDetailDto(odId, oId, pId, odAmount, pImage1, pName, pPrice, odTotsum, oDate));
			}
		} catch (SQLException e) {
			System.out.println("ordersDetailsDto 예외 : "+e.getMessage());
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
	
	// mId로 odId dto 불러오기
	public ArrayList<OrdersDetailDto> ordersDetailsDto(String mId, int startRow, int endRow){
		ArrayList<OrdersDetailDto> dtos = new ArrayList<OrdersDetailDto>() ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM (SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT oDATE, OD.*, pIMAGE1, pNAME, pPRICE, pPRICE*odAMOUNT ODTOTSUM FROM" + 
				"        ORDERS O, ORDERS_DETAIL OD, PRODUCT P WHERE O.OID=OD.OID AND OD.pID=P.pID AND mID=?" + 
				"            ORDER BY O.OID DESC)A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int odId = rs.getInt("odId");
				int oId = rs.getInt("oId");
				int pId = rs.getInt("pId");
				int odAmount = rs.getInt("odAmount");
				String pImage1 = rs.getString("pImage1");
				String pName = rs.getString("pName");
				int pPrice = rs.getInt("pPrice");
				int odTotsum = rs.getInt("odTotsum");
				Date oDate = rs.getDate("oDate");
				dtos.add(new OrdersDetailDto(odId, oId, pId, odAmount, pImage1, pName, pPrice, odTotsum, oDate));
			}
		} catch (SQLException e) {
			System.out.println("ordersDetailsDto 예외 : "+e.getMessage());
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
	
	// mId로 전체 개수 구하기
	public int ordersDetailTotCnt(String mId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) TOTCNT FROM ORDERS O, ORDERS_DETAIL OD, PRODUCT P WHERE O.OID=OD.OID AND OD.pID=P.pID AND mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mId);
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
	
	// 오더디테일  전체 개수
	
	// 오더 리스트 전체 불러오기
	
	

}
