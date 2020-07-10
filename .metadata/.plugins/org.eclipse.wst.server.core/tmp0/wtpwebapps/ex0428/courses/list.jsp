<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0428.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>강좌목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<h1>[ COURSES LIST ]</h1>
	<form action="list.jsp">
		<select name="type">
			<option value="lname">강좌명</option>
			<option value="room">강의실</option>
			<option value="pname">담당교수명</option>
			<option value="dept">교수학과</option>
		</select>
		<input type="text" name="word">
		<input type="submit" value="검색">
	</form>
	<table id="tbl" width=700 border=1>
		<tr>
			<td>강좌번호</td>
			<td>강좌명</td>
			<td>강의실</td>
			<td>담당교수명</td>
			<td>교수학과</td>
		</tr>
		<%
			String type=request.getParameter("type")==null?"lcode"
				:request.getParameter("type"); // type이 null이면 lcode, 아니면 type값을 출력
			String word=request.getParameter("word")==null?""
				:request.getParameter("word"); // word가 null이면 공백, 아니면 word값을 출력
			
			EDAO dao=new EDAO();
			ArrayList<CVO> list=dao.clist(type, word);
			for(CVO vo:list){
				out.print("<tr class=row>");
				out.print("<td class=lcode>" + vo.getLcode() + "</td>");
				out.print("<td class=lname>" + vo.getLname() + "</td>");
				out.print("<td>" + vo.getRoom() + "</td>");
				out.print("<td>" + vo.getPname() + "</td>");
				out.print("<td>" + vo.getDept() + "</td>");
				out.print("</tr>");
			}
		%>
	</table>
	<div id=enroll>
		<h1><span id="lname"></span><span id="total"></span></h1>
		<table id="tbl1" width=700 border=1></table>
		<script id="temp" type="text/x-handlebars-template">
			<tr>
				<td>학번</td>
				<td>학생명</td>
				<td>강좌번호</td>
				<td>강좌명</td>
				<td>수강신청일</td>
				<td>성적</td>
			</tr>
			{{#each items}}
				<tr>
					<td>{{scode}}</td>
					<td>{{sname}}</td>
					<td>{{lcode}}</td>
					<td>{{lname}}</td>
					<td>{{edate}}</td>
					<td>{{grade}}</td>
				</tr>
			{{/each}}
		</script>
	</div>
</body>
<script>
	$("#tbl").on("click", ".row", function(){
		var lname=$(this).find(".lname").html();
		$("#lname").html(lname + " 수강신청한 학생목록");
		
		var lcode=$(this).find(".lcode").html();
		$.ajax({
			type:"get",
			url:"enroll.jsp",
			dataType:"json",
			data:{"lcode":lcode},
			success:function(data){
				$("#total").html(data.total);
				
				var temp=Handlebars.compile($("#temp").html());
				$("#tbl1").html(temp(data));
			}
		});
	});
</script>
</html>