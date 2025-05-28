package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
   private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end) {
        PageHelper.startPage(page, pageSize);
        List<Emp> list =empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>)list;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp get(Integer id) {
        Emp emp = empMapper.getId(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.login(emp);
    }
}
