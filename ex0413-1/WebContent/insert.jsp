<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소등록</title>
<link rel="stylesheet" href="home.css"/>
</head>
<body>
	<div id="page">
		<div id="content">
			<h1>주소등록</h1>
			<hr>
			<form class="frm" action="sqlInsert.jsp">
				<table class="tblInsert">
					<tr>
						<td>이름</td>
						<td><input type="text" name="aname"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="tel"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><textarea rows="5" cols="50" name="address"></textarea></td>
					</tr>
					<tr>
						<td colspan=2 style="text-align:center;">
							<input type="submit" value="저장">
							<input type="reset" value="취소" onClick="location.href='list.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>