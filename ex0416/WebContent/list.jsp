<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0416.*, java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<link rel="stylesheet" href="home.css">
	<style>
		.del{cursor:pointer;}
		.del:hover{color:black;}
	</style>
</head>
<body>
	<h1>[상품목록]</h1>
	<div id="content">
	<div id="menu">
			<button class="btn">1</button>
			<button class="btn">2</button>
			<button class="btn">3</button>
			<button class="btn">4</button>
			<button class="btn">5</button>
			<a href="insert.html">상품입력</a>
		</div>
		<%
			ProductDAO dao=new ProductDAO();
			ArrayList<ProductVO> list=dao.list();
			for(int i=0; i<list.size(); i++){
				ProductVO vo=list.get(i);
				DecimalFormat df=new DecimalFormat("#,###원");
				String strPrice=df.format(vo.getPrice());
				out.println("<div class='box'>");
				out.println("<img src='http://placehold.it/150x150'>");
				out.println("<div class='pname'>" + vo.getPname() + "</div>");
				out.println("<div class='price'>" + strPrice + "</div>");
				out.println("<div class='del' pno=" + vo.getPno() + ">"+" 삭제</div>");
				out.println("</div>");
			}
		%>
	</div>
	<!--라이트 박스-->
	<div id="darken-background">
		<div id="lightbox">
			<img src="http://placehold.it/500x500"/>
			<div id="txtName"></div>
			<div id="txtPrice"></div>
			<div><button id="btnclose">닫기</button></div>
		</div>
	</div>
</body>
<script>
	$(".box").on("click", ".del", function(){
		var pno=$(this).attr("pno");
		if(confirm(pno + "번 상품을 삭제 하시겠습니까?")==false) return;
		location.href="sqlDelete.jsp?pno=" + pno;
	});
	$(".box").on("click", "img", function(){
		var box=$(this).parent();
		var pname=box.find(".pname").html();
		var price=box.find(".price").html();
		$("#txtName").html("상품명 : " + pname);
		$("#txtPrice").html("가격 : " + price);
		$("#darken-background").show();
	});
	$("#btnclose").on("click", function(){
		$("#darken-background").hide();
	});
	$("#menu").on("click", ".btn", function(){
		var num=$(this).html();
		$("#content").css("width", 190*num);
	});
</script>
</html>