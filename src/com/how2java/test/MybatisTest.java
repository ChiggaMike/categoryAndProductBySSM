package com.how2java.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryService categoryService;
	
//	@Test
	public void testAdd(){
		for (int i = 0; i < 51; i++){
			Category category = new Category();
			category.setName("new Category");
			categoryMapper.add(category);
		}
	}
//	@Test
	public void testDelete(){
		for (int i = 117; i < 158; i++) {
			categoryMapper.delete(i);
		}
	}
	
	@Test
	public void testAddTwo(){
		categoryService.addTwo();
	}

	
//	@Test
//	public void testList(){
//		Page p = new Page();
//		p.setStart(2);
//		p.setCount(3);
//		List<Category> cs = categoryMapper.list(p);
//		for (Category each:cs){
//			System.out.println(each.getName());
//		}
//	}

}
