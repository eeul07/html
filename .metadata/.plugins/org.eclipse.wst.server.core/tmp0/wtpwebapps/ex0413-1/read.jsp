<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String strAno=request.getParameter("ano");
	int ano=Integer.parseInt(strAno);
	AddDAO dao=new AddDAO();
	AddVO vo=dao.read(ano);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>주소읽기</title>
	<link rel="stylesheet" href="home.css"/>
</head>
<body>
	<div id="page">
		<div id="content">
			<h1>주소읽기</h1>
			<hr>
			</div>
			<form class="frm" action="update.jsp">
				<input type="hidden" name="ano" value="<%=ano%>">
				<table class="tblInsert">
					<tr>
						<td>이름</td>
						<td><input type="text" name="aname" value="<%=vo.getAname()%>"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="tel" value="<%=vo.getTel()%>"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><textarea rows="5" cols="50" name="address"><%=vo.getAddress()%></textarea></td>
					</tr>
					<tr>
						<td colspan=2 style="text-align:center;">
							<input type="submit" value="수정">
								<input type="reset" value="취소">
								<input type="button" value="삭제" onClick="location.href='delete.jsp?ano=<%=ano%>'">
								<input type="button" value="목록" onClick="location.href='list.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>