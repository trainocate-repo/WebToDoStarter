package com.example.demo.app.dept;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.app.dept.Dept;
import com.example.demo.app.dept.DeptDao;

@Repository
public class DeptDaoJdbcImpl implements DeptDao{
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public int count() throws DataAccessException{
		
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM dept", Integer.class);
		
		return count;
	}
	
	@Override
	public int insertOne(Dept dept) throws DataAccessException{
		
		int rowNumber = jdbc.update("INSERT INTO dept(groupName, departmentName, title, recruitment, recruitmentDetail, recruitee, skills, contact) VALUES(?,?,?,?,?,?,?,?)" , dept.getGroupName(), dept.getDepartmentName(), dept.getTitle(), dept.getRecruitment(), dept.getRecruitmentDetail(), dept.getRecruitee(), dept.getSkills(), dept.getContact());
		
		return rowNumber;
	}
	
	@Override
	public Dept selectOne(String departmentName) throws DataAccessException{
		
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM dept WHERE departmentName = ?", departmentName);
		
		Dept dept = new Dept();
		dept.setGroupName((String)map.get("groupName"));
		dept.setDepartmentName((String)map.get("departmentName"));
		dept.setTitle((String)map.get("title"));
		dept.setRecruitment((String)map.get("recruitment"));
		dept.setRecruitmentDetail((String)map.get("recruitmentDetail"));
		dept.setRecruitee((String)map.get("recruitee"));
		dept.setSkills((String)map.get("skills"));
		dept.setContact((String)map.get("contact"));
		
		return dept;
	}
	
	@Override
	public List<Dept> selectMany() throws DataAccessException{
		
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM dept");
		
		List<Dept> deptList = new ArrayList<>();
		
		for(Map<String, Object> map: getList) {
			Dept dept = new Dept();
			dept.setGroupName((String)map.get("groupName"));
			dept.setDepartmentName((String)map.get("departmentName"));
			dept.setTitle((String)map.get("title"));
			dept.setRecruitment((String)map.get("recruitment"));
			dept.setRecruitmentDetail((String)map.get("recruitmentDetail"));
			dept.setRecruitee((String)map.get("recruitee"));
			dept.setSkills((String)map.get("skills"));
			dept.setContact((String)map.get("contact"));
			
			deptList.add(dept);
		}
		
		return deptList;
	}
	
	@Override
	public int updateOne(Dept dept) throws DataAccessException{
		
		int rowNumber = jdbc.update("UPDATE dept SET groupName = ?, title = ?, recruitment = ?, recruitmentDetail = ?, recruitee = ?, skills = ?, contact = ? WHERE departmentName = ? ",  
														dept.getGroupName(), dept.getTitle(), dept.getRecruitment(), dept.getRecruitmentDetail(), dept.getRecruitee(), dept.getSkills(), dept.getContact(), dept.getDepartmentName() );

		
		return rowNumber;
		
	}
	
	@Override
	public int deleteOne(String departmentName) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM dept WHERE departmentName = ? ", departmentName);
		
		return rowNumber;
		
	}

}
