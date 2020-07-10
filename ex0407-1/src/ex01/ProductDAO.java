package ex01;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
	// 데이터베이스 접속 메서드
	public Connection con()throws Exception{
		   String driver = "oracle.jdbc.driver.OracleDriver";
		   String url = "jdbc:oracle:thin:@localhost:1521:xe";
		   String user = "system";
		   String password = "1234";

		   Class.forName(driver);
		   Connection con=DriverManager.getConnection(url, user, password);
		   return con;
		   }
	
	// 데이터 입력 메서드
	public void insert(ProductVO vo)throws Exception{
		String sql="insert into tbl_product(pno, pname, price) values(?, ?, ?)";
		PreparedStatement ps= con().prepareStatement(sql);
		ps.setString(1, vo.getPno());
		ps.setString(2, vo.getPname());
		ps.setInt(3, vo.getPrice());
		ps.execute();
		con().close();
	} // insert
	
		// 데이터 목록 메서드
		public ArrayList<ProductVO> list()throws Exception{
			ArrayList<ProductVO> array=new ArrayList<ProductVO>();
			String sql="select * from tbl_product";
			PreparedStatement ps=con().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ProductVO vo=new ProductVO();
				vo.setPno(rs.getString("pno"));
				vo.setPname(rs.getString("pname"));
				vo.setPrice(rs.getInt("price"));
				array.add(vo);
			} // while
			return array;
		} // list
} // class