package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StuDao;
import com.example.demo.dto.Stu;

@Controller
@RequestMapping("jpa")
public class StuController {
	@Autowired private StuDao dao;
	
	/**列表查询
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String findlist(Model model){
		List<Stu> list=dao.findAll();
		model.addAttribute("list", list);
		return "list";
		
	}
	
	/**跳转页面
	 * @return
	 */
	@RequestMapping("toadd")
	public String toadd(){
		return "add";
		
	}
	
	/**添加
	 * @param stu
	 * @return
	 */
	@RequestMapping("add")
	public String add(Stu stu){
		dao.save(stu);
		
		return "redirect:/jpa/list";
		
	}
	/**删除
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Long id){
		dao.deleteById(id);
		
		return "redirect:/jpa/list";
		
	}
	/**去修改
	 * @param id
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toupdate(Long id,Model model){
		Stu stu=dao.findAllById(id);
		model.addAttribute("stu", stu);
		return "update";
		
	}
	/**修改
	 * @param stu
	 * @return
	 */
	@RequestMapping("update")
	public String update(Stu stu){
		dao.saveAndFlush(stu);
		
		return "redirect:/jpa/list";
		
	}
	/**去登录
	 * @return
	 */
	@RequestMapping("tologin")
	public String tologin(){
		
		
		return "login";
		
	}
	
	/**登录
	 * @param stu
	 * @return
	 */
	@RequestMapping("login")
	public String login(Stu stu,HttpSession session){
		List<Stu> list=dao.findByNameAndPwd(stu.getName(),stu.getPwd());
		if (list!=null&&list.size()>0) {
			Stu stu2 = list.get(0);
			stu2.setPwd(null);
			session.setAttribute("stu2", stu2);
			return "redirect:/jpa/list";
		}
		return "sb";
		
	}
}
