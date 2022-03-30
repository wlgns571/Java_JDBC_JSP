package lec14_jdbc_jsp;

import java.util.ArrayList;

import lec14_jdbc_jsp.model.Student;
import lec14_jdbc_jsp.service.StudentService;

public class StudentMain {

	public static void main(String[] args) {
		StudentService service = StudentService.getInstance();
		
		// 학생 목록 조회
		ArrayList<Student> stuList = service.stuList();
		
		for(Student st : stuList) {
			System.out.println(st.toString());
		}
		
		
	}

}
