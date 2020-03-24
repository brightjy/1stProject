package com.jyp.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.jyp.shopping.dto.AdminDto;

public class AdminDao {
	
	public static final int SUCCESS = 1; // 로그인 성공, 관리자 등록 성공
	public static final int FAIL = 0; // 로그인 실패, 관리자 등록 실패
	
	private static AdminDao instance = new AdminDao();
	private AdminDao() {}
	public static AdminDao getInstance() {
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
	
	// 관리자 등록
	public int joinAdmin(AdminDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ADMIN (aID, aPW) VALUES (?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,dto.getaId());
			pstmt.setString(2, dto.getaPw());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"관리자 등록 성공":"관리자 등록 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("관리자 등록 실패"+dto.toString());
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
	// 관리자 로그인(aId, aPw)
	public int loginAdmin(String aId, String aPw) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM ADMIN WHERE aID=? AND aPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = SUCCESS;
				System.out.println("관리자 로그인 성공");
			}else {
				result = FAIL;
				System.out.println("관리자 로그인 실패");
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
		return result;
	}
	// 관리자 아이디(aId)로 dto 불러오기(세션에 담아둘 용)
	public AdminDto getAdminDto(String aId) {
		AdminDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM ADMIN WHERE aID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String aPw = rs.getString("aPw");
				String aName = rs.getString("aName");
				dto = new AdminDto(aId, aPw, aName);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return dto;
	}
	// 관리자 리스트 조회(페이징X)
	public ArrayList<AdminDto> listAdmin(){
		ArrayList<AdminDto> dtos = new ArrayList<AdminDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM ADMIN";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String aId = rs.getString("aId");
				String aPw = rs.getString("aPw");
				String aName = rs.getString("aName");
				dtos.add(new AdminDto(aId, aPw, aName));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return dtos;
	}
	
}
