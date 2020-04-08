<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.petmily.user.model.vo.AdminUser" %>
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminSideBar.css" type = "text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminUserList.css" type = "text/css">
<% 
	ArrayList<AdminUser> list = new ArrayList();
	if(request.getAttribute("userList")!=null){
		list = (ArrayList<AdminUser>)request.getAttribute("userList");
	}
	int userCount = 1;
%>
<section id="adminMainMenu">
	<aside id="sideBar">
		<div id="sideBar1" class="side">
			<h2>일반 회원</h2>
			<hr/>
			<a href="#">▷ 일반 회원 목록</a>
		</div>
		<div id="sideBar2" class="side">
			<h2>펫 시터</h2>
			<hr/>
			<a href="#">- 펫 시터 지원 목록</a>
			<a href="#">- 이전 펫 시터 지원 목록</a>
			<a href="#">- 펫 시터 목록</a>
		</div>
		<div id="sideBar3" class="side">
			<h2>게시글</h2>
			<hr/>
			<a href="#">- 후기 블라인드 요청 목록</a>
			<a href="#">- 블라인드 된 후기</a>
			<a href="#">- 블라인드 된 게시글</a>
		</div>
		<div id="sideBar4" class="side">
			<h2>고객 문의</h2>
			<hr/>
			<a href="#">- 고객 문의</a>
		</div>
		<div id="sideBar5" class="side">
			<h2>추가 요금</h2>
			<hr/>
			<a href="#">- 추가 요금 청구 목록</a>
			<a href="#">- 이전 추가 요금 청구 목록</a>
			<a href="#">- 추가 요금 관련 문의</a>
		</div>
	</aside>
	<section id="adminUserList">
		<h4>일반 회원 목록</h4>
		<hr/>
		<div id="adminUserListSearchType">
			<p id="type-date">등록 순
				<img alt="1" src="<%=request.getContextPath()%>/img/common/arrowDown.png" width="20px" height="20px"></p>
			<p id="type-point">평점 순
				<img alt="2" src="<%=request.getContextPath()%>/img/common/arrowUp.png" width="20px" height="20px"></p>
			<p id="type-name">이름 순
				<img alt="2" src="<%=request.getContextPath()%>/img/common/arrowUp.png" width="20px" height="20px"></p>
		</div>
		<div class="adminUserInfo">
			<table>
				<thead>
					<tr>
						<th>No</th>
						<th>아이디</th>
						<th>이름</th>
						<th>생일</th>
						<th>비밀 후기 평점</th>
						<th>후기 블라인드 요청건수</th>
						<th>상세 정보</th>
					</tr>
				</thead>
				<tbody>
					<% for(AdminUser u : list){ %>
					<tr>
						<td><%= userCount++ %></td>
						<td><%= u.getUserId() %></td>
						<td><%= u.getUserName() %></td>
						<td><%= u.getUserBirth() %></td>
						<td><%= u.getStarCount()==0?'-':u.getStarCount() %></td>
						<td><%= u.getBlindCount()==0?'-':u.getBlindCount() %></td>
						<td><button class="userInfo" onclick="location.replace('<%=request.getContextPath()%>/')">보기</button></td>
					</tr>
					<% } %>
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
		// 등록순 이벤트
		$("#type-date").click(e=>{
				$("#type-date>img").
				attr("src","<%=request.getContextPath()%>/img/common/arrowDown.png");
				$("#type-point>img").
				attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
				$("#type-name>img").
				attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
		})
		// 비밀후기 평점순 이벤트
		$("#type-point").click(e=>{
			$("#type-date>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
			$("#type-point>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowDown.png");
			$("#type-name>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
		})
		// 이름순 이벤트
		$("#type-name").click(e=>{
			$("#type-date>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
			$("#type-point>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowUp.png");
			$("#type-name>img").
			attr("src","<%=request.getContextPath()%>/img/common/arrowDown.png");
		})
	})
</script>
<%@ include file="/views/common/footer.jsp" %>