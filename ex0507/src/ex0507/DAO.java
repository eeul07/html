package ex0507;

import java.sql.*;
import java.util.*;
import org.json.simple.*;

public class DAO {
	// 데이터베이스 접속 메서드
	public Connection con()throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa"; // user변경
		String password = "pass";
	
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		return con;
	}
	
	// 교수입력 메서드
	public ArrayList<VO> pinsert(String pcode, String pname)throws Exception{
		String sql="{call add_professors(?, ?, ?)}"; // 프로시저 호출
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, pcode);
		cs.setString(2, pname);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
		cs.execute();
		
		ArrayList<VO> list=new ArrayList<VO>();
		ResultSet rs=(ResultSet)cs.getObject(3);
		while(rs.next()) {
			VO vo=new VO();
			vo.setCode(rs.getString("pcode"));
			vo.setName(rs.getString("pname"));
			list.add(vo);
		}
		return list;
	}
	
	// 특정학과에 속하는 교수목록, 학생목록 출력
	public JSONObject list(String dept)throws Exception{
		JSONObject jObject=new JSONObject();
		String sql="{call out_dept(?, ?, ?, ?, ?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, dept);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
		cs.execute();
		
		// 특정학과 교수목록, 교수인원수
		JSONArray pArray=new JSONArray();
		ResultSet rs=(ResultSet)cs.getObject(2);
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("pcode", rs.getString("pcode"));
			obj.put("pname", rs.getString("pname"));
			obj.put("dept", rs.getString("dept"));
			pArray.add(obj);
		}
		jObject.put("pArray", pArray);
		
		int pcnt=cs.getInt(3);
		jObject.put("pcnt", pcnt);
		
		// 특정학과 학생목록, 학생인원수
		rs=(ResultSet)cs.getObject(4);
		JSONArray sArray=new JSONArray();
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("scode", rs.getString("scode"));
			obj.put("sname", rs.getString("sname"));
			obj.put("dept", rs.getString("dept"));
			sArray.add(obj);
		}
		jObject.put("sArray", sArray);
		
		int scnt=cs.getInt(5);
		jObject.put("scnt", scnt);
		return jObject;
	}
	
	// 특정학생의 수강신청목록
	public JSONObject elist(String scode)throws Exception{
		JSONObject jObject=new JSONObject();
		String sql="{call out_enroll(?, ?, ?, ?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, scode);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
		cs.execute();
		
		ResultSet rs=(ResultSet)cs.getObject(2); // 강제형변환
		JSONArray array=new JSONArray();
		while(rs.next()) {
			JSONObject obj=new JSONObject();
			obj.put("lcode", rs.getString("lcode"));
			obj.put("lname", rs.getString("lname"));
			obj.put("grade", rs.getInt("grade"));
			array.add(obj);
		}
		jObject.put("array", array);
		double avg=cs.getDouble(3);
		jObject.put("avg", avg);
		double cnt=cs.getDouble(4);
		jObject.put("cnt", cnt);
		
		return jObject;
	}
} // class