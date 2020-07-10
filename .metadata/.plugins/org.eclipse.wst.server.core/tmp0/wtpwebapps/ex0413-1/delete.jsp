<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String strAno=request.getParameter("ano");
	int ano=Integer.parseInt(strAno);
	
	AddDAO dao=new AddDAO();
	dao.delete(ano);
%>
<script>
	alert("삭제되었습니다");
	location.href="list.jsp";
</script>