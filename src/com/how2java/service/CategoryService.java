package com.how2java.service;

import java.util.List;

import com.how2java.pojo.Category;

public interface CategoryService {
	
	List<Category> list();
	
	int add(Category category);
	
	void delete(Category category);
	
	Category get(int id);
	
	void update(Category category);
	
	//用于测试事务的方法
	void addTwo();
}
