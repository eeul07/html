<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>블로그 글쓰기</title>
	<link rel="stylesheet" href="home.css"/>
	<style>
		.tbl {border:3px solid orange; margin:auto; width:700;
				border-collapse:collapse;}
		.tbl td, .tbl tr {padding:10px; text-align:center;
									border:2px solid orange;}
	</style>
</head>
<body>
	<div id="page">
		<div id="header">
			<h1>SEUL's BLOG</h1>
		</div>
		<div id="content">
			<div id="menu">
				<div class="item"><a href="list.jsp">List</a></div>
			</div>
			<div class="box">
				<form action="sqlInsert.jsp">
					<table class="tbl">
						<tr>
							<th>제목</th>
							<td><input type="text" size=90 name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea rows="10" cols="90" name="article"></textarea>
							</td>
						</tr>
					</table>
					<div style="text-align:center; padding:10px;">
								<input type="submit" value="저장">&nbsp;&nbsp;&nbsp;
								<input type="reset" value="취소">
					</div>
				</form>
			</div>
		</div>
		<div id="footer">
			<h3>https://blog.naver.com/yeseul990712</h3>
		</div>
	</div>
</body>
</html>