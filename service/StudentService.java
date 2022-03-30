package lec14_jdbc_jsp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import lec14_jdbc_jsp.dao.StudentDao;
import lec14_jdbc_jsp.jdbc.ConnectionPool;
import lec14_jdbc_jsp.model.Student;

public class StudentService {
	private static StudentService instance = new StudentService();
	private StudentDao dao = StudentDao.getInstance();
	private ConnectionPool cp = ConnectionPool.getInstance();
	
	private StudentService() {}
	
	public static StudentService getInstance() {
		if(instance == null) {
			instance = new StudentService();
		}
		return instance;
	}
	
	// 회원목록 조회 (SELECT)
	public ArrayList<Student> stuList(){
		Connection conn = cp.getConnection();
		
		try {
			return dao.stuList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		
		return new ArrayList<Student>();
	}
	
	// 회원추가 (INSERT)
	public int insertStu(Student student) {
		Connection conn = cp.getConnection();
		
		try {
			return dao.insertStu(conn, student);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) cp.releaseConnection(conn);
		}
		
		return 0;
	}
	
	
	
	
}
