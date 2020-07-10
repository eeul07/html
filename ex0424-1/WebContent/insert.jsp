<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	String title=request.getParameter("title");
	String author=request.getParameter("author");
	String strPrice=request.getParameter("price");
	int price=Integer.parseInt(strPrice);
	
	BookVO vo=new BookVO();
	vo.setTitle(title);
	vo.setAuthor(author);
	vo.setPrice(price);
	
	BookDAO dao=new BookDAO();
	dao.insert(vo);
%>