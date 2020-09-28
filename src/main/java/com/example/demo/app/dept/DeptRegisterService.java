package com.example.demo.app.dept;

import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.app.dept.DeptDao;

@Service
public class DeptRegisterService {

	@Autowired
	DeptDao dao;
	
	public boolean insert(Dept dept) {
		
		int rowNumber = dao.insertOne(dept);
		
		boolean result = false;
		
		if(rowNumber > 0) {
			result = true;
		}
		
		return result;
	}
	
	public int count() {
		return dao.count();
	}
	
	public List<Dept> selectMany(){
		return dao.selectMany();
	}
	
	public Dept selectOne(String departmentName) {
		return dao.selectOne(departmentName);
	}
	
	public boolean updateOne(Dept dept) {
		int rowNumber = dao.updateOne(dept);
		boolean result = false;
		
		System.out.println("ここまで入ってる");
		System.out.println(dept.getGroupName());
		
		if(rowNumber > 0) {
			result = true;
			System.out.println("trueです");
			
		}
		System.out.println(rowNumber);
		return result;
	}
	
	public boolean deleteOne(String departmentName) {
		int rowNumber = dao.deleteOne(departmentName);
		
		boolean result = false;
		
		if(rowNumber > 0) {
			result = true;
		}
		return result;
	}
	
	/*
	@Autowired
	private DeptRepository deptRepository;
	
	public Employee findOne(int id) {
		Map<String, Object> map = deptRepository.findOne(id);
		
		int employeeId = (Integer)map.get("employee_id");
		String employeeName = (String)map.get("employee_name");
		int age = (Integer)map.get("age");
		
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(employeeName);
		employee.setAge(age);
		
		return employee;
	}
*/
}
