package tmall.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import tmall.bean.User;
import tmall.util.DBUtil;

public class UserDAO {
	
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement();){
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
		String sql = "insert into User values(null,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);){
			
			ps.setString(1, bean.getName());
			ps.setString(2,  bean.getPassword());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(User bean) {
		
		String sql = "update user set name = ? , password = ? where id = ? ";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);) {
  
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setInt(3, bean.getId());
  
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
		
	}
	
	public void delete(int id) {
		
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement();) {
			  
            String sql = "delete from User where id = " + id;
  
            s.execute(sql);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
	}
	
	public User get(int id) {
		User bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement();) {
			
			String sql = "select from User where id = " + id;
			
			ResultSet rs = (ResultSet) s.executeQuery(sql);
			
			while(rs.next()) {
				bean = new User();
				bean.setId(id);
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();
  
        String sql = "select * from User order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = (ResultSet) ps.executeQuery();
  
            while (rs.next()) {
                User bean = new User();
                int id = rs.getInt(1);
 
                String name = rs.getString("name");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                 
                bean.setId(id);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
	
    public boolean isExist(String name) {	
        User user = get(name);
        return user != null;
    }
    
    public User get(String name) {
        User bean = null;
          
        String sql = "select * from User where name = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = (ResultSet) ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                String password = rs.getString("password");
                bean.setPassword(password);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
    
    public User get(String name, String password) {
        User bean = null;
          
        String sql = "select * from User where name = ? and password=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = (ResultSet) ps.executeQuery();
  
            if (rs.next()) {
                bean = new User();
                int id = rs.getInt("id");
                bean.setName(name);
                bean.setPassword(password);
                bean.setId(id);
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return bean;
    }
}
