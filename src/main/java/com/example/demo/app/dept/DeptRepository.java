package com.example.demo.app.dept;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeptRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> deptRegister(DeptRegisterForm deptRegisterForm){
		String query = "SELECT employee_id, employee_name, age FROM employee WHERE employee_id=?";
		
		//String query = "INSERT ";
		
		Map<String, Object> dept= jdbcTemplate.queryForMap(query, 3);
		
		return dept;
	
	}

}
