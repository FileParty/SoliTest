package com.petmily.admin.service;

import static com.petmily.admin.common.JDBCTemplate.close;
import static com.petmily.admin.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.petmily.admin.model.dao.AdminDao;
import com.petmily.user.model.vo.AdminUser;

public class AdminService {
	
	AdminDao dao = new AdminDao();
	
	public ArrayList<AdminUser> userList(int cPage, int numPerPage, String type) {
		
		Connection conn = getConnection();
		
		ArrayList<AdminUser> list = dao.userList(conn, cPage, numPerPage, type);
		close(conn);
		return list;
	}

	public int boardCount() {
		Connection conn = getConnection();
		int count = dao.userCount(conn);
		close(conn);
		return count;
	}

}
