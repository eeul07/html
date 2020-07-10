package ex0504;

import java.sql.*;
import java.util.*;

public class EnrollDAO {
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
	
	// 과목별 평균점수 출력
	public ArrayList<EnrollVO> clist()throws Exception{
		ArrayList<EnrollVO> list=new ArrayList<EnrollVO>();
		String sql="SELECT LCODE, LNAME, PNAME, AVG(GRADE) A FROM ENROLL GROUP BY LCODE, LNAME, PNAME";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			EnrollVO vo=new EnrollVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setPname(rs.getString("pname"));
			vo.setAvg(rs.getDouble("a"));
			list.add(vo);
			System.out.println(vo.toString());
		}
		return list;
	}
	
	// 과목별 평균점수 출력
	public ArrayList<SVO> slist()throws Exception{
		ArrayList<SVO> list=new ArrayList<SVO>();
		String sql="SELECT DEPT, SCODE, SNAME, AVG(GRADE) A FROM ENROLL GROUP BY DEPT, SCODE, SNAME ORDER BY DEPT, SCODE";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			SVO vo=new SVO();
			vo.setDept(rs.getString("dept"));
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setAvg(rs.getDouble("a"));
			list.add(vo);
		}
		return list;
	}
} // class