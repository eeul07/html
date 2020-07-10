<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0504.*, java.util.*, org.json.simple.*, java.text.*" %>
<%
	DecimalFormat df=new DecimalFormat("##0.00");
	EnrollDAO dao=new EnrollDAO();
	ArrayList<SVO> list=dao.slist();
	
	JSONObject jObject=new JSONObject();
	JSONArray array=new JSONArray();
	for(SVO vo:list){
		JSONObject obj=new JSONObject();
		obj.put("dept", vo.getDept());
		obj.put("scode", vo.getScode());
		obj.put("sname", vo.getSname());
		String avg=df.format(vo.getAvg());
		obj.put("avg", avg);
		array.add(obj);
	}
	jObject.put("slist", array);
	out.println(jObject);
%>