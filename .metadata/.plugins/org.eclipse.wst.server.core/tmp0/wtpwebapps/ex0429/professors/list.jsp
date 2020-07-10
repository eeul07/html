<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0429.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>교수목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<style>
		h1{text-align:center;}
		#tbl, #stbl, #ctbl{text-align:center; border-collapse:collapse;
							border:1px solid #0128da; margin:auto;}
		.title{background:#5c7afe; color:white;}
		.row:hover{background:#9aacfd; color:white; cursor:pointer;}
		#slist, #clist{text-align:center; display:none;}	
	</style>
</head>
<body>
	<div id="menu">
    	<a href="/ex0429/professors/list.jsp">교수관리</a>
      	<a href="/ex0429/students/list.jsp">학생관리</a>
     	 <a href="/ex0429/courses/list.jsp">강좌관리</a> 	
   	</div>
	<h1>[ PROFESSORS LIST ]</h1>
	<table id=tbl width=700 border=1>
		<tr class=title>
			<td>번호</td>
			<td>이름</td>
			<td>소속학과</td>
			<td>임용일</td>
			<td>직급</td>
			<td>급여</td>
		</tr>
		<%
			PDAO dao=new PDAO();
			ArrayList<PVO> list=dao.list();
			for(PVO vo:list){
				out.println("<tr class=row>");
				out.println("<td class=pcode>" + vo.getPcode() + "</td>");
				out.println("<td class=pname>" + vo.getPname() + "</td>");
				out.println("<td>" + vo.getDept() + "</td>");
				out.println("<td>" + vo.getHiredate() + "</td>");
				out.println("<td>" + vo.getTitle() + "</td>");
				out.println("<td>" + vo.getSalary() + "</td>");
				out.println("</tr>");
			}
		%>
	</table>
	<div id="slist">
		<h1>[ STUDENTS LIST ]</h1>
		<div>담당교수 : <b><span class="pname"></span></b></div>
		<br>
		<table id="stbl" width=700 border=1></table>
		<script id="stemp" type="text/x-handlebars-template">
			<tr class=title>
				<td>학번</td>
				<td>학생명</td>
				<td>학과</td>
				<td>학년</td>
				<td>생년월일</td>
			</tr>
			{{#each slist}}
				<tr class=row>
					<td>{{scode}}</td>
					<td>{{sname}}</td>
					<td>{{dept}}</td>
					<td>{{year}}</td>
					<td>{{birthday}}</td>
				</tr>
			{{/each}}
		</script>
	</div>
	<div id="clist">
		<h1>[ COURSES LIST ]</h1>
		<div>담당교수 : <b><span class="pname"></span></b></div>
		<br>
		<table id="ctbl" width=700 border=1></table>
		<script id="ctemp" type="text/x-handlebars-template">
			<tr class=title>
				<td>강좌번호</td>
				<td>강좌명</td>
				<td>강의시간수</td>
				<td>강의실</td>
				<td>최대수강인원수</td>
				<td>수강신청인원수</td>
			</tr>
			{{#each clist}}
				<tr class=row>
					<td>{{lcode}}</td>
					<td>{{lname}}</td>
					<td>{{hours}}</td>
					<td>{{room}}</td>
					<td>{{capacity}}</td>
					<td>{{persons}}</td>
				</tr>
			{{/each}}
		</script>
	</div>
</body>
<script>
	var pcode;
	
	$("#tbl").on("click", ".row", function(){
		pcode=$(this).find(".pcode").html();
		var pname=$(this).find(".pname").html();
		$("#slist").show();
		$("#clist").show();
		$("#slist .pname").html(pname);
		$("#clist .pname").html(pname);
		
		getList();
	});
		
	function getList(){
		$.ajax({
			type:"get",
			url:"json.jsp",
			dataType:"json",
			data:{"pcode":pcode},
			success:function(data){
				var temp=Handlebars.compile($("#stemp").html());
				$("#stbl").html(temp(data));
				
				var temp=Handlebars.compile($("#ctemp").html());
				$("#ctbl").html(temp(data));
			}
		});
	}
</script>
</html>