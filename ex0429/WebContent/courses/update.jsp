<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0429.*"%>
<%
   	String scode=request.getParameter("scode");
	String lcode=request.getParameter("lcode");   
	String strGrade=request.getParameter("grade");
   	int grade=Integer.parseInt(strGrade);

   	EDAO dao=new EDAO();
   	dao.update(grade, scode, lcode);
%>