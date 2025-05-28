package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {

    /**
     * 分页查询
      * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end);

    /**
     * 删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 保存员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据id获取员工
     * @param id
     * @return
     */
    Emp get(Integer id);

    /**
     *
     * @param emp
     * @return
     */
    void update(Emp emp);

    /**
     *
     * @param name
     * @param password
     * @return
     */
    Emp login(Emp emp);



}
