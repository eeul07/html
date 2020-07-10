<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*" %>
<%
	request.setCharacterEncoding("UTF-8");
 	String pno=request.getParameter("pno");
 	String pname=request.getParameter("pname");
 	String strprice=request.getParameter("price");
 	int price=Integer.parseInt(strprice);
 	
 	ProductVO vo=new ProductVO();
 	vo.setPno(pno);
 	vo.setPname(pname);
 	vo.setPrice(price);
 	
 	ProductDAO dao=new ProductDAO();
 	dao.insert(vo);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>데이터저장</title>
</head>
<body>
	<h1>데이터가 입력 되었습니다.</h1>
	<a href="ex01.html">데이터입력</a>
</body>
</html>