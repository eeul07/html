<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String title=request.getParameter("title");
	String article=request.getParameter("article");
	
	BlogVO vo=new BlogVO();
	vo.setTitle(title);
	vo.setArticle(article);
	
	BlogDAO dao=new BlogDAO();
	dao.insert(vo);
%>
<script>
	alert("저장되었습니다.")
	location.href="list.jsp";
</script>