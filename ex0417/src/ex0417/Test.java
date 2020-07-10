package ex0417;

import java.util.*;

public class Test {

	public static void main(String[] args)throws Exception {
		
	} // main method
		
		public static void list()throws Exception{
			// list() 메서드 테스트
			GradeDAO dao=new GradeDAO();
			ArrayList<GradeVO> list=dao.list();
			for(int i=0; i<list.size(); i++) {
				GradeVO vo=list.get(i);
				System.out.println(vo.getGno() + vo.getGname() + vo.getKor() + vo.getEng() + vo.getMat());
			}
		} // list
		
		public static void insert()throws Exception{
			// insert() 메서트 테스트
			GradeVO vo=new GradeVO();
			GradeDAO dao=new GradeDAO();
			vo.setGname("성춘향");
			vo.setKor(88);
			vo.setEng(77);
			vo.setMat(66);
			dao.insert(vo);
		} // insert
		
		public static void update()throws Exception{
			// update() 메서드 테스트
			GradeVO vo=new GradeVO();
			GradeDAO dao=new GradeDAO();
			vo=new GradeVO();
			vo.setGno(6);
			vo.setKor(68);
			vo.setEng(70);
			vo.setMat(90);
			dao.update(vo);
		} // update
		
		public static void delete()throws Exception{
			// delete() 메서드 테스트
			GradeDAO dao=new GradeDAO();
			dao.delete(6);
		} // delete
		
} // class