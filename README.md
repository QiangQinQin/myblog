# 基于SSM的博客系统

## 系统实现的功能点

### 1、用户权限管理：
普通的用户(游客)只能浏览文章、管理员用户可以发布文章、文章管理
### 2、博客列表展示：
文章按照发布时间顺序(按照时间倒序)展示 ：博客类别、标签、博客名称、作者名、发布时间、阅读数量、博客的内容概括（显示不完的内容用省略号）
### 3、博客详情页面：
博客名称、作者、时间、博客内容、标签
### 4、博客后台列表：
博客检索(类别、标签、博客名称)、博客列表(博客id、博客类别、标签、时间)、博客操作
### 5、新增博客功能：
支持富文本编辑：可以调整大小、样式、图片、缩进等
##  数据库设计
- 用户表：账号id、账号名称、账号密码
- 博客表：博客id、博客名称、博客内容、发布时间、阅读量、类别id
- 博客/标签对应表：博客id、标签的id
- 标签表：标签id、标签名称（博客和标签：一对多：一个博客可以对应多个标签）
- 类别表：类别ID、类别名称（博客和类别：一对一：一个博客对应一个类别）
- 建表的sql语句：https://github.com/QiangQinQin/myblog/blob/master/table_sql.txt
## 博客涉及技术
### 服务端技术
- 核心框架：Spring:4.1.0.RELEASE
- web框架：SpringMVC：4.1.0.RELEASE
- 持久层框架：Mybatis 3.2.4
- 数据库连接池：阿里druid：0.2.6
- 数据库：MySQL5.XX
- JSON数据处理：谷歌json 2.3
### 前端技术
- jsp
- Ajax
- 前端框架:bootstrap
- 富文本编辑器:百度UEditor