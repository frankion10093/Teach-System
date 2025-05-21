package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询全部门数据
     * @return
     */
    @GetMapping
    public Result list(){
        log.info("查询全部门数据");
        //这里需要调用一个server端的接口，获取全部部门数据，然后封装成Result对象返回前端
        List<Dept> deptsList = deptService.list();
        return Result.success(deptsList);
    }

    /**
     * 删除部门
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门id={}",id);
        //这里需要一个server端的接口，删除部门数据，然后封装成Result对象返回前端
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 添加部门
     * parm dept
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门{}",dept);
        //这里需要server端的接口，新增部门数据，然后封装成Result对象返回前端
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
     @GetMapping("/{id}")
     public Result GetId(@PathVariable Integer id){
        log.info("查询部门id={}",id);
         // 这里需要server端的接口，根据id查询部门数据，然后封装成Result对象返回前端
        Dept dept = deptService.getById(id);
        return Result.success(dept);
     }


    /**
     * 更新部门
     * @param dept
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info(",更新部门 = {}",dept.getName());
        //这里需要server端的接口，更新部门数据，然后封装成Result对象返回前端
        deptService.update(dept);
        return Result.success();
    }


}
