package ex0429;

import java.sql.*;
import java.util.ArrayList;

public class CDAO {
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
		
	// ���¸�� ���
	public ArrayList<CVO> list(String pcode)throws Exception{
		ArrayList<CVO> list=new ArrayList<CVO>();
		String sql="select * from courses where instructor like ?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, pcode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setHours(rs.getInt("hours"));
			vo.setRoom(rs.getString("room"));
			vo.setCapacity(rs.getInt("capacity"));
			vo.setPersons(rs.getInt("persons"));
			vo.setInstructor(rs.getString("instructor"));
			list.add(vo);
		}
		return list;
	}
} // class