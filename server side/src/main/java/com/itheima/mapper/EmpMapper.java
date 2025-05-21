package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

//    @Select("select count(*)from emp")
//    public Long count();
//
//    @Select("Select * from emp limit #{start} , #{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);

//    @Select("Select * from emp where IF()")

    /**
     * 查询员工列表
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    List<Emp> list(String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate end);

    /**
     * 删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time)"+
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    /**
     * 根据id获取员工
     * @param id
     * @return
     */
    @Select("select *from emp where id = #{id}")
    Emp getId(Integer id);

    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 登录
     * @param emp
     * @return
     */
    @Select("select *from emp where username=#{username} and password =#{password}")
    Emp login(Emp emp);
}
