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

	public ArrayList<AdminUser> userList(Connection conn, int cPage, int numPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AdminUser> list = new ArrayList<AdminUser>();
		String sql = prop.getProperty("userList");
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

	public ArrayList<AdminUser> reviewCount(Connection conn, ArrayList<AdminUser> list) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("rvCount");
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++) {
				pstmt.setString(1, list.get(i).getUserId());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					list.get(i).setStarCount(rs.getInt(1));
				}
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<AdminUser> blindCount(Connection conn, ArrayList<AdminUser> list) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("bCount");
		try {
			for(int i=0;i<list.size();i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, list.get(i).getUserId());
				rs = pstmt.executeQuery();
				rs.next();
				list.get(i).setBlindCount(rs.getInt(1));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
