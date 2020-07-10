<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*"%>
<%
	String strSno=request.getParameter("sno");
	int sno=Integer.parseInt(strSno);
	String strPrice=request.getParameter("price");
	int price=Integer.parseInt(strPrice);
   
	BookVO vo=new BookVO();
	vo.setSno(sno);
	vo.setPrice(price);
	BookDAO dao=new BookDAO();
	dao.update(vo);
%>