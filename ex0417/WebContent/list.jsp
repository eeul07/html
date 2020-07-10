<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0417.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>성적처리</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<style>
		h1, h3{text-align:center; color:black;}
		#content{width:700px; background:#66b7fe;
						margin:auto; color:white; padding:10px;}
		#tbl{margin:auto; text-align:center;
				border-collapse:collapse;}
		input{text-align:right;}
		#tbl .title{background:#fef161; color:black;}
	</style>
</head>
<body>
	<h1>[성적처리]</h1>
	<div id="content">
		NAME : <input type="text" id="txtName">
		<hr>
		&nbsp;&nbsp;KOR : <input type="text" id="txtKor">
		<hr>
		&nbsp;&nbsp;ENG : <input type="text" id="txtEng">
		<hr>
		&nbsp;&nbsp;MAT : <input type="text" id="txtMat">
		<hr>
		<input type="button" value="등록" id="btnInsert">
		
		<h3>[성적목록]</h3>
		<table id="tbl" border=1 width=600>
			<tr class="title">
				<td>NO</td>
				<td>NAME</td>
				<td>KOR</td>
				<td>ENG</td>
				<td>MAT</td>
				<td>UPDATE</td>
				<td>DELETE</td>
			</tr>
			<%
				GradeDAO dao=new GradeDAO();
				ArrayList<GradeVO> list=dao.list();
				for(int i=0; i<list.size(); i++){
					GradeVO vo=list.get(i);
					int gno=vo.getGno();
					String gname=vo.getGname();
					int kor=vo.getKor();
					int eng=vo.getEng();
					int mat=vo.getMat();
					out.print("<tr class=row>");
					out.print("<td class=gno>" + gno + "</td>");
					out.print("<td class=gname>" + gname + "</td>");
					out.print("<td><input type=text class=kor size=2 value=" + kor + "></td>");
					out.print("<td><input type=text class=eng size=2 value=" + eng + "></td>");
					out.print("<td><input type=text class=mat size=2 value=" + mat + "></td>");
					out.print("<td><button class=update>수정</button></td>");
					out.print("<td><button class=delete>삭제</button></td>");
					out.print("</tr>");
				}
			%>
		</table>
	</div>
</body>
<script>
	$("#tbl").on("click", ".row .delete", function(){
		var row=$(this).parent().parent();
		var gno=row.find(".gno").html();
		if(confirm(gno + "번을 삭제하시겠습니까?")==false) return;
		location.href="sqlDelete.jsp?gno=" + gno;
	});
	$("#tbl").on("click", ".row .update", function(){
		var row=$(this).parent().parent();
		var gno=row.find(".gno").html();
		var kor=row.find(".kor").val();
		var eng=row.find(".eng").val();
		var mat=row.find(".mat").val();
		if(confirm(gno + "번을 수정하시겠습니까?")==false) return;
		location.href="sqlUpdate.jsp?gno=" + gno + "&kor=" + kor + "&eng=" + eng + "&mat=" + mat;
	});
	$("#btnInsert").on("click", function(){
		if(confirm("저장하시겠습니까?")==false) return;
		var gname=$("#txtName").val();
		var kor=$("#txtKor").val();
		var eng=$("#txtEng").val();
		var mat=$("#txtMat").val();
		if(gname==""){
			alert("이름을 입력하세요!");
			$("#txtName").val("");
			$("#txtName").focus();
		}else if(kor=="" || kor.replace(/[0-9]/g,'')){
			alert("국어점수를 숫자로 입력하세요!");
			$("#txtKor").val("");
			$("#txtKor").focus();
		}else if(eng=="" || eng.replace(/[0-9]/g,'')){
			alert("영어점수를 숫자로 입력하세요!");
			$("#txtEng").val("");
			$("#txtEng").focus();
		}else if(mat=="" || mat.replace(/[0-9]/g,'')){
			alert("수학점수를 숫자로 입력하세요!");
			$("#txtMat").val("");
			$("#txtMat").focus();
		}else{
			location.href="sqlInsert.jsp?gname=" + gname + "&kor=" + kor + "&eng=" + eng + "&mat=" + mat;
		}
	});
</script>
</html>