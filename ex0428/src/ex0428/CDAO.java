package ex0428;

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
			
	// Ư�� ������ ��û�л� ��� ���
	public ArrayList<EVO> list(String lcode)throws Exception{
		ArrayList<EVO> list=new ArrayList<EVO>();
		String sql="select * from view_enrollments where lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, lcode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			EVO vo=new EVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setGrade(rs.getInt("grade"));
			vo.setEdate(rs.getDate("edate"));
			list.add(vo);
		}
		return list;
	}
	
	// ���¸� ��û�� �л���
	public int count(String lcode)throws Exception{
		int count=0;
		String sql="select count(*) cnt from view_enrollments where lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, lcode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			count=rs.getInt("cnt");
		}
		return count;
	}
} // class