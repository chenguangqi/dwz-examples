# DWZ框架第8-10天学习指南：开发综合管理系统

## 学习目标

通过这三天的学习和实践，您将：
1. 综合运用前七天学到的所有DWZ框架知识
2. 开发一个完整的员工管理系统
3. 掌握实际项目开发的流程和方法
4. 提升解决实际问题的能力

## 项目概述

我们将开发一个员工管理系统，包含以下主要功能模块：
1. 用户登录和权限管理
2. 员工信息管理（增删改查）
3. 部门管理
4. 职位管理
5. 数据统计和报表展示

## 第8天：需求分析和设计

### 任务1：需求分析

#### 系统功能需求
1. **用户管理**
   - 用户登录/登出
   - 权限控制
   - 用户信息维护

2. **员工管理**
   - 员工信息录入、修改、删除、查询
   - 员工信息包括：姓名、性别、出生日期、身份证号、联系电话、邮箱、部门、职位、入职日期等

3. **部门管理**
   - 部门信息维护（增删改查）
   - 部门层级结构展示

4. **职位管理**
   - 职位信息维护（增删改查）

5. **数据统计**
   - 员工统计报表
   - 部门人员分布图

#### 非功能需求
1. 界面美观、操作简便
2. 响应速度快，性能良好
3. 数据安全，防止未授权访问
4. 支持主流浏览器

### 任务2：系统设计

#### 技术选型
- 前端框架：DWZ JUI
- 后端语言：PHP（模拟数据）
- 数据库：JSON文件模拟（实际项目中使用MySQL等）

#### 系统架构设计
```
前端层 (DWZ JUI)
    |
数据交互层 (Ajax)
    |
后端逻辑层 (PHP)
    |
数据存储层 (JSON文件)
```

#### 数据库设计（模拟）
1. **用户表(users.json)**
   - id: 用户ID
   - username: 用户名
   - password: 密码
   - role: 角色

2. **员工表(employees.json)**
   - id: 员工ID
   - name: 姓名
   - gender: 性别
   - birthDate: 出生日期
   - idCard: 身份证号
   - phone: 联系电话
   - email: 邮箱
   - departmentId: 部门ID
   - positionId: 职位ID
   - hireDate: 入职日期

3. **部门表(departments.json)**
   - id: 部门ID
   - name: 部门名称
   - parentId: 上级部门ID

4. **职位表(positions.json)**
   - id: 职位ID
   - name: 职位名称

#### 界面设计
1. 登录页面
2. 系统主界面（包含菜单、导航等）
3. 员工管理页面
4. 部门管理页面
5. 职位管理页面
6. 统计报表页面

### 实践练习

今天我们主要进行需求分析和系统设计，为后续开发做好准备。

创建项目目录结构：
```
employee_system/
├── index.html          # 系统入口页面
├── login.html          # 登录页面
├── main.html           # 系统主页面
├── data/               # 模拟数据文件
│   ├── users.json
│   ├── employees.json
│   ├── departments.json
│   └── positions.json
├── pages/              # 各功能页面
│   ├── employee_list.html
│   ├── employee_form.html
│   ├── department_list.html
│   ├── department_form.html
│   ├── position_list.html
│   └── position_form.html
└── js/                 # 自定义JavaScript文件
    └── app.js
```

## 第9天：功能开发和实现

### 任务1：搭建基础框架

#### 创建登录页面
login.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理系统 - 登录</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/login.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ui.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
        DWZ.init("./dwz_jui-master/dwz.frag.xml", {
            statusCode:{ok:200, error:300, timeout:301}, 
            keys: {statusCode:"statusCode", message:"message"}
        });
    });
    </script>
</head>
<body>
    <div id="login">
        <div class="login_header">
            <div class="login_logo">
                <img src="./dwz_jui-master/themes/default/images/logo.png" />
            </div>
            <div class="login_headerContent">
                <h1>员工管理系统</h1>
                <div class="navList">
                    <ul>
                        <li><a href="#">了解系统</a></li>
                        <li><a href="#">反馈问题</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="login_main">
            <div class="login_main_left">
                <img src="./dwz_jui-master/themes/default/images/login_banner.jpg" />
            </div>
            <div class="login_main_right">
                <form method="post" action="main.html">
                    <div class="login_form">
                        <div class="login_row">
                            <label>用户名:</label>
                            <input type="text" name="username" class="required" />
                        </div>
                        <div class="login_row">
                            <label>密码:</label>
                            <input type="password" name="password" class="required" />
                        </div>
                        <div class="login_row">
                            <label>验证码:</label>
                            <input type="text" name="captcha" class="required" />
                            <img src="./dwz_jui-master/themes/default/images/captcha.jpg" />
                        </div>
                        <div class="login_row">
                            <button type="submit">登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="login_footer">
            Copyright &copy; 2023 员工管理系统
        </div>
    </div>
</body>
</html>
```

#### 创建系统主页面
main.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理系统</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.tree.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.database.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.theme.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.alertMsg.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.contextmenu.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ui.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(function(){
        DWZ.init("./dwz_jui-master/dwz.frag.xml", {
            statusCode:{ok:200, error:300, timeout:301}, 
            keys: {statusCode:"statusCode", message:"message"},
            callback:function(){
                initEnv();
            }
        });
    });
    </script>
</head>
<body scroll="no">
    <div id="layout">
        <div id="header">
            <div class="headerNav">
                <a class="logo" href="#">标志</a>
                <ul class="nav">
                    <li><a href="login.html">退出</a></li>
                </ul>
                <ul class="themeList" id="themeList">
                    <li theme="default"><div class="selected">蓝色</div></li>
                    <li theme="green"><div>绿色</div></li>
                    <li theme="purple"><div>紫色</div></li>
                    <li theme="silver"><div>银色</div></li>
                    <li theme="azure"><div>天蓝</div></li>
                </ul>
            </div>
        </div>
        
        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse"><div></div></div>
                </div>
            </div>
            <div id="sidebar">
                <div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
                <div class="accordion" fillSpace="sidebar">
                    <div class="accordionHeader">
                        <h2><span>Folder</span>系统管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="pages/user_list.html" target="navTab" rel="users">用户管理</a></li>
                        </ul>
                    </div>
                    <div class="accordionHeader">
                        <h2><span>Folder</span>人事管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="pages/employee_list.html" target="navTab" rel="employees">员工管理</a></li>
                            <li><a href="pages/department_list.html" target="navTab" rel="departments">部门管理</a></li>
                            <li><a href="pages/position_list.html" target="navTab" rel="positions">职位管理</a></li>
                        </ul>
                    </div>
                    <div class="accordionHeader">
                        <h2><span>Folder</span>统计报表</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="pages/report_chart.html" target="navTab" rel="reports">统计图表</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navTab-tab">
                            <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                        </ul>
                    </div>
                    <div class="tabsLeft">left</div>
                    <div class="tabsRight">right</div>
                    <div class="tabsMore">more</div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">我的主页</a></li>
                </ul>
                <div class="navTab-panel tabsPageContent layoutBox">
                    <div class="page unitBox">
                        <div class="accountInfo">
                            <div class="alertInfo">
                                <h2><a href="#">员工管理系统</a></h2>
                            </div>
                            <div class="right">
                                <p>当前版本：v1.0.0</p>
                            </div>
                        </div>
                        <div class="pageContent">
                            <div style="float:left; width:400px;">
                                <div class="panel">
                                    <h1>系统说明</h1>
                                    <div>
                                        这是一个基于DWZ JUI框架开发的员工管理系统示例程序。
                                    </div>
                                </div>
                            </div>
                            
                            <div style="float:right; width:400px;">
                                <div class="panel">
                                    <h1>快捷操作</h1>
                                    <div>
                                        <a class="button" href="pages/employee_form.html" target="navTab" rel="employee_add"><span>新增员工</span></a>
                                        <a class="button" href="pages/department_form.html" target="navTab" rel="department_add"><span>新增部门</span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

### 任务2：创建模拟数据文件

#### 创建数据目录和文件
data/users.json:
```json
[
  {
    "id": 1,
    "username": "admin",
    "password": "admin",
    "role": "administrator"
  },
  {
    "id": 2,
    "username": "user",
    "password": "user",
    "role": "user"
  }
]
```

data/employees.json:
```json
[
  {
    "id": 1,
    "name": "张三",
    "gender": "男",
    "birthDate": "1990-01-01",
    "idCard": "110101199001011234",
    "phone": "13800138001",
    "email": "zhangsan@example.com",
    "departmentId": 1,
    "positionId": 1,
    "hireDate": "2020-01-01"
  },
  {
    "id": 2,
    "name": "李四",
    "gender": "女",
    "birthDate": "1992-05-15",
    "idCard": "110101199205151234",
    "phone": "13800138002",
    "email": "lisi@example.com",
    "departmentId": 2,
    "positionId": 2,
    "hireDate": "2021-03-10"
  }
]
```

data/departments.json:
```json
[
  {
    "id": 1,
    "name": "技术部",
    "parentId": 0
  },
  {
    "id": 2,
    "name": "销售部",
    "parentId": 0
  },
  {
    "id": 3,
    "name": "人事部",
    "parentId": 0
  }
]
```

data/positions.json:
```json
[
  {
    "id": 1,
    "name": "程序员"
  },
  {
    "id": 2,
    "name": "销售经理"
  },
  {
    "id": 3,
    "name": "人事专员"
  }
]
```

## 第10天：测试和完善

### 任务1：完善各功能模块

#### 创建员工管理页面
pages/employee_list.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageHeader">
            <form onsubmit="return navTabSearch(this);" action="employee_list.html" method="post">
                <div class="searchBar">
                    <table class="searchContent">
                        <tr>
                            <td>
                                姓名：<input type="text" name="name" />
                            </td>
                            <td>
                                部门：<select name="departmentId">
                                    <option value="">所有</option>
                                    <option value="1">技术部</option>
                                    <option value="2">销售部</option>
                                    <option value="3">人事部</option>
                                </select>
                            </td>
                            <td>
                                <button type="submit">检索</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div class="pageContent">
            <div class="panelBar">
                <ul class="toolBar">
                    <li><a class="add" href="employee_form.html" target="navTab" rel="employee_add"><span>添加</span></a></li>
                    <li><a class="delete" href="delete_employee.html" target="ajaxTodo" title="确定要删除该员工吗?"><span>删除</span></a></li>
                    <li><a class="edit" href="employee_form.html?id={id}" target="navTab" rel="employee_edit"><span>修改</span></a></li>
                    <li class="line">line</li>
                    <li><a class="icon" href="employee_excel.html" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
                </ul>
            </div>
            <table class="table" width="100%" layoutH="138">
                <thead>
                    <tr>
                        <th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                        <th orderField="id" class="asc">编号</th>
                        <th orderField="name">姓名</th>
                        <th orderField="gender">性别</th>
                        <th orderField="department">部门</th>
                        <th orderField="position">职位</th>
                        <th orderField="hireDate">入职日期</th>
                        <th width="80">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr target="sid_user" rel="1">
                        <td><input name="ids" value="1" type="checkbox"></td>
                        <td>1</td>
                        <td>张三</td>
                        <td>男</td>
                        <td>技术部</td>
                        <td>程序员</td>
                        <td>2020-01-01</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_employee.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="employee_form.html?id=1" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                    <tr target="sid_user" rel="2">
                        <td><input name="ids" value="2" type="checkbox"></td>
                        <td>2</td>
                        <td>李四</td>
                        <td>女</td>
                        <td>销售部</td>
                        <td>销售经理</td>
                        <td>2021-03-10</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_employee.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="employee_form.html?id=2" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="panelBar">
                <div class="pages">
                    <span>显示</span>
                    <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                        <option value="20">20</option>
                        <option value="50" selected>50</option>
                        <option value="100">100</option>
                        <option value="200">200</option>
                    </select>
                    <span>条，共<label class="totalCount">2</label>条</span>
                </div>
                <div class="pagination" targetType="navTab" totalCount="2" numPerPage="50" pageNumShown="10" currentPage="1"></div>
            </div>
        </div>
    </div>
</body>
</html>
```

pages/employee_form.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工信息</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageContent">
            <form method="post" action="save_employee.html" class="pageForm required-validate" target="navTabTodo">
                <div class="pageFormContent" layoutH="56">
                    <div class="unit">
                        <label>姓名：</label>
                        <input type="text" class="required" name="name">
                    </div>
                    <div class="unit">
                        <label>性别：</label>
                        <label><input type="radio" name="gender" value="男" checked>男</label>
                        <label><input type="radio" name="gender" value="女">女</label>
                    </div>
                    <div class="unit">
                        <label>出生日期：</label>
                        <input type="text" name="birthDate" class="date required" dateFmt="yyyy-MM-dd" />
                    </div>
                    <div class="unit">
                        <label>身份证号：</label>
                        <input type="text" class="required" name="idCard">
                    </div>
                    <div class="unit">
                        <label>联系电话：</label>
                        <input type="text" class="phone required" name="phone">
                    </div>
                    <div class="unit">
                        <label>邮箱：</label>
                        <input type="text" class="email required" name="email">
                    </div>
                    <div class="unit">
                        <label>部门：</label>
                        <select name="departmentId" class="required combox">
                            <option value="">请选择</option>
                            <option value="1">技术部</option>
                            <option value="2">销售部</option>
                            <option value="3">人事部</option>
                        </select>
                    </div>
                    <div class="unit">
                        <label>职位：</label>
                        <select name="positionId" class="required combox">
                            <option value="">请选择</option>
                            <option value="1">程序员</option>
                            <option value="2">销售经理</option>
                            <option value="3">人事专员</option>
                        </select>
                    </div>
                    <div class="unit">
                        <label>入职日期：</label>
                        <input type="text" name="hireDate" class="date required" dateFmt="yyyy-MM-dd" />
                    </div>
                </div>
                <div class="formBar">
                    <ul>
                        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                        <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

#### 创建部门管理页面
pages/department_list.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门管理</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageHeader">
            <form onsubmit="return navTabSearch(this);" action="department_list.html" method="post">
                <div class="searchBar">
                    <table class="searchContent">
                        <tr>
                            <td>
                                部门名称：<input type="text" name="name" />
                            </td>
                            <td>
                                <button type="submit">检索</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div class="pageContent">
            <div class="panelBar">
                <ul class="toolBar">
                    <li><a class="add" href="department_form.html" target="navTab" rel="department_add"><span>添加</span></a></li>
                    <li><a class="delete" href="delete_department.html" target="ajaxTodo" title="确定要删除该部门吗?"><span>删除</span></a></li>
                    <li><a class="edit" href="department_form.html?id={id}" target="navTab" rel="department_edit"><span>修改</span></a></li>
                </ul>
            </div>
            <table class="table" width="100%" layoutH="138">
                <thead>
                    <tr>
                        <th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                        <th orderField="id" class="asc">编号</th>
                        <th orderField="name">部门名称</th>
                        <th width="80">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr target="sid_user" rel="1">
                        <td><input name="ids" value="1" type="checkbox"></td>
                        <td>1</td>
                        <td>技术部</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_department.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="department_form.html?id=1" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                    <tr target="sid_user" rel="2">
                        <td><input name="ids" value="2" type="checkbox"></td>
                        <td>2</td>
                        <td>销售部</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_department.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="department_form.html?id=2" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                    <tr target="sid_user" rel="3">
                        <td><input name="ids" value="3" type="checkbox"></td>
                        <td>3</td>
                        <td>人事部</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_department.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="department_form.html?id=3" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
```

pages/department_form.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>部门信息</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageContent">
            <form method="post" action="save_department.html" class="pageForm required-validate" target="navTabTodo">
                <div class="pageFormContent" layoutH="56">
                    <div class="unit">
                        <label>部门名称：</label>
                        <input type="text" class="required" name="name">
                    </div>
                    <div class="unit">
                        <label>上级部门：</label>
                        <select name="parentId" class="combox">
                            <option value="0">无</option>
                            <option value="1">技术部</option>
                            <option value="2">销售部</option>
                            <option value="3">人事部</option>
                        </select>
                    </div>
                </div>
                <div class="formBar">
                    <ul>
                        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                        <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

#### 创建职位管理页面
pages/position_list.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>职位管理</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageHeader">
            <form onsubmit="return navTabSearch(this);" action="position_list.html" method="post">
                <div class="searchBar">
                    <table class="searchContent">
                        <tr>
                            <td>
                                职位名称：<input type="text" name="name" />
                            </td>
                            <td>
                                <button type="submit">检索</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <div class="pageContent">
            <div class="panelBar">
                <ul class="toolBar">
                    <li><a class="add" href="position_form.html" target="navTab" rel="position_add"><span>添加</span></a></li>
                    <li><a class="delete" href="delete_position.html" target="ajaxTodo" title="确定要删除该职位吗?"><span>删除</span></a></li>
                    <li><a class="edit" href="position_form.html?id={id}" target="navTab" rel="position_edit"><span>修改</span></a></li>
                </ul>
            </div>
            <table class="table" width="100%" layoutH="138">
                <thead>
                    <tr>
                        <th width="30"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                        <th orderField="id" class="asc">编号</th>
                        <th orderField="name">职位名称</th>
                        <th width="80">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr target="sid_user" rel="1">
                        <td><input name="ids" value="1" type="checkbox"></td>
                        <td>1</td>
                        <td>程序员</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_position.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="position_form.html?id=1" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                    <tr target="sid_user" rel="2">
                        <td><input name="ids" value="2" type="checkbox"></td>
                        <td>2</td>
                        <td>销售经理</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_position.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="position_form.html?id=2" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                    <tr target="sid_user" rel="3">
                        <td><input name="ids" value="3" type="checkbox"></td>
                        <td>3</td>
                        <td>人事专员</td>
                        <td>
                            <a title="删除" target="ajaxTodo" href="delete_position.html" class="btnDel">删除</a>
                            <a title="编辑" target="navTab" href="position_form.html?id=3" class="btnEdit">编辑</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
```

pages/position_form.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>职位信息</title>
    <link href="../dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../dwz_jui-master/js/jquery-3.4.1.js"></script>
</head>
<body>
    <div class="page">
        <div class="pageContent">
            <form method="post" action="save_position.html" class="pageForm required-validate" target="navTabTodo">
                <div class="pageFormContent" layoutH="56">
                    <div class="unit">
                        <label>职位名称：</label>
                        <input type="text" class="required" name="name">
                    </div>
                </div>
                <div class="formBar">
                    <ul>
                        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                        <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

### 任务2：测试和完善

#### 创建入口页面
index.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工管理系统</title>
    <script type="text/javascript">
        // 重定向到登录页面
        window.location.href = "login.html";
    </script>
</head>
<body>
    如果没有自动跳转，请点击<a href="login.html">这里</a>
</body>
</html>
```

### 任务3：系统优化和文档编写

#### 添加自定义JavaScript功能
js/app.js:
```javascript
// 自定义应用JavaScript代码
$(document).ready(function() {
    // 可以在这里添加自定义的功能
    
    // 示例：添加一个全局的确认删除函数
    window.confirmDelete = function(url, title) {
        if (!title) title = "确定要删除这条记录吗？";
        alertMsg.confirm(title, {
            okCall: function() {
                DWZ.ajaxTodo(url, navTabAjaxDone);
            }
        });
    };
    
    // 示例：格式化日期显示
    window.formatDate = function(dateStr) {
        if (!dateStr) return '';
        var date = new Date(dateStr);
        var year = date.getFullYear();
        var month = (date.getMonth() + 1).toString().padStart(2, '0');
        var day = date.getDate().toString().padStart(2, '0');
        return year + '-' + month + '-' + day;
    };
});
```

## 总结

通过这三天的综合开发实践，您应该已经掌握了：

1. **项目开发流程**：从需求分析、系统设计到编码实现、测试完善的完整流程
2. **DWZ框架综合应用**：熟练运用前七天学到的所有DWZ组件和功能
3. **实际问题解决能力**：在实际开发中遇到问题并解决问题的能力
4. **系统架构思维**：对Web应用的整体架构有了更深入的理解

这个员工管理系统虽然只是一个示例项目，但它涵盖了实际项目开发中的很多关键点，为您今后开发更复杂的系统打下了坚实的基础。