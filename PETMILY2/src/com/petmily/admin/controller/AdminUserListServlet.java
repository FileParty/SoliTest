package com.petmily.admin.controller;

import static com.petmily.admin.common.PageBarTemplate.getPageBar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petmily.admin.service.AdminService;
import com.petmily.user.model.vo.AdminUser;

/**
 * Servlet implementation class AdminUserListServlet
 */
@WebServlet("/admin/user_list")
public class AdminUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cPage = 1;
		if(request.getParameter("cPage")!=null) {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}
		String type = "userBirth";
		if(request.getParameter("type")!=null) {
			type = request.getParameter("type");
		}
		int numPerPage = 10;
		
		ArrayList<AdminUser> list = new AdminService().userList(cPage,numPerPage);
		
		int totalDate = new AdminService().boardCount();
		String url = request.getContextPath() + "/admin/user_list";
		String pageBar = getPageBar(url,totalDate,cPage,numPerPage);
		
		for(AdminUser u : list) {
			System.out.println(u);
		}
		
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.size();j++) {
				switch(type) {
				case "userBirth" : break;
				case "StarCount" : break;
				case "BlindCount" : break;
				}
			}
		}
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("userList", list);
		request.getRequestDispatcher("/views/admin/adminUserService.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
