<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0423.*" %>
<%
	String sid=request.getParameter("sid");
	
	ShopDAO dao=new ShopDAO();
	dao.delete(sid);
%>