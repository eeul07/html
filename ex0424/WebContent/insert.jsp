<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0424.*" %>
<%
	String strSno=request.getParameter("sno");
	int sno=Integer.parseInt(strSno);
	String thmn=request.getParameter("thmn");
	String title=request.getParameter("title");
	String authors=request.getParameter("authors");
	String strprice=request.getParameter("price");
	int price=Integer.parseInt(strprice);
	String status=request.getParameter("status");
	
	BookVO vo=new BookVO();
	vo.setSno(sno);
	vo.setThmn(thmn);
	vo.setTitle(title);
	vo.setAuthors(authors);
	vo.setPrice(price);
	vo.setStatus(status);
	
	BookDAO dao=new BookDAO();
	dao.insert(vo);
%>