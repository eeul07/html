package ex01;

import java.sql.*;
import java.util.*;

public class BoardDAO {
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
	
	// 데이터베이스 목록 출력
	public ArrayList<BoardVO> list()throws Exception{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		String sql="select * from tbl_board";
		PreparedStatement ps=con().prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			BoardVO vo=new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWdate(rs.getTimestamp("wdate"));
				list.add(vo);
		}
		return list;
	}
	
	// 데이터 입력
	public void insert(BoardVO vo)throws Exception{
		String sql="insert into tbl_board(bno, title, content, wdate) values(seq_board.nextval, ?, ?, sysdate)";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setString(1, vo.getTitle());
		ps.setString(2, vo.getContent());
		ps.execute();
	}
	
	// 데이터 읽기
	public BoardVO read(int bno)throws Exception{
		String sql="select * from tbl_board where bno=?";
		PreparedStatement ps=con().prepareStatement(sql);
		ps.setInt(1, bno);
		ResultSet rs=ps.executeQuery();
		BoardVO vo=new BoardVO();
		if(rs.next()) {
			vo.setBno(rs.getInt("bno"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
		}
		return vo;
	}
} // class