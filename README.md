# 公司员工管理系统
## 介绍
这是基于黑马程序员Javaweb课程编写的员工管理系统,主要用于新手入门Springboot,涉及
<div align="center">
<a href='https://cn.vuejs.org/'>Vue</a> 
<a href='https://nginx.org/en/'>Nginx</a>
<a href='https:springioprojectsspring-boot'>SpringBoot</a>
<a href='https://mybatis.net.cn/'>Mybatis</a>
<a href='https://jwt.io/'>Jwt</a>
<a href='https://www.mysql.com/cn/'>Mysql</a>
</div>


## 启动
### 1.配置数据库
首先下载<a href='https://dev.mysql.com/downloads/'>***Mysql***</a>数据库,这是免费的社区版本。在初始化数据库时将账户和密码设置为```root```和```1234```,在**CMD**终端中输入```net start mysql ```启动数据库。
### 2.配置数据
用idea自带的数据库工具连接数据库,在数据库控制台输入并执行``create database db01``创建一个名称为db01的数据新数据库,执行```use db01```进入数据库。在拉取的文件夹中找到SQL脚本导入,至此数据准备完毕。
### 3.启动项目
在拉取的文件夹中双击\client side文件夹中的nginx.exe文件运行前端程序,可以在在浏览器中访问localhost:90查看项目是否启动。用idea打开server side文件夹，编译运行即可运行后端代码。

### 4.拓展
项目运行起来后会发现员工图片加载失败,这是由于项目的图片存储在阿里云oss中,但我的代码中没有填写相关配置,可以在server side\src\main\resources\application.yml文件中根据注释修改代码,配置成功后再次启动项目即可。



