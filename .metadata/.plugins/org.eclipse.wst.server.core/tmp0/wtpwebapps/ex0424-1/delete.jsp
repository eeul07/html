<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String strSno=request.getParameter("sno");
	int sno=Integer.parseInt(strSno);
	
	BookDAO dao=new BookDAO();
	dao.delete(sno);
%>