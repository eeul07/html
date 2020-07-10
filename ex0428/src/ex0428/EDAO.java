package ex0428;

import java.sql.*;
import java.util.*;

public class EDAO {
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
	
	// Ư�� �л��� ������û ��� ���
	public ArrayList<EVO> list(String scode)throws Exception{
		ArrayList<EVO> list=new ArrayList<EVO>();
		String sql="select * from view_enrollments where scode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
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
	
	// �л��� ��û�� ������û �����
	public int count(String scode)throws Exception{
		int count=0;
		String sql="select count(*) cnt from enrollments where scode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			count=rs.getInt("cnt");
		}
		return count;
	}
	
	// ���� ������ ������
	public ArrayList<CVO> clist(String type, String word)throws Exception{
		ArrayList<CVO> list=new ArrayList<CVO>();
		String sql="select * from view_courses where " + type + " like ?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, "%" + word + "%");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setRoom(rs.getString("room"));
			vo.setPname(rs.getString("pname"));
			vo.setDept(rs.getString("dept"));
			list.add(vo);
		}
		return list;
	}
} // class