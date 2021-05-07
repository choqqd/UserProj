package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public UserInfoVO updateUser(UserInfoVO vo) {
		conn = DBcon.getConnect();
		String sql = "update user_temp set user_phone =? where user_id = ?";
		String selectsql = "select * from user_temp where user_id = ?";
		UserInfoVO rvo = new UserInfoVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPhone());
			psmt.setString(2, vo.getId());
			int r = psmt.executeUpdate();
			System.out.println(r+"건 수정");
		
			//수정한 데이터 가져오기
			psmt = conn.prepareStatement(selectsql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				rvo.setPhone(rs.getString("user_phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return rvo;
	}
	
	public List<UserInfoVO> UserSelect(){
		conn = DBcon.getConnect();
		String selectsql = "select * from user_temp";
		List<UserInfoVO> list = new ArrayList<UserInfoVO>();
		
		try {
			psmt = conn.prepareStatement(selectsql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				UserInfoVO vo = new UserInfoVO();
				vo.setId(rs.getString("user_id"));
				vo.setName(rs.getString("user_name"));
				vo.setPhone(rs.getString("user_phone"));
				vo.setGender(rs.getString("user_gender"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	public UserInfoVO insertuser(UserInfoVO vo) {
		conn = DBcon.getConnect();
		
		String sql = "insert into user_temp values(?,?,?,?,?)";
		String selectsql = "select * from user_temp where user_id = ?";
		UserInfoVO rvo = new UserInfoVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getGender());
			
			int r = psmt.executeUpdate();
			System.out.println(r+"건입력");
			
			//입력한 데이터 가져오기
			psmt = conn.prepareStatement(selectsql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				rvo.setId(rs.getString("user_id"));
				rvo.setName(rs.getString("user_name"));
				rvo.setPhone(rs.getString("user_phone"));
				rvo.setGender(rs.getString("user_gender"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rvo;
	}
	
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
