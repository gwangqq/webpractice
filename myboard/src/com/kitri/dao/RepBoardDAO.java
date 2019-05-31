package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;

public class RepBoardDAO {

	public void insert(RepBoard repBoard) throws AddException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String insertSQL = "insert into repboard("
				+ "BOARD_SEQ,PARENT_SEQ,BOARD_SUBJECT,BOARD_WRITER, BOARD_CONTENTS,BOARD_DATE, BOARD_PASSWORD, BOARD_VIEWCOUNT)"
				+ "values(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, systimestamp, ?, 0)";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			pstmt = conn.prepareStatement(insertSQL);
			pstmt.setInt(1, repBoard.getParent_seq());
			pstmt.setString(2, repBoard.getBoard_subject());
			pstmt.setString(3, repBoard.getBoard_writer());
			pstmt.setString(4, repBoard.getBoard_contents());
			pstmt.setString(5, repBoard.getBoard_password());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	public List<RepBoard> selectByRows(int startRow, int endRow) {
		List<RepBoard> list = new ArrayList<>();
		String selectByRowsSQL = "select *\r\n" + 
				"from (select rownum r ,repboard.*\r\n" + 
				"        from repboard\r\n" + 
				"        start with parent_seq=0\r\n" + 
				"        connect by prior board_seq = parent_seq\r\n" + 
				"        order siblings by board_seq DESC)\r\n" + 
				"where r between ? and ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			pstmt = conn.prepareStatement(selectByRowsSQL);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//검색 결과를  RepBoard 객체에 대입
				RepBoard repBoard = new RepBoard();
				
				repBoard.setBoard_seq(rs.getInt("board_seq"));
				repBoard.setParent_seq(rs.getInt("parent_seq"));
				repBoard.setBoard_subject(rs.getString("board_subject"));
				repBoard.setBoard_writer(rs.getString("board_writer"));
				repBoard.setBoard_contents(rs.getString("board_contents"));
				repBoard.setBoard_date(rs.getTimestamp("board_date"));
				repBoard.setBoard_password(rs.getString("board_password"));
				repBoard.setBoard_viewcount(rs.getInt("board_viewcount"));
				list.add(repBoard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		RepBoardDAO dao = new RepBoardDAO();
//		RepBoard repBoard = new RepBoard();
//		repBoard.setBoard_subject("테스트2");
//		repBoard.setBoard_writer("test2");
//		repBoard.setBoard_contents("테스트2 내용입니다");
//		repBoard.setBoard_password("testp2");
////		repBoard.setParent_seq(1);
//
//		try {
//			dao.insert(repBoard);
//
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
		for(RepBoard repBoard : dao.selectByRows(10, 20)) {
			System.out.println(repBoard);
		}
	}

	public int selectTotalCnt() {
		String selectTotalCntSQL = "SELECT count(*) FROM repboard";
		int totalCnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			pstmt = conn.prepareStatement(selectTotalCntSQL);
			rs = pstmt.executeQuery();
			rs.next();
			totalCnt =rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return totalCnt;
	}

}
