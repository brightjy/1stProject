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

import com.jyp.shopping.dto.QnaDto;

public class QnaDao {
	
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static QnaDao instance = new QnaDao();
	private QnaDao() {
		
	}
	public static QnaDao getInstance() {
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
	
	// 글 목록(startRow, endRow)
	public ArrayList<QnaDto> qnaList(int startRow, int endRow){
		ArrayList<QnaDto> dtos = new ArrayList<QnaDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM(SELECT ROWNUM RN, A.* FROM (SELECT*FROM QNA_BOARD ORDER BY qGROUP DESC, qSTEP)A) WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int qId = rs.getInt("qId");
				String qCategory = rs.getString("qCategory");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String qPw = rs.getString("qPw");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFile1 = rs.getString("qFile1");
				String qFile2 = rs.getString("qFile2");
				Date qDate = rs.getDate("qDate");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				dtos.add(new QnaDto(qId, qCategory, mId, aId, qPw, qTitle, qContent, qFile1, qFile2, qDate, qHit, qGroup, qStep, qIndent, qIp));
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
	// 내가 쓴 글 목록
	public ArrayList<QnaDto> qnaListMember(String mId, int startRow, int endRow){
		ArrayList<QnaDto> dtos = new ArrayList<QnaDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="SELECT*FROM(SELECT ROWNUM RN, A.* FROM (SELECT*FROM QNA_BOARD ORDER BY qGROUP DESC, qSTEP)A) WHERE MID=? AND RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int qId = rs.getInt("qId");
				String qCategory = rs.getString("qCategory");
				String aId = rs.getString("aId");
				String qPw = rs.getString("qPw");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFile1 = rs.getString("qFile1");
				String qFile2 = rs.getString("qFile2");
				Date qDate = rs.getDate("qDate");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				dtos.add(new QnaDto(qId, qCategory, mId, aId, qPw, qTitle, qContent, qFile1, qFile2, qDate, qHit, qGroup, qStep, qIndent, qIp));
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
	// 전체 글 개수
	public int qnaTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM QNA_BOARD";
		
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
	// 내가 쓴 글 전체 글 개수
	public int qnaTotCntMember(String mId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM QNA_BOARD WHERE MID=?";
		
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
	// 원글 쓰기
	public int qnaWrite(QnaDto dto) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA_BOARD (qID, qCATEGORY, mID, aID, qPW, qTITLE, qCONTENT, qFILE1, qFILE2, qGROUP, qSTEP, qINDENT, qIP)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, QNA_SEQ.CURRVAL, 0, 0, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getqCategory());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getaId());
			pstmt.setString(4, dto.getqPw());
			pstmt.setString(5, dto.getqTitle());
			pstmt.setString(6, dto.getqContent());
			pstmt.setString(7, dto.getqFile1());
			pstmt.setString(8, dto.getqFile2());
			pstmt.setString(9, dto.getqIp());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"글 쓰기 성공":"글 쓰기 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("글 쓰기 실패"+dto.toString());
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
	// qId 글 조회수 1 올리기
	public void qnaHitup(int qId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD" + 
				"    SET qHIT = qHIT+1" + 
				"    WHERE qID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	// 상세글 보기(조회수 올라감)
	public QnaDto qnaContent(int qId) {
		qnaHitup(qId);
		QnaDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM QNA_BOARD WHERE qID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String qCategory = rs.getString("qCategory");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String qPw = rs.getString("qPw");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFile1 = rs.getString("qFile1");
				String qFile2 = rs.getString("qFile2");
				Date qDate = rs.getDate("qDate");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				dto = new QnaDto(qId, qCategory, mId, aId, qPw, qTitle, qContent, qFile1, qFile2, qDate, qHit, qGroup, qStep, qIndent, qIp);
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
	// 그저 qId로 Dto 가져오기
	public QnaDto qnaDto(int qId) {
		QnaDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM QNA_BOARD WHERE qID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String qCategory = rs.getString("qCategory");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String qPw = rs.getString("qPw");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFile1 = rs.getString("qFile1");
				String qFile2 = rs.getString("qFile2");
				Date qDate = rs.getDate("qDate");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				dto = new QnaDto(qId, qCategory, mId, aId, qPw, qTitle, qContent, qFile1, qFile2, qDate, qHit, qGroup, qStep, qIndent, qIp);
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
	
	// 답변 글 쓰기 전 stepA
	public void qnaReplyStepA(int qGroup, int qStep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD SET QSTEP=QSTEP+1 WHERE QGROUP=? AND QSTEP>?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qGroup);
			pstmt.setInt(2, qStep);
			pstmt.executeUpdate();
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
	}
	// 답변 글 쓰기
	public int qnaReply(QnaDto dto) {
		qnaReplyStepA(dto.getqGroup(), dto.getqStep());
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA_BOARD (qID, qCATEGORY, mID, aID, qPW, qTITLE, qCONTENT, qFILE1, qFILE2, qGROUP, qSTEP, qINDENT, qIP)" + 
				"    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getqCategory());
			pstmt.setString(2, dto.getmId());
			pstmt.setString(3, dto.getaId());
			pstmt.setString(4, dto.getqPw());
			pstmt.setString(5, dto.getqTitle());
			pstmt.setString(6, dto.getqContent());
			pstmt.setString(7, dto.getqFile1());
			pstmt.setString(8, dto.getqFile2());
			pstmt.setInt(9, dto.getqGroup());
			pstmt.setInt(10, dto.getqStep()+1);
			pstmt.setInt(11, dto.getqIndent()+1);
			pstmt.setString(12, dto.getqIp());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"답변 글 쓰기 성공":"답변 글 쓰기 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("답변 글 쓰기 실패"+dto.toString());
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
	// 글 수정하기
	public int qnaModify(String qTitle, String qContent, String qFile1, String qFile2, String qIp, int qId, String qPw) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD" + 
				"    SET qTITLE = ?," + 
				"        qCONTENT = ?," + 
				"        qFILE1 = ?," + 
				"        qFILE2 = ?," + 
				"        qIP = ?" + 
				"    WHERE qID=? AND qPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qTitle);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qFile1);
			pstmt.setString(4, qFile2);
			pstmt.setString(5, qIp);
			pstmt.setInt(6, qId);
			pstmt.setString(7, qPw);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"글 수정 성공":"글 수정 실패");
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
	// 글 삭제하기
	public int qnaDelete(int qId, String qPw) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE QNA_BOARD WHERE qID=? AND qPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			pstmt.setString(2, qPw);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"글 삭제 성공":"글 삭제 실패");
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
