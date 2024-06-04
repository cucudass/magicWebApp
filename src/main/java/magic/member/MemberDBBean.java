package magic.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDBBean {
	//jsp 소스에서 MemberDB빈 객체 생성을 위한 참조 변수
	private static MemberDBBean instance = new MemberDBBean();
	
	public static MemberDBBean getInstance() {
		return instance;
	}
	
	//쿼리작업에 사용할 커넥션 객체를 리턴하는 메소드(dbcp 기법)
	public Connection getConnection() throws Exception {
		return ((DataSource) (new InitialContext().lookup("java:comp/env/jdbc/mysql"))).getConnection();
	}
	
	/**
	 * 휘원가입
	 * */
	public int insertMember(MemberBean mb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = -1;

		String sql = " insert into membert value (?,?,?,?,?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getMem_uid());
			pstmt.setString(2, mb.getMem_pwd());
			pstmt.setString(3, mb.getMem_name());
			pstmt.setString(4, mb.getMem_email());
			pstmt.setString(5, mb.getMem_addr());
			
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
	 * ID 중복 확인
	 * */
	public int confirmID(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int re = -1;
		//매개변수로 받은 id를 ? 인 쿼리 파리마터에 매핑
		String sql = " select mem_uid, mem_pwd " +
					 " from membert " +
					 " where mem_uid = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				re = 1;
			else 
				re = -1;
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
	 * 유저 비번 확인
	 * return 비번 일치 1 불일치 0
	 * */
	public int userCheck(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String db_mem_pwd = "";
		int re = -1; //초기값 -1 비번 일치 1 불일치 0

		String sql = " select mem_pwd " +
					 " from membert " +
					 " where mem_uid = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				db_mem_pwd = rs.getString("mem_pwd");
				if(db_mem_pwd.equals(pwd)) { //패스워드 일치
					re = 1;
				} else { // 불일치
					re = 0;
				}
			} else {
				re = -1;
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
	 * 유저 정보 select 
	 * */
	public MemberBean getMember(String id) {
		MemberBean member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select mem_uid, mem_pwd, mem_name, mem_email, mem_addr " + 
					 " from membert " + 
					 " where mem_uid = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberBean();
				member.setMem_uid(id);
				member.setMem_pwd(rs.getString("mem_pwd"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_addr(rs.getString("mem_addr"));
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
		return member;
	}
	
	public int updateMember(MemberBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int re = 0;
		String sql = " update membert set mem_pwd = ?, mem_email = ?, mem_addr = ? " + 
					 " where mem_uid = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMem_pwd());
			pstmt.setString(2, member.getMem_email());
			pstmt.setString(3, member.getMem_addr());
			pstmt.setString(4, member.getMem_uid());
			
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
	
}
