package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddDAO {
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
		public ArrayList<AddVO> list()throws Exception{
			ArrayList<AddVO> array=new ArrayList<AddVO>();
			String sql="SELECT * FROM TBL_ADDRESS ORDER BY ANO DESC";
			PreparedStatement ps=con().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AddVO vo=new AddVO();
				vo.setAno(rs.getInt("ano"));
				vo.setAname(rs.getString("aname"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				array.add(vo);
			}
			return array;
		}
		
		// ������ �Է� �޼���
		public void insert(AddVO vo)throws Exception{
			String sql="INSERT INTO TBL_ADDRESS(ANO, ANAME, TEL, ADDRESS) VALUES(SEQ_ADD.NEXTVAL, ?, ?, ?)";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setString(1, vo.getAname());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddress());
			ps.execute();
		}
		
		// ������ �б� �޼���
		public AddVO read(int ano)throws Exception{
			String sql="SELECT * FROM TBL_ADDRESS WHERE ANO=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setInt(1, ano);
			ResultSet rs=ps.executeQuery();
			
			AddVO vo=new AddVO();
			if(rs.next()) {
				vo.setAno(rs.getInt("ano"));
				vo.setAname(rs.getString("aname"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
			}
			return vo;
		}
		
		// ������ ���� �޼���
		public void delete(int ano)throws Exception{
			String sql="DELETE FROM TBL_ADDRESS WHERE ANO=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setInt(1, ano);
			ps.execute();
		}
		
		// ������ ���� �޼���
		public void update(AddVO vo)throws Exception{
			String sql="UPDATE TBL_ADDRESS SET ANAME=?, TEL=?, ADDRESS=? WHERE ANO=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setString(1, vo.getAname());
			ps.setString(2, vo.getTel());
			ps.setString(3, vo.getAddress());
			ps.setInt(4, vo.getAno());
			ps.execute();
		}
} // class