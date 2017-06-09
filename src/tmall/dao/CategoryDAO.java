package tmall.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import tmall.bean.Category;
import tmall.util.DBUtil;

public class CategoryDAO {
	
	public int getTotal() {
		int total = 0;
		
//		这是JDK1.7的新特性，try-resource-catch，可以实现代码执行完之后圆括号内的资源自动关闭。
//		前提是那些资源得实现了Autocloseable接口。
//		关于此接口可以查阅JDK API，此处摘录一小段。
//		public interface AutoCloseable
//		An object that may hold resources (such as file or socket handles) until it is closed. 
//		The close() method of an AutoCloseable object is called automatically when exiting a try-with-resources 
//		block for which the object has been declared in the resource specification header. This construction ensures 
//		prompt release, avoiding resource exhaustion exceptions and errors that may otherwise occur.
		
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement();) {
			
			String sql = "select count(*) from Category";
			
			ResultSet rs = (ResultSet) s.executeQuery(sql);
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	public void add(Category bean) {
		
		String sql = "insert into category values(null, ?)";
		
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql)) {
			ps.setString(1, bean.getName());
			ps.execute();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Category bean) {
		String sql = "update category set name = ? where id = ?";
		
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql)) {
			
			ps.setString(1, bean.getName());
			ps.setInt(2, bean.getId());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement()) {
			
			String sql = "delete from Category where id = " + id;
			s.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Category get(int id) {
		
		Category bean = null;
		try (Connection c = DBUtil.getConnection(); Statement s = (Statement) c.createStatement()) {
			
			String sql = "select * from Category where id = " + id;
			ResultSet rs = (ResultSet) s.executeQuery(sql);
			
			while(rs.next()) {
				bean = new Category();
				bean.setName(rs.getString(2));
				bean.setId(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
		
	}
	
	public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }
	
	public List<Category> list(int start, int count) {
        List<Category> beans = new ArrayList<Category>();
  
        String sql = "select * from Category order by id desc limit ?,? ";
  
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = (ResultSet) ps.executeQuery();
  
            while (rs.next()) {
                Category bean = new Category();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                bean.setId(id);
                bean.setName(name);
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
	
}
