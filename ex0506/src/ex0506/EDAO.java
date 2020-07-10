package ex0506;

import java.sql.*;
import java.util.*;

public class EDAO {
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
	
	// 강좌별 평균출력
	public ArrayList<CVO> clist()throws Exception{
		ArrayList<CVO> list=new ArrayList<CVO>();
		String sql="select clcode, lname, pname, room, avg(grade) avg, count(scode) cnt from couenr group by clcode, lname, pname, room";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("clcode"));
			vo.setLname(rs.getString("lname"));
			vo.setAvg(rs.getDouble("avg"));
			vo.setPname(rs.getString("pname"));
			vo.setRoom(rs.getString("room"));
			vo.setCnt(rs.getInt("cnt"));
			list.add(vo);
			System.out.println(vo.toString());
		}
		return list;
	}
	
	// 학생별 평균출력
	public ArrayList<SVO> slist()throws Exception{
		ArrayList<SVO> list=new ArrayList<SVO>();
		String sql="select sscode, sname, dept, avg(grade) avg, count(lcode) cnt from stuenr group by sscode, sname, dept";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			SVO vo=new SVO();
			vo.setScode(rs.getString("sscode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			vo.setAvg(rs.getDouble("avg"));
			vo.setCnt(rs.getInt("cnt"));
			list.add(vo);
			System.out.println(vo.toString());
		}
		return list;
	}
} // class