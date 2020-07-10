package ex0416;

import java.sql.*;
import java.util.*;

public class ProductDAO {
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
	
	// 목록출력 메서드
	public ArrayList<ProductVO> list()throws Exception{
		ArrayList<ProductVO> list=new ArrayList<ProductVO>();
		String sql="select * from tbl_product";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ProductVO vo=new ProductVO();
			vo.setPno(rs.getString("pno"));
			vo.setPname(rs.getString("pname"));
			vo.setPrice(rs.getInt("price"));
			list.add(vo);
		}
		return list;
	}
	
	// 데이터 입력 메서드
	public void insert(ProductVO vo)throws Exception{
		String sql="insert into tbl_product(pno, pname, price) values(sql_pno.nextval, ?, ?)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getPname());
		ps.setInt(2, vo.getPrice());
		ps.execute();
	}
	
	// 데이터 삭제 메서드
			public void delete(String pno)throws Exception{
				String sql="delete from tbl_product where pno=?";
				PreparedStatement ps=con().prepareStatement(sql);
				ps.setString(1, pno);
				ps.execute();
			}
} // class