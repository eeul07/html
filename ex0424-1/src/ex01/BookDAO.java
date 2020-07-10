package ex01;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
	// 데이터베이스 접속
		public Connection con()throws Exception{
			   String driver = "oracle.jdbc.driver.OracleDriver";
			   String url = "jdbc:oracle:thin:@localhost:1521:xe";
			   String user = "system";
			   String password = "1234";

			   Class.forName(driver);
			   Connection con=DriverManager.getConnection(url, user, password);
			   return con;
			   }
		
		// 데이터 입력
		public void insert(BookVO vo)throws Exception{
			String sql="insert into tbl_book(sno, title, author, price) values(seq_sno.nextval, ?, ?, ?)";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setInt(3, vo.getPrice());
			ps.execute();
			con().close();
		}
		
		// 전체 데이터개수 출력
		public int count()throws Exception{
			int count=0;
			String sql="select count (*) cnt from tbl_book";
			PreparedStatement ps=con().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				count=rs.getInt("cnt");
			}
			con().close();
			return count;
		}
		
		// 데이터 목록출력
		public ArrayList<BookVO> list()throws Exception{
			ArrayList<BookVO> list=new ArrayList<BookVO>();
			String sql="select * from tbl_book";
			PreparedStatement ps=con().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BookVO vo=new BookVO();
				vo.setSno(rs.getInt("sno"));
				vo.setTitle(rs.getString("title"));
				vo.setAuthor(rs.getString("author"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
			return list;
		}
		
		// 데이터 삭제
		public void delete(int sno)throws Exception{
			String sql="delete from tbl_book where sno=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setInt(1, sno);
			ps.execute();
			con().close();
		}
		
		// 데이터 수정
		public void update(BookVO vo)throws Exception{
			String sql="update tbl_book set price=? where sno=?";
			PreparedStatement ps=con().prepareStatement(sql);
			ps.setInt(1, vo.getPrice());
			ps.setInt(2, vo.getSno());
			ps.execute();
			con().close();
		}
} // class