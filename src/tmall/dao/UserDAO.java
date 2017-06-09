package tmall.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import tmall.bean.User;
import tmall.util.DBUtil;

public class UserDAO {
	
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement()){
			String sql = "select count(*) from User";
			
			ResultSet rs = (ResultSet) s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public void add(User bean) {
		
	}
	
	public void update(User bean) {
		
		
	}
	
	public void delete(int id) {
		
	}
	
	public User get(int id) {
		
	}
	
}
