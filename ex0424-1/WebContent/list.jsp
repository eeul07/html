<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex01.*, org.json.simple.*, java.util.*" %>
<%
	BookDAO dao=new BookDAO();
	int count=dao.count();
	
	JSONObject jObject=new JSONObject();
	jObject.put("total", count); // jObject안에 total안에 count
	
	ArrayList<BookVO> array=dao.list();
	JSONArray jArray=new JSONArray();
	
	for(BookVO vo:array){
		JSONObject obj=new JSONObject();
		obj.put("sno", vo.getSno());
		obj.put("title", vo.getTitle());
		obj.put("author", vo.getAuthor());
		obj.put("price", vo.getPrice());
		jArray.add(obj);
	}
	jObject.put("items", jArray); // jObject안에 items안에 jArray
	out.println(jObject);
%>