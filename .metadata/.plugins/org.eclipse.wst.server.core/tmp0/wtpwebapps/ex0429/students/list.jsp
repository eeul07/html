<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0429.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
	<style>
		h1{text-align:center;}
		#tbl, #etbl{text-align:center; border-collapse:collapse;
							border:1px solid #0128da; margin:auto;}
		.title{background:#5c7afe; color:white;}
		.row:hover{background:#9aacfd; color:white;}
		button{cursor:pointer;}
		#enroll{text-align:center; display:none;}
		#students{width:700px; border:1px solid #0128da; text-align:center;
				background:#5c7afe; color:white; margin:auto;}
		#courses{width:700px; border:1px solid #0128da; text-align:center;
				background:#5c7afe; color:white; margin:auto; margin-top:5px; margin-bottom:5px;}
	</style>
</head>
<body>
	<div id="menu">
    	<a href="/ex0429/professors/list.jsp">교수관리</a>
      	<a href="/ex0429/students/list.jsp">학생관리</a>
     	 <a href="/ex0429/courses/list.jsp">강좌관리</a> 	
   	</div>
	<h1>[ STUDENTS LIST ]</h1>
	<table id=tbl width=700 border=1>
		<tr class=title>
			<td>학번</td>
			<td>학생명</td>
			<td>학과</td>
			<td>생년월일</td>
			<td>지도교수</td>
			<td>수강신청</td>
		</tr>
		<%
			SDAO sdao=new SDAO();
			ArrayList<SVO> list=sdao.list("%%");
			for(SVO vo:list){
				out.println("<tr class=row>");
				out.println("<td class=scode>" + vo.getScode() + "</td>");
				out.println("<td class=sname>" + vo.getSname() + "</td>");
				out.println("<td>" + vo.getDept() + "</td>");
				out.println("<td>" + vo.getBirthday() + "</td>");
				out.println("<td>" + vo.getAdvisor() + "</td>");
				out.println("<td><button>수강신청</button></td>");
				out.println("</tr>");
			}
		%>
	</table>
	<div id="enroll">
		<h1>[ ENROLLMENTS LIST ]</h1>
		<div id=students>
			학번 : <span id="scode"></span>
			성명 : <span id="sname"></span>
		</div>
		<div id="courses">
			강좌명 : 
			<select id="lcode"></select>
			<script id="temp" type="text/x-handlebars-template">
			{{#each clist}}
				<option value={{lcode}}>{{lcode}}:{{lname}}</option>
			{{/each}}
		</script>
			<input type="button" value="신청" id="btnEnroll">
		</div>
		<table id="etbl" width=700 border=1></table>
		<script id="etemp" type="text/x-handlebars-template">
			<tr class=title>
				<td>강좌번호</td>
				<td>강좌명</td>
				<td>수강신청일</td>
				<td>접수</td>
				<td>수강취소</td>
			</tr>
			{{#each elist}}
				<tr class=row>
					<td class=lcode>{{lcode}}</td>
					<td>{{lname}}</td>
					<td>{{edate}}</td>
					<td>{{grade}}</td>
					<td><button>수강취소</button></td>
				</tr>
			{{/each}}
		</script>
	</div>
</body>
<script>
	var scode;
	
	$("#etbl").on("click", ".row button", function(){
		if(!confirm("수강신청을 취소하시겠습니까?"))return;
	    var row=$(this).parent().parent();
	    var lcode=row.find(".lcode").html();
		$.ajax({
			type:"post",
	        url:"delete.jsp",
	        data:{"scode":scode, "lcode":lcode},
	        success:function(){
	        	alert("수강취소완료!");
	            getList();
	        }
		});
	})
		   
   // 리턴받을값이 있으면 데이터타입이 필요하고 리턴받을값이 없으면 데이터타입이 필요없다.
   $("#btnEnroll").on("click", function(){
      if(!confirm("수강신청하시겠습니까?"))return;
      var lcode=$("#lcode").val();
      $.ajax({
         type:"post",
         url:"insert.jsp",
         data:{"scode":scode, "lcode":lcode},
         success:function(){
            alert("수강신청완료!");
            getList();
         }
      });
   });
	
	$("#tbl").on("click", ".row button", function(){
		var row=$(this).parent().parent();
		scode=row.find(".scode").html();
		var sname=row.find(".sname").html();
		$("#scode").html(scode);
		$("#sname").html(sname);
		$("#enroll").show();
		
		getList();
	});
	
	$.ajax({
		type:"get",
		url:"cjson.jsp",
		dataType:"json",
		success:function(data){
			var temp=Handlebars.compile($("#temp").html());
			$("#lcode").html(temp(data));
		}
	});
	
	function getList(){
		$.ajax({
			type:"get",
			url:"ejson.jsp",
			data:{"scode":scode},
			dataType:"json",
			success:function(data){
				var temp=Handlebars.compile($("#etemp").html());
				$("#etbl").html(temp(data));
			}
		});
	}
</script>
</html>