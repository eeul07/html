<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0428.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<h1>[ STUDENTS LIST ]</h1>
	<form action="list.jsp">
		<select name="type">
			<option value="sname">학생명</option>
			<option value="pname">교수명</option>
			<option value="sdept">학생학과</option>
			<option value="year">학년</option>
		</select>
		<input type="text" name="word">
		<input type="submit" value="검색">
	</form>
	<table id="tbl" width=700 border=1>
		<tr>
			<td>학번</td>
			<td>학생명</td>
			<td>학생학과</td>
			<td>학년</td>
			<td>생년월일</td>
			<td>교수명</td>
			<td>교수학과</td>
		</tr>
		<%	
			String type=request.getParameter("type")==null?"scode"
					:request.getParameter("type"); // type이 null이면 scode, 아니면 type값을 출력
			String word=request.getParameter("word")==null?""
					:request.getParameter("word"); // word가 null이면 공백, 아니면 word값을 출력			
			
			SDAO dao=new SDAO();
			ArrayList<SVO> list=dao.list(type, word); // type, word 넘겨주기
			for(SVO vo:list){
				out.print("<tr class=row>");
				out.print("<td class=scode>" + vo.getScode() + "</td>");
				out.print("<td class=sname>" + vo.getSname() + "</td>");
				out.print("<td>" + vo.getSdept() + "</td>");
				out.print("<td>" + vo.getYear() + "</td>");
				out.print("<td>" + vo.getBirthday() + "</td>");
				out.print("<td>" + vo.getPname() + "</td>");
				out.print("<td>" + vo.getPdept() + "</td>");
				out.print("</tr>");
			}
		%>
	</table>
	<div id=enroll>
		<h1><span id="sname"></span><span id="total"></span></h1>
		<table id="tbl1" width=700 border=1></table>
		<script id="temp" type="text/x-handlebars-template">
			{{#each items}}
				<tr>
					<td>{{lcode}}</td>
					<td>{{lname}}</td>
					<td>{{grade}}</td>
					<td>{{edate}}</td>
				</tr>
			{{/each}}
		</script>
	</div>
</body>
<script>
	$("#tbl").on("click", ".row", function(){
		var sname=$(this).find(".sname").html();
		$("#sname").html(sname + "의 수강신청 목록");
		
		var scode=$(this).find(".scode").html();
		$.ajax({
			type:"get",
			url:"enroll.jsp",
			dataType:"json",
			data:{"scode":scode},
			success:function(data){
				$("#total").html(data.total);
				
				var temp=Handlebars.compile($("#temp").html());
				$("#tbl1").html(temp(data));
			}
		});
	});
</script>
</html>