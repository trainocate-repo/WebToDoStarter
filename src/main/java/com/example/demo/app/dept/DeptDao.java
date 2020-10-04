package com.example.demo.app.dept;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.demo.app.dept.Dept;

public interface DeptDao {
	
	public int count() throws DataAccessException;
	
	public int insertOne(Dept dept) throws DataAccessException;
	
	public Dept selectOne(String departmentName) throws DataAccessException;
	
	public List<Dept> selectMany() throws DataAccessException;
	
	public int updateOne(Dept dept) throws DataAccessException;
	
	public int deleteOne(String departmentName) throws DataAccessException;

}
