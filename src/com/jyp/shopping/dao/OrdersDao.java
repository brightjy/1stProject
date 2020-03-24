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

import com.jyp.shopping.dto.OrdersDto;

public class OrdersDao {

	public static final int SUCCESS = 1; // 주문성공
	public static final int FAIL = 0; // 주문실패
	
	private static OrdersDao instance = new OrdersDao();
	private OrdersDao() { }
	public static OrdersDao getInstance() {
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
	
	// [관리자용] 전체 오더 리스트 보기(startRow, endRow)
	public ArrayList<OrdersDto> OrdersListAdmin(int startRow, int endRow){
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM ORDERS ORDER BY oDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int oId = rs.getInt("oId");
				Date oDate = rs.getDate("oDate");
				String mId = rs.getString("mID");
				String oName = rs.getString("oName");
				String oPhone = rs.getString("oPhone");
				String oAddress = rs.getString("oAddress");
				String oPayment = rs.getString("oPayment");
				int oInvoice = rs.getInt("oInvoice");
				dtos.add(new OrdersDto(oId, oDate, mId, oName, oPhone, oAddress, oPayment, oInvoice));
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
	// [사용자용] 전체 오더 리스트 보기(mId, startRow, endRow)
	public ArrayList<OrdersDto> OrdersListUsers(String mId, int startRow, int endRow){
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM ORDERS WHERE mID=? ORDER BY oDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int oId = rs.getInt("oId");
				Date oDate = rs.getDate("oDate");
				String oName = rs.getString("oName");
				String oPhone = rs.getString("oPhone");
				String oAddress = rs.getString("oAddress");
				String oPayment = rs.getString("oPayment");
				int oInvoice = rs.getInt("oInvoice");
				dtos.add(new OrdersDto(oId, oDate, mId, oName, oPhone, oAddress, oPayment, oInvoice));
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
	// [관리자용] 전체 오더 개수
	public int ordersTotCntAdmin() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) TOTCNT FROM ORDERS";
		
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
	// [사용자용] 전체 오더 개수
	public int ordersTotCntUser(String mId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) TOTCNT FROM ORDERS WHERE mID=?";
		
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
	// [사용자용] 주문하기(신규 오더 넣기)
	public int ordersInsert(String mId, String oName, String oPhone, String oAddress, String oPayment) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO ORDERS (oID, oDate, mID, oNAME, oPHONE, oADDRESS, oPAYMENT)" + 
				"    VALUES (ORDERS_SEQ.NEXTVAL, SYSDATE, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, oName);
			pstmt.setString(3, oPhone);
			pstmt.setString(4, oAddress);
			pstmt.setString(5, oPayment);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"주문 생성 성공":"주문 생성 실패");
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
	// [관리자용] 오더 수정(송장 번호만 추가 가능, 고객번호로 조회)
	public int ordersModify(int oInvoice, int oId) {
		int result = SUCCESS;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE ORDERS" + 
				"    SET oINVOICE = ?" + 
				"    WHERE oID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oInvoice);
			pstmt.setInt(2, oId);			
			result = pstmt.executeUpdate();
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
	// [사용자용] 오더 취소 - 사용자용 (송장이 없는 오더만 취소 가능)
	public int ordersDelete(String mId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="DELETE ORDERS WHERE mID=? AND oINVOICE=NULL";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);		
			result = pstmt.executeUpdate();
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


	// oId 로 dto 가져오기
	public OrdersDto ordersDto(int oId) {
		OrdersDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT * FROM ORDERS WHERE oID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,oId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Date oDate = rs.getDate("oDate");
				String mId = rs.getString("mId");
				String oName = rs.getString("oName");
				String oPhone = rs.getString("oPhone");
				String oAddress = rs.getString("oAddress");
				String oPayment = rs.getString("oPayment");
				int oInvoice = rs.getInt("oInvoice");
				dto = new OrdersDto(oId, oDate, mId, oName, oPhone, oAddress, oPayment, oInvoice);
			}			
		} catch (SQLException e) {
			System.out.println("주문 dto가져오기 예외 : " +e.getMessage());
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
	
	// 지금 막 생성된 oId 가져오기
	public int getCurrOid() {
		int currOid = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT ORDERS_SEQ.CURRVAL oID FROM DUAL";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				currOid = rs.getInt("oId");
			}			
		} catch (SQLException e) {
			System.out.println("주문번호 가져오기 예외 : "+e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}				
		return currOid;
	}
}
