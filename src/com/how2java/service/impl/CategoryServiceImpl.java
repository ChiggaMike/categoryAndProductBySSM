package com.how2java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.how2java.mapper.CategoryMapper;
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;
	
	public List<Category> list(){
		return categoryMapper.list();
	};
	
	public int add(Category category){
		return categoryMapper.add(category);
	}

	public void delete(Category category) {
		categoryMapper.delete(category.getId());
	}
	
	public Category get(int id){
		return categoryMapper.get(id);
	}
	
	public void update(Category category){
		categoryMapper.update(category);
	}
	
	//���ڲ�������ķ���(ʹ��ע�ⷽʽ)
	@Transactional(propagation=Propagation.REQUIRED, rollbackForClassName="Exception")
	public void addTwo(){
		Category c1 = new Category();
		c1.setName("�̵�����");
		categoryMapper.add(c1);
		
		Category c2 = new Category();
		c2.setName("���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���,"
				+ "���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���,���ֳ���Ӧ�ֶηŲ���");
		categoryMapper.add(c2);
	}
	
}
