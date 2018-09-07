package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.StuDao;
import com.example.demo.dto.Stu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(value = "学生管理")
@Controller
@RequestMapping("jpa")
public class StuController {
	@Autowired private StuDao dao;
	
	/**列表查询
	 * @param model
	 * @return
	 */
	/*@RequestMapping("list")
	public String findlist(Model model){
		List<Stu> list=dao.findAll();
		model.addAttribute("list", list);
		return "list";
		
	}*/
	/**分页
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String fylist(Model model,String name,@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="3")int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Stu> pages=dao.fylist(pageable);
		model.addAttribute("pages", pages);
		model.addAttribute("name", name);
		return "fylist2";
		
	}
	 /*@RequestMapping("list")
	    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "3")Integer size,Model model){
	        Sort sort = new Sort(Sort.Direction.ASC, "id");
	        if(page<0){
	            page=0;
	        }
	        Pageable pageable =  new PageRequest(page*1-1, size, sort);
	        Page<Stu> pages = dao.findAll(pageable);
	        model.addAttribute("pages",pages);
	        return new ModelAndView("fylist");
	    }*/
	
	/**跳转页面
	 * @return
	 */
	@RequestMapping("toadd")
	public String toadd(){
		return "add";
		
	}
	/**注销
	 * @return
	 */
	@RequestMapping("zx")
	public String zx(HttpSession session){
		session.invalidate();
		return "redirect:/jpa/tologin";
		
	}
	
	/**添加
	 * @param stu
	 * @return
	 */
	@ApiOperation(notes="添加",value="woadd")
	@RequestMapping(value="add",method=RequestMethod.POST)
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
