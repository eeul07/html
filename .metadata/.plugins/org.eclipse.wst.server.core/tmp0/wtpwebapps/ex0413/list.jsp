<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, ex01.*" %>
<%
	BBSDAO dao=new BBSDAO();
	ArrayList<BBSVO> array=dao.list();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Board List</title>
	<style>
		div{background:gray; color:white; font-size:30px;}
	</style>
</head>
<body>
	<h1>[Board List]</h1>
	<a href="insert.jsp">[Writing]</a>
	<%
		for(int i=0; i<array.size(); i++){
			BBSVO vo=array.get(i);
			out.println("<div>");
			out.println(vo.getBno());
			out.println("<a href='read.jsp?bno=" + vo.getBno() + "'>" +  vo.getTitle() + "</a>");
			out.println(vo.getWdate());
			out.println("</div>");
		}
	%>
</body>
</html>