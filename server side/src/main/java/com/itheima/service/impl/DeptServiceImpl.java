package com.itheima.service.impl;

import com.itheima.anno.MyLog;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @MyLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)//所有异常都会回滚事务
    @Override
    public void delete(Integer id) {
    //我们要保证操作前后数据的一致性，让这两步操作同时成功失败
        deptMapper.delete(id);
//        int i = 1/0;
//       例如这里 1/0 报的异常是RuntimeException,运行时异常,可以回滚事务
//       但如果遇见非运行时异常还是无法回滚,例如空指针异常,所以需要配置rollbackFor(Exception.class)
        empMapper.deleteByDeptId(id);

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }


}
