package com.how2java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;
import com.how2java.util.Page;

@Controller
@RequestMapping("")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public ModelAndView listCategory(Page page){
		ModelAndView mav = new ModelAndView();
		PageHelper.offsetPage(page.getStart(), page.getCount());
		List<Category> cs = categoryService.list();
		int total = (int) new PageInfo<>(cs).getTotal();
		
		page.calculateLast(total);
		//放入转发参数
		mav.addObject("cs", cs);
		//放入jsp路径
		mav.setViewName("listCategory");
		return mav;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public ModelAndView addCategory(Category category){
		categoryService.add(category);
		ModelAndView mav = new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.DELETE)
	public ModelAndView deleteCategory(Category category){
		categoryService.delete(category);
		ModelAndView mav = new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public ModelAndView editCategory(Category category){
		Category c = categoryService.get(category.getId());
		ModelAndView mav = new ModelAndView("editCategory");
		mav.addObject("c", c);
		return mav;
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.PUT)
	public ModelAndView updateCategory(Category category){
		categoryService.update(category);
		ModelAndView mav = new ModelAndView("redirect:/listCategory");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/submitCategory")
	public String submitCategory(@RequestBody Category category){
		System.out.println("SSM接受到浏览器提交的JSON，并转换为Category对象：" + category);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/getOneCategory")
	public String getOneCategory(){
		Category c = new Category();
		c.setId(100);
		c.setName("第100个分类");
		JSONObject json = new JSONObject();
		json.put("category", JSONObject.toJSON(c));
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/getManyCategory")
	public String getManyCategory(){
		List<Category> cs = new ArrayList<>();
		for (int i = 0; i < 10; i++){
			Category c = new Category();
			c.setId(i);
			c.setName("分类名称：" + i);
			cs.add(c);
		}
		return JSONObject.toJSON(cs).toString();
	}
}
