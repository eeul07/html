package ex0427;

import java.sql.*;
import java.util.*;

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
	public ArrayList<SVO> list(String type, String word)throws Exception{
		ArrayList<SVO> list=new ArrayList<SVO>();
		String sql="select * from sview where " + type + " like ?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, "%" + word + "%");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			SVO vo=new SVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setSdept(rs.getString("sdept"));
			vo.setBirthday(rs.getDate("birthday"));
			vo.setPname(rs.getString("pname"));
			vo.setDept(rs.getString("pdept"));
			list.add(vo);
		}
		return list;
	}
} // class