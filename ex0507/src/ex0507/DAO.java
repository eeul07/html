package ex0507;

import java.sql.*;
import java.util.*;
import org.json.simple.*;

public class DAO {
	// �����ͺ��̽� ���� �޼���
	public Connection con()throws Exception{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "haksa"; // user����
		String password = "pass";
	
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url, user, password);
		return con;
	}
	
	// �����Է� �޼���
	public ArrayList<VO> pinsert(String pcode, String pname)throws Exception{
		String sql="{call add_professors(?, ?, ?)}"; // ���ν��� ȣ��
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
	
	// Ư���а��� ���ϴ� �������, �л���� ���
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
		
		// Ư���а� �������, �����ο���
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
		
		// Ư���а� �л����, �л��ο���
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
	
	// Ư���л��� ������û���
	public JSONObject elist(String scode)throws Exception{
		JSONObject jObject=new JSONObject();
		String sql="{call out_enroll(?, ?, ?, ?)}";
		CallableStatement cs=con().prepareCall(sql);
		cs.setString(1, scode);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
		cs.execute();
		
		ResultSet rs=(ResultSet)cs.getObject(2); // ��������ȯ
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