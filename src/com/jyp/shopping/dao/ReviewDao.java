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

import com.jyp.shopping.dto.ReviewDto;

public class ReviewDao {

	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	
	private static ReviewDao instance = new ReviewDao();
	private ReviewDao() {
		
	}
	public static ReviewDao getInstance() {
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
	
	// 리뷰 목록
	public ArrayList<ReviewDto> reviewList(int startRow, int endRow){
		ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM(SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT R.*, pIMAGE1, pNAME  FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID ORDER BY rGROUP DESC, rSTEP)A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int rId = rs.getInt("rId");
				int oId = rs.getInt("oId");
				int pId = rs.getInt("pId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dtos.add(new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1));
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
	// 전체 리뷰 개수
	public int reviewTotCnt() {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM REVIEW_BOARD";
		
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
	// 상품 별 리뷰 개수
	public int reviewTotCntpId(int pId) {
		int totCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID AND P.PID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
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
	
	// 상품 별 리뷰 리스트 보기
	public ArrayList<ReviewDto> reviewListpId(int pId, int startRow, int endRow) {
		ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM(SELECT ROWNUM RN, A.* FROM" + 
				"    (SELECT R.*, pIMAGE1, pNAME  FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID ORDER BY rGROUP DESC, rSTEP)A)" + 
				"    WHERE pID=? AND RN BETWEEN ? AND ?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int rId = rs.getInt("rId");
				int oId = rs.getInt("oId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dtos.add(new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1));
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
	
	
	// 내가 쓴 리뷰 리스트 보기
		public ArrayList<ReviewDto> reviewListMid(String mId, int startRow, int endRow) {
			ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT*FROM(SELECT ROWNUM RN, A.* FROM" + 
					"    (SELECT R.*, pIMAGE1, pNAME  FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID ORDER BY rGROUP DESC, rSTEP)A)" + 
					"    WHERE MID=? AND RN BETWEEN ? AND ?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					int rId = rs.getInt("rId");
					int oId = rs.getInt("oId");
					int pId = rs.getInt("pId");
					String aId = rs.getString("aId");
					String rPw = rs.getString("rPw");
					String rTitle = rs.getString("rTitle");
					String rContent = rs.getString("rContent");
					String rFile1 = rs.getString("rFile1");
					String rFile2 = rs.getString("rFile2");
					Date rDate = rs.getDate("rDate");
					int rHit = rs.getInt("rHit");
					int rGroup = rs.getInt("rGroup");
					int rStep = rs.getInt("rStep");
					int rIndent = rs.getInt("rIndent");
					String rIp = rs.getString("rIp");
					String pName = rs.getString("pName");
					String pImage1 = rs.getString("pImage1");
					dtos.add(new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1));
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
		// 내가쓴 리뷰 개수 불러오기
		public int reviewMemberTotCnt(String mId) {
			int totCnt = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) TOTCNT FROM REVIEW_BOARD WHERE MID=?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId );
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
	public int reviewWrite(int oId, int pId, String mId, String aId, String rPw, String rTitle, String rContent, String rFile1, String rFile2, String rIp) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW_BOARD (rID, oID, pID, mID, aID, rPW, rTITLE, rCONTENT, rFILE1, rFILE2, rGROUP, rSTEP, rINDENT, rIP)" + 
				"    VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, REVIEW_SEQ.CURRVAL, 0, 0, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			pstmt.setInt(2, pId);
			pstmt.setString(3, mId);
			pstmt.setString(4, aId);
			pstmt.setString(5, rPw);
			pstmt.setString(6, rTitle);
			pstmt.setString(7, rContent);
			pstmt.setString(8, rFile1);
			pstmt.setString(9, rFile2);	
			pstmt.setString(10, rIp);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS?"글 쓰기 성공":"글 쓰기 실패");
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
	// 답글 쓰기 전 stepA
	public void reivewReplyStepA(int rGroup, int rStep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD SET rSTEP=rSTEP+1 WHERE rGROUP=? AND rSTEP>?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rGroup);
			pstmt.setInt(2, rStep);
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
	// 답글 쓰기
	public int reviewReply(ReviewDto dto) {
		reivewReplyStepA(dto.getrGroup(), dto.getrStep());
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REVIEW_BOARD (rID, oID, pID, mID, aID, rPW, rTITLE, rCONTENT, rFILE1, rFILE2, rGROUP, rSTEP, rINDENT, rIP)" + 
				"    VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getoId());
			pstmt.setInt(2, dto.getpId());
			pstmt.setString(3, dto.getmId());
			pstmt.setString(4, dto.getaId());
			pstmt.setString(5, dto.getrPw());
			pstmt.setString(6, dto.getrTitle());
			pstmt.setString(7, dto.getrContent());
			pstmt.setString(8, dto.getrFile1());
			pstmt.setString(9, dto.getrFile2());
			pstmt.setInt(10, dto.getrGroup());
			pstmt.setInt(11, dto.getrStep()+1);
			pstmt.setInt(12, dto.getrIndent()+1);
			pstmt.setString(13, dto.getrIp());
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
	// rId로 리뷰 불러서 조회수 1 올리기
	public void reviewHitup(int rId) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD" + 
				"    SET rHIT = rHIT+1" + 
				"    WHERE rID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
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
	// 상세보기(조회수 올라감)
	public ReviewDto reviewContent(int rId) {
		reviewHitup(rId);
		ReviewDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, pIMAGE1, pNAME  FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID AND rID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int oId = rs.getInt("oId");
				int pId = rs.getInt("pId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dto = new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1);
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
	// rId로 리뷰 DTO 불러오기(조회수 안올라감)
	public ReviewDto reviewDtoRid(int rId) {
		ReviewDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.*, pIMAGE1, pNAME  FROM REVIEW_BOARD R, PRODUCT P WHERE R.pID=P.pID AND rID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int oId = rs.getInt("oId");
				int pId = rs.getInt("pId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dto = new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1);
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
	// pId로 리뷰 불러오기(해당 상품 상세페이지에 해당 상품 리뷰 보여주기 용)
	public ReviewDto reviewDtoPid(int pId) {
		ReviewDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM REVIEW_BOARD WHERE pID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int rId = rs.getInt("rId");
				int oId = rs.getInt("oId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dto = new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1); 
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
	// oId로 리뷰 불러오기(고객이 오더한 상품 중 리뷰 작성할 상품이 있으면, 주문 상세페이지에서 보여줄 용)
	public ReviewDto reviewDtoOid(int oId) {
		ReviewDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT*FROM REVIEW_BOARD WHERE oID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int rId = rs.getInt("rId");
				int pId = rs.getInt("pId");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String rPw = rs.getString("rPw");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				String rFile1 = rs.getString("rFile1");
				String rFile2 = rs.getString("rFile2");
				Date rDate = rs.getDate("rDate");
				int rHit = rs.getInt("rHit");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				String pName = rs.getString("pName");
				String pImage1 = rs.getString("pImage1");
				dto = new ReviewDto(rId, oId, pId, mId, aId, rPw, rTitle, rContent, rFile1, rFile2, rDate, rHit, rGroup, rStep, rIndent, rIp, pName, pImage1); 
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
	
	
	// 글 수정
	public int reviewModify(String rTitle, String rContent, String rFile1, String rFile2, String rPw, String rIp, int rId) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REVIEW_BOARD" + 
				"    SET rTITLE = ?," + 
				"        rCONTENT = ?," + 
				"        rFILE1 =?," + 
				"        rFILE2 = ?," + 
				"        rIP = ?" + 
				"    WHERE rID=? AND rPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rTitle);
			pstmt.setString(2, rContent);
			pstmt.setString(3, rFile1);
			pstmt.setString(4, rFile2);
			pstmt.setString(5, rIp);
			pstmt.setInt(6, rId);
			pstmt.setString(7, rPw);
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
	// 글 삭제
	public int reviewDelete(int rId, String rPw) {
		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE REVIEW_BOARD WHERE rID=? AND rPW=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			pstmt.setString(2, rPw);
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
	
	// 리뷰 작성 여부 확인하기
	public int reviewExist(String mId, int oId, int pId){
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) C FROM REVIEW_BOARD WHERE MID=? AND OID=? AND PID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, oId);
			pstmt.setInt(3, pId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("C");
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
