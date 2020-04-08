<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.petmily.user.model.vo.AdminUser" %>
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminSideBar.css" type = "text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPetSitterSupport.css" type = "text/css">
<% 
	ArrayList<AdminUser> list = new ArrayList();
	if(request.getAttribute("psSList")!=null){
		list = (ArrayList<AdminUser>)request.getAttribute("psSList");
	}
%>
<section id="adminMainMenu">
<%@ include file="/views/admin/adminSideBar.jsp" %>
	<section id="adminUserList">
		<h4>펫 시터 지원 목록</h4>
		<hr/>
		<div id="adminUserListSearchType">
			<p id="type-date">일자 순
				<img alt="2" 
						src="<%=request.getContextPath()%>/img/common/arrowUp.png" 
					width="20px" height="20px"></p>
			<p id="type-point">평점 순
				<img alt="2" 
						src="<%=request.getContextPath()%>/img/common/arrowUp.png" 
					 width="20px" height="20px"></p>
			<p id="type-name">이름 순
				<img alt="1" 
						src="<%=request.getContextPath()%>/img/common/arrowDown.png" 
					width="20px" height="20px"></p>
		</div>
		<div class="adminUserInfo">
			<table>
				<thead>
					<tr>
						<th>No</th>
						<th>선택</th>
						<th>아이디</th>
						<th>이름</th>
						<th>지원폼확인</th>
						<th>상태</th>
						<th>메일 회신</th>
						<th>지원 일자</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="pageBar">
				<%= request.getAttribute("pageBar") %>
			</div>
		</div>
	</section>
</section>
<script>
	$(function(){
		// 일자순 이벤트
		$("#type-date").click(e=>{
				location.replace('<%=request.getContextPath()%>/admin/user_list?type=BCOUNT%20DESC');
		})
		// 비밀후기 평점순 이벤트
		$("#type-point").click(e=>{
			location.replace('<%=request.getContextPath()%>/admin/user_list?type=STAR');
		})
		// 이름순 이벤트
		$("#type-name").click(e=>{
			location.replace('<%=request.getContextPath()%>/admin/user_list?type=USER_NAME');
		})
		
	})
</script>
<%@ include file="/views/common/footer.jsp" %>