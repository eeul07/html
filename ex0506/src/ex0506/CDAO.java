package ex0506;

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
	public ArrayList<CVO> list()throws Exception{
		ArrayList<CVO> list=new ArrayList<CVO>();
		String sql="SELECT * FROM COUPRO";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			CVO vo=new CVO();
			vo.setLcode(rs.getString("lcode"));
			vo.setLname(rs.getString("lname"));
			vo.setPname(rs.getString("pname"));
			vo.setRoom(rs.getString("room"));
			list.add(vo);
		}
		return list;
	}
	
	// Ư�����¸� ��û�� �л����
		public ArrayList<SVO> elist(String lcode)throws Exception{
			ArrayList<SVO> list=new ArrayList<SVO>();
			String sql="SELECT * FROM STUENR WHERE LCODE=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setString(1, lcode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				SVO vo=new SVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setDept(rs.getString("dept"));
				vo.setCnt(rs.getInt("grade"));
				list.add(vo);
			}
			return list;
		}
		
	// Ư�������� ������� ���
	public CVO avg(String lcode)throws Exception{
		CVO vo=new CVO();
		String sql="SELECT LCODE, AVG(GRADE) AVG, COUNT(SCODE) CNT FROM STUENR WHERE LCODE=? GROUP BY LCODE";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, lcode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			vo.setAvg(rs.getDouble("avg"));
			vo.setCnt(rs.getInt("cnt"));
		}
		return vo;
	}
} // class