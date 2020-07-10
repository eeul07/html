package ex0424;

import java.sql.*;
import java.util.*;

public class BookDAO {
	// �����ͺ��̽� ����
		public Connection con()throws Exception{
			   String driver = "oracle.jdbc.driver.OracleDriver";
			   String url = "jdbc:oracle:thin:@localhost:1521:xe";
			   String user = "system";
			   String password = "1234";

			   Class.forName(driver);
			   Connection con=DriverManager.getConnection(url, user, password);
			   return con;
			   }
		
		// ������ �Է�
		public void insert(BookVO vo)throws Exception{
			String sql="insert into tbl_book(sno, thmn, title, authors, price, status) values(seq_sno.nextval, ?, ?, ?, ?, ?)";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setInt(1, vo.getSno());
			ps.setString(2, vo.getThmn());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getAuthors());
			ps.setInt(5, vo.getPrice());
			ps.setString(6, vo.getStatus());
			ps.execute();
			con().close();
		}
		
		// ������ ������
		public ArrayList<BookVO> list()throws Exception{
			ArrayList<BookVO> list=new ArrayList<BookVO>();
			String sql="select * from tbl_book order by sno desc";
			PreparedStatement ps=con().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BookVO vo=new BookVO();
				vo.setSno(rs.getInt("sno"));
				vo.setThmn(rs.getString("thmn"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthors(rs.getString("authors"));
				vo.setPrice(rs.getInt("price"));
				vo.setStatus(rs.getString("Status"));
				list.add(vo);
			}
			return list;
		}
} // class