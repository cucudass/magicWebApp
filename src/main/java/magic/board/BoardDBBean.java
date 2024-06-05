package magic.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.exceptions.RSAException;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		return ((DataSource) (new InitialContext().lookup("java:comp/env/jdbc/mysql"))).getConnection();
	}
	
	/**
	 * 글쓰기
	 * */
	public int insertBoard(BoardBean board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1;

		String sql = " insert into boardt(b_name, b_email, b_title, b_content, b_pwd, b_date, b_ip) " +
					 " value (?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_name());
			pstmt.setString(2, board.getB_email());
			pstmt.setString(3, board.getB_title());
			pstmt.setString(4, board.getB_content());
			pstmt.setString(5, board.getB_pwd());
			pstmt.setTimestamp(6, board.getB_date());
			pstmt.setString(7, board.getB_ip());
			
			re = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return re;
	}
	
	/**
	 * 글 내용 전부 select 
	 * */
	public ArrayList<BoardBean> listBoard() {
		ArrayList<BoardBean> boardList = null;
		BoardBean board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select b_id, b_name, b_email, b_title, b_content, b_pwd, b_date, b_hit, b_ip " + 
					 " from boardt "; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<BoardBean>();
			while(rs.next()) {
				board = new BoardBean();
				
				board.setB_id(rs.getInt("b_id")); //board.setB_id(rs.getInt(1)); //-> 컬러명 대신 숫자로 가능(1부터 시작)
				board.setB_name(rs.getString("b_name"));
				board.setB_email(rs.getString("b_email"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_pwd(rs.getString("b_pwd"));
				board.setB_date(rs.getTimestamp("b_date"));
				board.setB_hit(rs.getInt("b_hit"));
				board.setB_ip(rs.getString("b_ip"));
				//ip까지의 1행을 가져와서 저장
				
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return boardList;
	}
	
	/**
	 *@hitadd -> true 조회수 + 1 후 테이블 조회
	 *@hitadd -> false 테이블 조회
	 * */
	public BoardBean getBoard(int bid, boolean hitadd) {
		BoardBean board = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			conn = getConnection();
			if(hitadd) {
				sql = " update boardt set b_hit = b_hit + 1 " +
					  " where b_id = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bid);
				pstmt.executeUpdate();
			}
			sql = " select b_id, b_name, b_email, b_title, b_content, " +
				  " 	   b_pwd, b_date, b_hit, b_ip " + 
				  " from boardt " + 
				  " where b_id = ? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardBean();
				
				board.setB_id(rs.getInt("b_id"));
				board.setB_name(rs.getString("b_name"));
				board.setB_email(rs.getString("b_email"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_pwd(rs.getString("b_pwd"));
				board.setB_date(rs.getTimestamp("b_date"));
				board.setB_hit(rs.getInt("b_hit"));
				board.setB_ip(rs.getString("b_ip"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	/**
	 * 비번 확인 후 해당 게시글 삭제
	 * */
	public int deleteBoard(int b_id, String b_pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String pwd = "";
		int re = -1;
			sql = " delete from boardt " + 
				  " where b_id = ? and b_pwd = ? "; 
		try {
			conn = getConnection();
			sql = " select b_pwd from boardt where b_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //비밀번호 있을 경우
				pwd = rs.getString("b_pwd");
				if(pwd.equals(b_pwd)) { //비밀번호 일치
					sql = " delete from boardt " + 
						  " where b_id = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, b_id);
					re = pstmt.executeUpdate();
				} else { //비밀번호 불일치
					re = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return re;
	}
		
	/**
	 * board 테이블 값 수정
	 * */
	public int editBoard(BoardBean board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int re = -1;
		String sql = "";
		String pwd = "";
		try {
			conn = getConnection();
			sql = " select b_pwd from boardt " +
				  " where b_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("b_pwd");
				if(pwd.equals(board.getB_pwd())) {
					sql = " update boardt set b_name = ?, b_email = ?, b_title = ?, b_content = ? " +
						  " where b_id = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getB_name());
					pstmt.setString(2, board.getB_email());
					pstmt.setString(3, board.getB_title());
					pstmt.setString(4, board.getB_content());
					//key
					pstmt.setInt(5, board.getB_id());
					re = pstmt.executeUpdate();
				} else {
					re = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return re;
	}
}
