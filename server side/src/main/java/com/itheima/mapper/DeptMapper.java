package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门数据
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();
    /**
     * 根据id和删除部门信息
     * @param id
     */
    @Delete("delete from dept where id = #{id} ")
    void delete(Integer id);
    /**
     * 添加部门
     * param dept
     */
    @Insert("insert into  dept(name,create_time,update_time)  values(#{name},#{createTime},#{updateTime}) ")
    void insert(Dept dept);

    /**
     * 根据id修改部门信息
     * @param dept
     */
    @Update("update dept set name = #{name} where id = #{id}")
     public void update(Dept dept);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    public Dept getById(Integer id);
}
