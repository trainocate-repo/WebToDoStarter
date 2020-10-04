package com.example.demo.app.dept;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class DeptRegisterController {
	
	@Autowired
	private DeptRegisterService deptRegisterService;
	

	@GetMapping("/deptRegister")
	public String getDeptRegister(@ModelAttribute DeptRegisterForm form, Model model) {
		return "deptRegister";
	}
	
	

	@PostMapping(value = "/deptRegister", params="register")
	public String postDeptRegister(@ModelAttribute DeptRegisterForm form, BindingResult bindingResult, Model model) {
		
		System.out.println(form);
		
		Dept dept = new Dept();
		
		dept.setGroupName(form.getGroupName());
		dept.setDepartmentName(form.getDepartmentName());
		dept.setTitle(form.getTitle());
		dept.setRecruitment(form.getRecruitment());
		dept.setRecruitmentDetail(form.getRecruitmentDetail());
		dept.setRecruitee(form.getRecruitee());
		dept.setSkills(form.getSkills());
		dept.setContact(form.getContact());
		
		boolean result = deptRegisterService.insert(dept);
		
		if(result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}	
		return "redirect:/deptRegister";
	}
	
	
	@PostMapping(value="/deptDetail", params="toDeptList")
	public String getDeptList2(Model model) {
		model.addAttribute("contents", "deptList :: deptList_contents");
		
		List<Dept> deptList = deptRegisterService.selectMany();
		
		model.addAttribute("deptList",deptList);
		
		int count = deptRegisterService.count();
		model.addAttribute("deptListCount", count);
		
		return "JobList";
				
	}
	
	@PostMapping(value="/deptRegister", params="toDeptList")
	@GetMapping("/JobList")
	public String getDeptList(Model model) {
		model.addAttribute("contents", "deptList :: deptList_contents");
		
		List<Dept> deptList = deptRegisterService.selectMany();
		
		model.addAttribute("deptList",deptList);
		
		int count = deptRegisterService.count();
		model.addAttribute("deptListCount", count);
		
		return "JobList";
				
	}


	@GetMapping("/deptDetail/{departmentName}")
	public String getDepartmentDetail(@ModelAttribute DeptRegisterForm form, Model model, @PathVariable("departmentName") String departmentName) {
		
		model.addAttribute("contents", "deptDetail :: deptDetail_contents");
		
		if(departmentName != null && departmentName.length() > 0) {
			
			Dept dept = deptRegisterService.selectOne(departmentName);

			
			model.addAttribute("dept", dept);
		}
		
			return "deptDetail";
		}
	
	@GetMapping("/deptEdit/{departmentName}")
	public String getDepartmentEdit(@ModelAttribute DeptRegisterForm form, Model model, @PathVariable("departmentName") String departmentName) {
		
		System.out.println("departmentName = " + departmentName);
		
		model.addAttribute("contents", "deptEdit :: deptEdit_contents");
		
		if(departmentName != null && departmentName.length() > 0) {
			
			Dept dept = deptRegisterService.selectOne(departmentName);
			
			form.setDepartmentName(dept.getDepartmentName());
			form.setGroupName(dept.getGroupName());
			form.setTitle(dept.getTitle());
			form.setRecruitment(dept.getRecruitment());
			form.setRecruitmentDetail(dept.getRecruitmentDetail());
			form.setRecruitee(dept.getRecruitee());
			form.setSkills(dept.getSkills());
			form.setContact(dept.getContact());
			
			model.addAttribute("deptRegisterForm", form);
		}
		
			return "deptEdit";
		}
	
	@PostMapping(value = "/deptEdit", params = "update")
	public String postDeptEditUpdate(@ModelAttribute DeptRegisterForm form, Model model) {
		System.out.println("更新ボタンの処理");
		
		Dept dept = new Dept();
		
		dept.setDepartmentName(form.getDepartmentName());
		dept.setGroupName(form.getGroupName());
		dept.setTitle(form.getTitle());
		dept.setRecruitment(form.getRecruitment());
		dept.setRecruitmentDetail(form.getRecruitmentDetail());
		dept.setRecruitee(form.getRecruitee());
		dept.setSkills(form.getSkills());
		dept.setContact(form.getContact());
		
		System.out.println(form.getGroupName());
		System.out.println(dept.getGroupName());
		
		boolean result = deptRegisterService.updateOne(dept);
		
		if(result == true) {
			model.addAttribute("result", "更新成功");
		} else {
			model.addAttribute("result", "更新失敗");
		}
		
		return getDeptList(model);
	}

	@PostMapping(value = "/deptEdit", params = "delete")
	public String postDeptEditDelete(@ModelAttribute DeptRegisterForm form, Model model) {
		System.out.println("削除ボタンの処理");
		boolean result = deptRegisterService.deleteOne(form.getDepartmentName());
		if(result == true) {
			model.addAttribute("result", "削除成功");
		} else {
			model.addAttribute("result","削除失敗");
		}
		
		return getDeptList(model);
	}
	
}

