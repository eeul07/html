package ex0429;

import java.sql.*;
import java.util.*;

public class PDAO {
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
	
	// 교수목록 출력
	public ArrayList<PVO> list()throws Exception{
		ArrayList<PVO> list=new ArrayList<PVO>();
		String sql="select * from professors";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			PVO vo=new PVO();
			vo.setPcode(rs.getString("pcode"));
			vo.setPname(rs.getString("pname"));
			vo.setDept(rs.getString("dept"));
			vo.setHiredate(rs.getString("hiredate"));
			vo.setTitle(rs.getString("title"));
			vo.setSalary(rs.getInt("salary"));
			list.add(vo);
		}
		return list;
	}
} // class