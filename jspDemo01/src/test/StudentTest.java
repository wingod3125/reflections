package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.StudentBiz;
import biz.impl.StudentBizImpl;
import entity.Grade;
import entity.Student;

public class StudentTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		Student stu = new Student();
		stu.setStudentName("xiaoye");	
		stu.setSex("ÄĞ");
		stu.setTelephone("13295959595");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("1993-4-22");
		stu.setBirthday(d);
		stu.setAddress("ÖêÖŞ");
		stu.setEmail("xiaoye@qq.com");
		Grade g = new Grade();
		g.setGid(1);
		stu.setGrade(g);	
		StudentBiz biz = new StudentBizImpl();
		if(biz.add(stu)>0){
			System.out.println("Ìí¼Ó³É¹¦");
		}
	}

}
