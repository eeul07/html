<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0506.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>점수통계</title>
</head>
<body>
	<h1>[ 강좌별 평균점수 ]</h1>
	<table width=700 border=1>
		<tr>
			<td>강좌번호</td>
			<td>강좌명</td>
			<td>담당교수</td>
			<td>강의실</td>
			<td>평균점수</td>
			<td>수강인원</td>
		</tr>
		<%
		EDAO dao=new EDAO();
		ArrayList<CVO> list=dao.clist();
		for(CVO vo:list){
			out.println("<tr>");
			out.println("<td>" + vo.getLcode() + "</td>");
			out.println("<td>" + vo.getLname() + "</td>");
			out.println("<td>" + vo.getPname() + "</td>");
			out.println("<td>" + vo.getRoom() + "</td>");
			DecimalFormat df=new DecimalFormat("#0.00");
			String strAvg=df.format(vo.getAvg());
			out.println("<td>" + strAvg + "</td>");
			out.println("<td>" + vo.getCnt() + "</td>");
			out.println("</tr>");
		}
		%>
	</table>
	<h1>[ 학생별 평균점수 ]</h1>
	<table width=700 border=1>
		<tr>
			<td>학번</td>
			<td>학생명</td>
			<td>학생학과</td>
			<td>평균점수</td>
			<td>수강과목수</td>
		</tr>
		<%
		EDAO sdao=new EDAO();
		ArrayList<SVO> slist=sdao.slist();
		for(SVO vo:slist){
			out.println("<tr>");
			out.println("<td>" + vo.getScode() + "</td>");
			out.println("<td>" + vo.getSname() + "</td>");
			out.println("<td>" + vo.getDept() + "</td>");
			DecimalFormat df=new DecimalFormat("#0.00");
			String strAvg=df.format(vo.getAvg());
			out.println("<td>" + strAvg + "</td>");
			out.println("<td>" + vo.getCnt() + "</td>");
			out.println("</tr>");
		}
		%>
	</table>
</body>
</html>