package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    public List<Dept> list();
    /**
     * 删除部门
     */
    public void delete(Integer id);

    /**
     * 添加部门
     * param dept
     */
    public void add(Dept dept);

    /**
     * 修改部门
     * param dept
     */
    public void update(Dept dept);
    /**
     * 根据id查询部门
     */
    public Dept getById(Integer id);
}
