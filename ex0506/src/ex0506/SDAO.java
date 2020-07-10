package ex0506;

import java.sql.*;
import java.util.*;

public class SDAO {
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
	
	// �л���� ���
	public ArrayList<SVO> list()throws Exception{
		ArrayList<SVO> list=new ArrayList<SVO>();
		String sql="SELECT * FROM STUPRO";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			SVO vo=new SVO();
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setDept(rs.getString("dept"));
			list.add(vo);
		}
		return list;
	}
	
	// Ư���л��� ��û�� ���¸��
	public ArrayList<CVO> elist(String scode)throws Exception{
		ArrayList<CVO> list=new ArrayList<CVO>();
		String sql="SELECT * FROM COUENR WHERE SCODE=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setCnt(rs.getInt("grade"));
			list.add(vo);
		}
		return list;
	}
	
	// Ư���л��� ������� ���
	public CVO avg(String scode)throws Exception{
		CVO vo=new CVO();
		String sql="SELECT SCODE, AVG(GRADE) AVG, COUNT(LCODE) CNT FROM COUENR WHERE SCODE=? GROUP BY SCODE";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, scode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			vo.setAvg(rs.getDouble("avg"));
			vo.setCnt(rs.getInt("cnt"));
		}
		return vo;
	}
} // class