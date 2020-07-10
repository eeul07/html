<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글관리</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<style>
		#page{width:800px; background:#aeb2fe; margin:auto; padding:10px;}
		h1{text-align:center;}
		.box{width:755px; border:1px solid white; padding:10px;
			margin:auto; margin:10px;}
		.date{width:150px; font-size:10px; color:white; float:left;}
		#content{margin:10px}
		.btnLike{color:red;}
		.cnt{font-size:13px;}
		.like{float:right;}
	</style>
</head>
<body>
	<div id="page">
		<h1>[댓글관리]</h1>
		<div>
			<input type="text" id="content" size=50>
			<input type="button" value="저장" id="btnInsert">
			<%
				ReplyDAO dao=new ReplyDAO();
				ArrayList<ReplyVO> list=dao.list();
				for(int i=0; i<list.size(); i++){
					ReplyVO vo=list.get(i);
					int rno=vo.getRno();
					String content=vo.getContent();
					Date wdate=vo.getWdate();
					int cnt=vo.getCnt();
					out.println("<div class=box>");
					out.println("<div class=title>");
					out.println("<span class=date>" + wdate + "</span>");
					out.println("<div class=like rno=" + rno + ">");
					out.println("<button class=btnLike>♥</button>");
					out.println("<span class=cnt>" + cnt + "</span>");
					out.println("<button class=delete>삭제</button>");
					out.println("</div>");
					out.println("</div>");
					out.println("<div class=content>" + content + "</div>");
					out.println("</div>");
				}
			%>
		</div>
	</div>
</body>
<script>
	$(".box").on("click", ".delete", function(){
		var rno=$(this).parent().attr("rno");
		if(confirm(rno + "번을 삭제하시겠습니까?")==false) return;
		location.href="sqlDelete.jsp?rno=" + rno;
	});
	$(".box").on("click", ".btnLike", function(){
		var rno=$(this).parent().attr("rno");
		if(confirm(rno + "번을 좋아요 누르시겠습니까?")==false) return;
		location.href="sqlUpdate.jsp?rno=" + rno;
	});
	$("#btnInsert").on("click", function(){
		if(confirm("댓글을 저장하시겠습니까?")==false) return;
		var content=$("#content").val();
		if(content==""){
			alert("내용을 입력하세요!")
		}else{
			location.href="sqlInsert.jsp?content=" + content;
		}
	});
</script>
</html>