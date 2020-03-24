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

import com.jyp.shopping.dto.MemberDto;

public class MemberDao {

	public static final int EXIST = 0; // 중복된 아이디
	public static final int NONEXIST = 1; // 사용 가능한 아이디
	public static final int SUCCESS = 1; // 회원가입 성공, 정보수정 성공, 로그인 성공, 회원탈퇴 성공
	public static final int FAIL = 0; // 회원가입 실패, 정보수정 실패, 로그인 실패, 회원탈퇴 실패
	public static final int NONEXISTMEMBER = 0; // 없는 회원
	public static final int EXISTMEMBER = 1; // 있는 회원 
	
	private static MemberDao instance = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}		
		return conn;
	}
	
	// 아이디 중복 체크 
	public int mIdChk(String mId) {
		int result = EXIST;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM MEMBER WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXIST;
			}else {
				result = NONEXIST;
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
	// 회원가입(mID,mPW,mNAME,mPHONE,mADDRESS/mBIRTH,mDROP//mDATE)
	public int JoinMember(MemberDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="INSERT INTO MEMBER(mID,mPW,mNAME,mEMAIL,mPHONE,mADDRESS,mBIRTH)" + 
				"    VALUES (?,?,?,?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setString(4, dto.getmEmail());
			pstmt.setString(5, dto.getmPhone());
			pstmt.setString(6, dto.getmAddress());
			pstmt.setDate(7, dto.getmBirth());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"회원가입 성공":"회원가입 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("회원가입 실패"+dto.toString());
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
	// 로그인(mId, mPw)
	public int loginMember(String mId, String mPw) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM MEMBER WHERE MDROP=0 AND mID=? AND mPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,mId);
			pstmt.setString(2,mPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=SUCCESS;
				System.out.println("로그인 성공");
			}else {
				result=FAIL;
				System.out.println("로그인 실패. 아이디와 비밀번호 오류");
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
	// 로그인 후 세션에 정보를 넣기 위해 mId로 dto 가져오기
	public MemberDto getMemberDto(String mId) {
		MemberDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM MEMBER WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mPw = rs.getString("mPw");
				String mName = rs.getString("mName");
				String mEmail = rs.getString("mEmail");
				String mPhone = rs.getString("mPhone");
				String mAddress = rs.getString("mAddress");
				Date mBirth = rs.getDate("mBirth");
				Date mDate = rs.getDate("mDate");
				int mDrop = rs.getInt("mDrop");				
				dto = new MemberDto(mId, mPw, mName, mEmail, mPhone, mAddress, mBirth, mDate, mDrop);
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
	// 회원 정보 수정(mId)
	public int modifyMember(MemberDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE MEMBER" + 
				"    SET mPW=?," + 
				"        mNAME=?," + 
				"        mEMAIL=?," + 
				"        mPHONE=?," + 
				"        mADDRESS=?," + 
				"        mBIRTH=?" + 
				"    WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw());
			pstmt.setString(2, dto.getmName());
			pstmt.setString(3, dto.getmEmail());
			pstmt.setString(4, dto.getmPhone());
			pstmt.setString(5, dto.getmAddress());
			pstmt.setDate(6, dto.getmBirth());
			pstmt.setString(7, dto.getmId());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"정보 수정 성공":"정보 수정 실패");
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
	// 회원 탈퇴
	public int deleteMember(String mId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER" + 
				"    SET mDROP=1" + 
				"    WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"회원탈퇴 성공":"회원탈퇴 실패");
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
	// 리스트 조회(startRow, endRow)
	public ArrayList<MemberDto> listMember(int startRow, int endRow){
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM (SELECT ROWNUM RN, A.* FROM (SELECT*FROM MEMBER ORDER BY mDATE DESC)A) WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String mId = rs.getString("mId");
				String mPw = rs.getString("mPw");
				String mName = rs.getString("mName");
				String mEmail = rs.getString("mEmail");
				String mPhone = rs.getString("mPhone");
				String mAddress = rs.getString("mAddress");
				Date mBirth = rs.getDate("mBirth");
				Date mDate = rs.getDate("mDate");
				int mDrop = rs.getInt("mDrop");
				dtos.add(new MemberDto(mId, mPw, mName, mEmail, mPhone, mAddress, mBirth, mDate, mDrop));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return dtos;
	}
	// 가입한 전체 회원수 
	public int getTotCntMember() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM MEMBER";
		
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
	
	//임시 비밀번호 발급 가능 조건 체크 -> mId, mName, mEmail 이 조건에 맞는 회원 있는지 가져오기
	public int searchMember(String mId, String mName, String mEmail) {
		int result = NONEXISTMEMBER;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM MEMBER WHERE mID=? AND mNAME=? AND mEMAIL=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, mName);
			pstmt.setString(3, mEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("있지ㅋㅋㅋ");
				result = EXISTMEMBER;
			}else {
				System.out.println("없어"+mId+"/"+mName+"/"+mEmail);
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
	
	// 임시 비밀번호로 비밀번호 수정
	public int modifyTempPw(String mPw, String mId, String mName, String mEmail ) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE MEMBER" + 
				"    SET mPW=?" + 
				"    WHERE mID=? AND mName=? AND mEmail=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mPw);
			pstmt.setString(2, mId);
			pstmt.setString(3, mName);
			pstmt.setString(4, mEmail);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"비밀번호 수정 성공":"비밀번호 수정 실패");
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
	
	// 회원 로그인 시도 시 탈퇴한 회원 여부 확인하기
	public int memberDropChk(String mId) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT mDROP FROM MEMBER WHERE mID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("mdrop");
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
}
