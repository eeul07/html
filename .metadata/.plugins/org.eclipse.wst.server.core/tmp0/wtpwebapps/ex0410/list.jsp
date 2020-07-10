<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Board List</title>
	<link rel="stylesheet" href="home.css"/>
</head>
<body>
	<div id="page">
		<div id="content">
			<h3>Board List</h3>
			<hr>
			<div id="menu">
				<a href="insert.html">Writing</a>
			</div>
			<table class="tbl">
				<tr class="title">
					<td>No.</td>
					<td>Title</td>
					<td>Date</td>
				</tr>
				<%
					BoardDAO dao=new BoardDAO();
					ArrayList<BoardVO> list=dao.list();
					for(int i=0; i<list.size(); i++){
						BoardVO vo=new BoardVO();
						vo=list.get(i);
						int bno=vo.getBno();
						String title=vo.getTitle();
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String date=sdf.format(vo.getWdate());
						out.println("<tr class='data'>");
						out.println("<td>" + bno + "</td>");
						out.println("<td><a href='read.jsp?bno=" + bno + "'>" + title + "</a></td>");
						out.println("<td>" + date + "</td>");
						out.println("</tr>");
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>