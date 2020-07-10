package ex0429;

import java.sql.*;
import java.util.ArrayList;

public class SDAO {
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
		
	// 학생목록 출력
	public ArrayList<SVO> list(String pcode)throws Exception{
		ArrayList<SVO> list=new ArrayList<SVO>();
		String sql="select * from students where advisor like ?"; // 연산자를 like로 변경
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, pcode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			SVO vo=new SVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			vo.setYear(rs.getString("year"));
			vo.setBirthday(rs.getString("birthday"));
			vo.setAdvisor(rs.getString("advisor"));
			list.add(vo);
		}
		return list;
	}
} // class