package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.itheima.utils.JwtUtils.generateJwt;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    /**
     * 登录接口
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("login emp: {}", emp);
        Emp e =empService.login(emp);

        //如果登录成功，生成并下发令牌
        if(e != null){
            //生成令牌
            HashMap<String ,Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name",e.getName());
            claims.put("username", e.getUsername());
            String jwt = generateJwt(claims);
            return Result.success(jwt);
        }

        //登录失败

        return Result.error("用户名或密码错误");
    }

}
