package com.petmily.admin.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.petmily.user.model.vo.AdminUser;
import com.petmily.user.model.vo.User;

public class AdminDao {
	
	Properties prop = new Properties();
	
	public AdminDao() {
		
		String path = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		
		try {
			prop.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<AdminUser> userList(Connection conn, int cPage, int numPerPage, String type) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AdminUser> list = new ArrayList<AdminUser>();
		String sql = prop.getProperty("userList");
		sql = sql.replaceAll("ORTYPERO",type);
		try {
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AdminUser u = new AdminUser();
				u.setUserId(rs.getString("USER_ID"));
				u.setUserName(rs.getString("USER_NAME"));
				u.setUserBirth(rs.getString("USER_BIRTH_DAY"));
				u.setStarCount(rs.getDouble("STAR"));
				u.setBlindCount(rs.getInt("BCOUNT"));
				list.add(u);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int userCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		ArrayList<User> list = new ArrayList<User>();
		String sql = prop.getProperty("userCount");
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString(1));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}


}
