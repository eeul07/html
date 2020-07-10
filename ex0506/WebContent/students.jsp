<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0506.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<style>
	#enroll, #elist{display:none;}
	</style>
</head>
<body>
	<h1>[ 학생목록 ]</h1>
	<table id=tbl width=700 border=1>
		<tr>
			<td>학번</td>
			<td>학생명</td>
			<td>학과</td>
		</tr>
		<%
		SDAO dao=new SDAO();
		ArrayList<SVO> list=dao.list();
		for(SVO vo:list){
			out.println("<tr class=row>");
			out.println("<td class=scode>" + vo.getScode() + "</td>");
			out.println("<td>" + vo.getSname() + "</td>");
			out.println("<td>" + vo.getDept() + "</td>");
			out.println("</tr>");
		}
		%>
	</table>
	<br>
	<hr>
	<div id="enroll">
	<h1>[ 수강신청목록 ]</h1>
			수강과목 : <span id="cnt"></span>
			평균 : <span id="avg"></span>
	</div>
	<br>
	<div id="elist">
		<table id="tbl1" width=700 border=1></table>
		<script id="temp1" type="text/x-handlebars-template">
			<tr class=title>
				<td>강좌번호</td>
				<td>강좌명</td>
				<td>점수</td>
			</tr>
			{{#each elist}}
				<tr class=row>
					<td class=lcode>{{lcode}}</td>
					<td>{{lname}}</td>
					<td>{{grade}}</td>
				</tr>
			{{/each}}
		</script>
	</div>
</body>
<script>
	var scode;	
	getList();
	
	$("#tbl").on("click", ".row", function(){
		scode=$(this).find(".scode").html();
		$("#elist").show();
		$("#enroll").show();
		
		getList();
	});
	
	// 수강신청 목록 출력
	function getList(){
		$.ajax({
			type:"get",
			url:"sjson.jsp",
			data:{"scode":scode},
			dataType:"json",
			success:function(data){
				var temp=Handlebars.compile($("#temp1").html());
				$("#tbl1").html(temp(data));
				$("#cnt").html(data.cnt);
				$("#avg").html(data.avg);
				// $("#pcode").html(scode);
			}
		});
	}
</script>
</html>