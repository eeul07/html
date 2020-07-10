package ex0429;

import java.sql.*;
import java.util.ArrayList;

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
		
	// Ư���л��� ��û�� �������
	public ArrayList<EVO> list(String scode)throws Exception{
		ArrayList<EVO> list=new ArrayList<EVO>();
		String sql="select * from view_enroll where scode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			EVO vo=new EVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setEdate(rs.getString("edate"));
			vo.setGrade(rs.getInt("grade"));
			list.add(vo);
		}
		con().close();
		return list;
	}
	
	// Ư�� ���¸� ������û�� �л����
	public ArrayList<EVO> clist(String lcode)throws Exception{
		ArrayList<EVO> list=new ArrayList<EVO>();
		String sql="select * from view_enroll where lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, lcode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			EVO vo=new EVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setEdate(rs.getString("edate"));
			vo.setGrade(rs.getInt("grade"));
			list.add(vo);
		}
		con().close();
		return list;
	}
	
	// ������û 
	public void insert(String scode, String lcode)throws Exception{
		String sql="insert into view_enroll(scode, lcode, edate) values(?, ?, sysdate)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ps.setString(2, lcode);
		ps.execute();
		con().close();
	}
	   
	// �������
	public void delete(String scode, String lcode)throws Exception{
		String sql="delete from view_enroll where scode=? and lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
	    ps.setString(1, scode);
	    ps.setString(2, lcode);
	    ps.execute();
	    con().close();
    }
	
	// ������ ����
	public void update(int grade, String scode, String lcode)throws Exception{
		String sql="update enrollments set grade=? where scode=? and lcode=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, grade);
		ps.setString(2, scode);
		ps.setString(3, lcode);
		ps.execute();
		con().close();
	}
} // class