# SpringBoot电脑商城项目

## 1.项目分析

1.项目功能：登录、注册、热销产品、用户管理（密码、个人信息、收获地址）、购物车（展示、增加、删除）、订单模块。

2.开发顺序：注册、登录、用户管理、购物车、商品、订单模块。

3.某一个模块的开发：

- 持久层开发：依据前端页面的设置规划相关的SQL语句，以及进行配置
- 业务层开发：核心功能控制、业务操作以及异常的处理
- 控制层开发：接受请求、处理响应
- 前端开发：JS、JQuery、AJAX这些技术来连接后台

## 2.羡慕的开发环境

1.JDK：17

2.Maven：idea自带

3.数据库：MySQL

4.开发的平台：idea开发

## 3.搭建项目

1.项目名称：store（表示商城的意思）

2.结构：com.laity.store

3.资源文件：resource文件夹下（static、templates）

4.单元测试：test.com.laity.store

5.在properties文件中配置数据库的连接信息

```
spring.datasource.url=jdbc:mysql://localhost:3306/store?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
```

6.创建一个store数据库

7.测试连接：

- 启动SpringBoot主类，是否有对应的Spring图形输出
- 在单元测试类中测试数据库的连接是否可以正常加载

8.访问项目的静态资源是否可以正常的加载，将所有的静态资源复制到static目录下。

