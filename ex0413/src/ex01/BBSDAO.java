package ex01;

import java.sql.*;
import java.util.*;

public class BBSDAO {
	// �����ͺ��̽� ���� �޼���
	public Connection con()throws Exception{
		   String driver = "oracle.jdbc.driver.OracleDriver";
		   String url = "jdbc:oracle:thin:@localhost:1521:xe";
		   String user = "system";
		   String password = "1234";

		   Class.forName(driver);
		   Connection con=DriverManager.getConnection(url, user, password);
		   return con;
		   }
	
	// ������ ��� ��� �޼���
	public ArrayList<BBSVO> list()throws Exception{
		ArrayList<BBSVO> array=new ArrayList<BBSVO>();
		String sql="SELECT * FROM TBL_BBS ORDER BY BNO DESC";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			BBSVO vo=new BBSVO();
			vo.setBno(rs.getInt("bno"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWdate(rs.getDate("wdate"));
			array.add(vo);
		}
		return array;
	}
	
	// ������ �Է� �޼���
	public void insert(BBSVO vo)throws Exception{
		String sql="INSERT INTO TBL_BBS(BNO, TITLE, CONTENT, WDATE) VALUES(SEQ_BBS.NEXTVAL, ?, ?, SYSDATE)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getContent());
		ps.execute();
	}
	
	// ������ �б� �޼���
	public BBSVO read(int bno)throws Exception{
		String sql="SELECT * FROM TBL_BBS WHERE BNO=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, bno);
		ResultSet rs=ps.executeQuery();
		
		BBSVO vo=new BBSVO();
		if(rs.next()) {
			vo.setBno(rs.getInt("bno"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWdate(rs.getTimestamp("wdate"));
		}
		return vo;
	}
	
	// ������ ���� �޼���
	public void delete(int bno)throws Exception{
		String sql="DELETE FROM TBL_BBS WHERE BNO=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, bno);
		ps.execute();
	}
	
	// ������ ���� �޼���
	public void update(BBSVO vo)throws Exception{
		String sql="UPDATE TBL_BBS SET TITLE=?, CONTENT=? WHERE BNO=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getContent());
		ps.setInt(3, vo.getBno());
		ps.execute();
	}
} // class
