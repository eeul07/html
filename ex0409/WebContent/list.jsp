<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>SEUL's BLOG</title>
	<link rel="stylesheet" href="home.css"/>
</head>
<body>
	<div id="page">
		<div id="header">
			<h1>SEUL's BLOG</h1>
		</div>
		<div id="menu">
			<div class="item"><a href="insert.jsp">Writing</a></div>
		</div>
		<div id="content">
		<%
			BlogDAO dao=new BlogDAO();
			ArrayList<BlogVO> list=dao.list();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd   hh:mm:dd");
			for(int i=0; i<list.size(); i++){
				BlogVO vo=list.get(i);
				String strDate=sdf.format(vo.getWdate());
				out.println("<div class='box'>");
				out.println("<h1 class='title'>" +vo.getBno() + ")" + vo.getTitle() + "</h1>");
				out.println("<h3 class='date'>" + strDate + "</h3>");
				out.println("<p class='article'>" + vo.getArticle() + "</p>");
				out.println("<a href='sqlDelete.jsp?bno=" + vo.getBno() + "'>삭제</a>");
				out.println("</div>");
			}
		%>
		</div>
		<div id="footer">
			<h3>https://blog.naver.com/yeseul990712</h3>
		</div>	
	</div>
</body>
</html>