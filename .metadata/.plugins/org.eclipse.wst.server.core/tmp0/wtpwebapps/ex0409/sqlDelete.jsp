<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String strBno=request.getParameter("bno");
	int bno=Integer.parseInt(strBno);
	
	BlogDAO dao=new BlogDAO();
	dao.delete(bno);
%>
<script>
	alert("삭제되었습니다.")
	location.href="list.jsp";
</script>