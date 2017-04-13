package entity;

import java.util.Date;
/**
 * 实体类，与数据表对应
 * @author Administrator
 *
 */
public class Result {
	private Student student;
	private Subject subject;
	private Date examDate;
	private int studentResult;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public int getStudentResult() {
		return studentResult;
	}
	public void setStudentResult(int studentResult) {
		this.studentResult = studentResult;
	}
	
}
