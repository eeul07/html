<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0506.*, java.util.*, org.json.simple.*" %>

<%
	String scode=request.getParameter("scode");
	SDAO dao=new SDAO();
	ArrayList<SVO> list=dao.list(); // 학생목록
	ArrayList<CVO> elist=dao.elist(scode); // 특정학생 강좌목록
	CVO vo=dao.avg(scode); // 특정학생 평균
	
	JSONObject jObject=new JSONObject();
	
	JSONArray jArray=new JSONArray();
	for(SVO v:list){
		JSONObject obj=new JSONObject();
		obj.put("scode", v.getScode());
		obj.put("sname", v.getSname());
		obj.put("dept", v.getDept());
		jArray.add(obj);
	}
	jObject.put("list", jArray);
	
	JSONArray jArray1=new JSONArray();
	for(CVO v:elist){
		JSONObject obj=new JSONObject();
		obj.put("lcode", v.getLcode());
		obj.put("lname", v.getLname());
		obj.put("grade", v.getCnt());
		jArray1.add(obj);
	}
	jObject.put("elist", jArray1);
	
	jObject.put("avg", vo.getAvg());
	jObject.put("cnt", vo.getCnt());
	out.print(jObject);
%>