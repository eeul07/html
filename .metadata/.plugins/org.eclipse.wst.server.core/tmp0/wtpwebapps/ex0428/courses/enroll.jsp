<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ex0428.*, java.util.*, org.json.simple.*, java.text.*" %>

<%	
	String lcode=request.getParameter("lcode");
	CDAO dao=new CDAO();
	ArrayList<EVO> list=dao.list(lcode);
	
	JSONObject jObject=new JSONObject();
	jObject.put("total", dao.count(lcode));
	
	JSONArray array=new JSONArray();
	for(EVO vo:list){
		JSONObject obj=new JSONObject();
		obj.put("scode", vo.getScode());
		obj.put("sname", vo.getSname());
		obj.put("lcode", vo.getLcode());
		obj.put("lname", vo.getLname());
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String edate=sdf.format(vo.getEdate());
		
		obj.put("edate", edate);
		
		obj.put("grade", vo.getGrade());
		array.add(obj);
	}
	jObject.put("items", array);
	out.println(jObject);
%>