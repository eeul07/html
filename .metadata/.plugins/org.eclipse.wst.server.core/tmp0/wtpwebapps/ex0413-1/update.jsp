<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String strAno=request.getParameter("ano");
	int ano=Integer.parseInt(strAno);
	String aname=request.getParameter("aname");
	String tel=request.getParameter("tel");
	String address=request.getParameter("address");
	
	AddVO vo=new AddVO();
	vo.setAno(ano);
	vo.setAname(aname);
	vo.setTel(tel);
	vo.setAddress(address);
	
	AddDAO dao=new AddDAO();
	dao.update(vo);
%>
<script>
	alert("수정되었습니다");
	location.href="list.jsp";
</script>