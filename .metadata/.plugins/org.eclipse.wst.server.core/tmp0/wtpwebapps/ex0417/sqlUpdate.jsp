<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0417.*" %>
<%
	String strgno=request.getParameter("gno");
	int gno=Integer.parseInt(strgno);
	String gname=request.getParameter("gname");
	String strkor=request.getParameter("kor");
	int kor=Integer.parseInt(strkor);
	String streng=request.getParameter("eng");
	int eng=Integer.parseInt(streng);
	String strmat=request.getParameter("mat");
	int mat=Integer.parseInt(strmat);
	
	GradeVO vo=new GradeVO();
	vo.setGno(gno);
	vo.setGname(gname);
	vo.setKor(kor);
	vo.setEng(eng);
	vo.setMat(mat);
	
	GradeDAO dao=new GradeDAO();
	dao.update(vo);
%>
<script>
	alert("수정되었습니다");
	location.href="list.jsp";
</script>