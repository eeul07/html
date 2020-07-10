<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, ex01.*" %>
<%
	AddDAO dao=new AddDAO();
	ArrayList<AddVO> array=dao.list();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>목록</title>
	<link rel="stylesheet" href="home.css"/>
</head>
<body>
	<div id="page">
		<div id="content">
			<h1>목록</h1>
			<hr>
			<div id="menu">
				<a href="insert.jsp">주소입력</a>
			</div>
			<table class="tbl">
				<tr class="title">
						<td>번호</td>
						<td>이름</td>
						<td>주소</td>
					</tr>
				<%
					for(int i=0; i<array.size(); i++){
						AddVO vo=array.get(i);
						out.println("<tr class='data'>");
						out.println("<td>" + vo.getAno() + "</td>");
						out.println("<td><a href='read.jsp?ano=" + vo.getAno() + "'>" + vo.getAname() + "</a></td>");
						out.println("<td>" + vo.getAddress() + "</td>");
						out.println("</tr>");
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>