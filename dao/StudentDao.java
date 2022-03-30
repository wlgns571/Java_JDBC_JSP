package lec14_jdbc_jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lec14_jdbc_jsp.model.Student;

public class StudentDao {
	
	private static StudentDao instance = new StudentDao();
	
	private StudentDao() {}
	
	public static StudentDao getInstance() {
		if(instance == null) {
			instance = new StudentDao();
		}
		return instance;
	}
	
	// 회원목록 조회 (SELECT)
	public ArrayList<Student> stuList(Connection conn) throws SQLException{
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT					");
		query.append("		stu_id    AS id		");
		query.append("	  , stu_name  AS name	");
		query.append("	  , stu_score AS score	");
		query.append("FROM						");
		query.append("		students			");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Student> result = new ArrayList<Student>();
		
		while(rs.next()) {
			Student temp = new Student();
			
			temp.setStuId(rs.getInt("id"));
			temp.setStuName(rs.getString("name"));
			temp.setStuScore(rs.getInt("score"));
			
			result.add(temp);
		}
		
		if(ps!=null)ps.close();
		if(rs!=null)rs.close();
		
		return result;
	}
	
	
	// 회원 추가 (INSERT)
	public int insertStu(Connection conn, Student student) throws SQLException {
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO				");
		query.append("			students		");
		query.append("VALUES (					");
		query.append("		  stu_seq.NEXTVAL	");
		query.append("		, ?					");
		query.append("		, ?					");
		query.append("		)					");
		
		PreparedStatement ps = conn.prepareStatement(query.toString());
		
		int idx = 1;
		ps.setString(idx++, student.getStuName());
		ps.setInt(idx++, student.getStuScore());
		
		int cnt = ps.executeUpdate();
		
		if(ps!=null)ps.close();
		
		return cnt;
	}
	
	
	
	
	
	
	
}
