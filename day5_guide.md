# DWZ框架第五天学习指南

## 学习目标

通过今天的学习，您将：
1. 深入理解表格和分页组件的工作原理和使用方法
2. 掌握树形组件(tree)的使用技巧
3. 能够创建带分页的数据列表和树形菜单
4. 熟练运用数据展示组件提升数据呈现效果

## 任务1：学习表格和分页组件

### 什么是表格组件？

表格组件是DWZ框架中用于展示结构化数据的重要组件。它不仅支持基本的表格展示功能，还集成了排序、分页、行选择等高级功能，使数据展示更加灵活和用户友好。

### 表格组件工作原理

DWZ框架的表格组件基于以下技术实现：
1. **HTML结构解析**：通过特定的CSS类识别表格组件
2. **数据渲染**：支持静态数据和动态Ajax加载数据
3. **交互处理**：提供排序、选择、操作等功能
4. **样式美化**：通过CSS实现美观的表格外观

### 表格组件核心属性和类

#### 表格容器类
- `class="table"`：基本表格样式
- `class="list"`：列表样式表格
- `class="grid"`：网格样式表格

#### 表格功能类
- `class="tablesorter"`：启用表格排序功能
- `class="tableForm"`：表单样式的表格
- `class="tableStyle"`：应用表格样式

#### 分页组件
- `class="panelBar"`：分页工具栏容器
- `class="pagination"`：分页组件
- `rel="参数"`：分页参数配置

### 表格组件使用示例

#### 基本表格使用
```html
<table class="table" width="100%">
    <thead>
        <tr>
            <th width="50">ID</th>
            <th>姓名</th>
            <th>邮箱</th>
            <th width="100">操作</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>张三</td>
            <td>zhangsan@example.com</td>
            <td>
                <a href="edit.html" target="navTab">编辑</a>
                <a href="delete.html" target="ajaxTodo">删除</a>
            </td>
        </tr>
        <tr>
            <td>2</td>
            <td>李四</td>
            <td>lisi@example.com</td>
            <td>
                <a href="edit.html" target="navTab">编辑</a>
                <a href="delete.html" target="ajaxTodo">删除</a>
            </td>
        </tr>
    </tbody>
</table>
```

#### 带分页的表格使用
```html
<div class="pageContent">
    <table class="table" width="100%">
        <thead>
            <tr>
                <th width="50">ID</th>
                <th orderField="name" class="asc">姓名</th>
                <th orderField="email">邮箱</th>
                <th width="100">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>张三</td>
                <td>zhangsan@example.com</td>
                <td>
                    <a href="edit.html" target="navTab">编辑</a>
                    <a href="delete.html" target="ajaxTodo">删除</a>
                </td>
            </tr>
            <!-- 更多数据行 -->
        </tbody>
    </table>
    
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50" selected>50</option>
                <option value="100">100</option>
            </select>
            <span>条，共<label class="totalCount">120</label>条</span>
        </div>
        
        <div class="pagination" targetType="navTab" totalCount="120" numPerPage="50" pageNumShown="10" currentPage="1"></div>
    </div>
</div>
```

### 分页组件参数说明

- `targetType`：目标类型(navTab/dialog)
- `totalCount`：总记录数
- `numPerPage`：每页显示记录数
- `pageNumShown`：显示的页码数量
- `currentPage`：当前页码

### 实践练习

让我们通过实际例子来掌握表格和分页组件的使用：

1. 创建一个新的HTML文件 `table_pagination_demo.html`
2. 引入DWZ框架所需的CSS和JS文件
3. 创建带分页功能的数据表格
4. 测试分页和排序功能

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>表格和分页组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.table.js" type="text/javascript"></script>
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
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>表格和分页组件演示</h1>
            
            <div class="pageContent">
                <table class="table" width="100%">
                    <thead>
                        <tr>
                            <th width="50">ID</th>
                            <th orderField="name" class="asc">姓名</th>
                            <th orderField="email">邮箱</th>
                            <th orderField="department">部门</th>
                            <th width="120">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>张三</td>
                            <td>zhangsan@example.com</td>
                            <td>技术部</td>
                            <td>
                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>李四</td>
                            <td>lisi@example.com</td>
                            <td>销售部</td>
                            <td>
                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>王五</td>
                            <td>wangwu@example.com</td>
                            <td>人事部</td>
                            <td>
                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>赵六</td>
                            <td>zhaoliu@example.com</td>
                            <td>财务部</td>
                            <td>
                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                            </td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>钱七</td>
                            <td>qianqi@example.com</td>
                            <td>技术部</td>
                            <td>
                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
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
                        </select>
                        <span>条，共<label class="totalCount">120</label>条</span>
                    </div>
                    
                    <div class="pagination" targetType="navTab" totalCount="120" numPerPage="50" pageNumShown="10" currentPage="1"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## 任务2：掌握树形组件(tree)

### 什么是树形组件？

树形组件(tree)是DWZ框架中用于展示具有层级关系数据的组件，常用于菜单导航、组织结构、分类目录等场景。它支持节点展开/折叠、选中、异步加载等功能。

### 树形组件工作原理

DWZ框架的树形组件基于以下技术实现：
1. **递归渲染**：通过递归算法渲染多层级节点
2. **事件绑定**：处理节点的展开、折叠、选中等操作
3. **异步加载**：支持动态加载子节点数据
4. **样式控制**：通过CSS实现节点图标和连接线

### 树形组件核心属性和类

#### 树容器类
- `class="tree"`：基本树形组件样式
- `class="treeFolder"`：可折叠的树形组件
- `class="treeExpand"`：默认展开的树形组件

#### 树节点属性
- `href="链接"`：节点链接地址
- `target="目标"`：链接打开目标(navTab/dialog等)
- `rel="标识"`：节点唯一标识
- `title="标题"`：节点提示标题

### 树形组件使用示例

#### 基本树形菜单使用
```html
<ul class="tree">
    <li><a href="page1.html" target="navTab">节点1</a></li>
    <li><a>父节点2</a>
        <ul>
            <li><a href="page2.html" target="navTab">子节点2.1</a></li>
            <li><a href="page3.html" target="navTab">子节点2.2</a></li>
        </ul>
    </li>
    <li><a href="page4.html" target="navTab">节点3</a></li>
</ul>
```

#### 可折叠树形菜单使用
```html
<ul class="tree treeFolder">
    <li><a>系统管理</a>
        <ul>
            <li><a href="user_list.html" target="navTab" rel="users">用户管理</a></li>
            <li><a href="role_list.html" target="navTab" rel="roles">角色管理</a></li>
            <li><a href="permission_list.html" target="navTab" rel="permissions">权限管理</a></li>
        </ul>
    </li>
    <li><a>业务管理</a>
        <ul>
            <li><a href="order_list.html" target="navTab" rel="orders">订单管理</a></li>
            <li><a href="product_list.html" target="navTab" rel="products">产品管理</a></li>
        </ul>
    </li>
</ul>
```

### 实践练习

创建一个树形组件演示页面：

tree_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>树形组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.tree.js" type="text/javascript"></script>
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
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>树形组件演示</h1>
            
            <div style="width: 300px; float: left;">
                <h2>基本树形菜单</h2>
                <ul class="tree">
                    <li><a href="page1.html" target="navTab">首页</a></li>
                    <li><a>系统管理</a>
                        <ul>
                            <li><a href="user_list.html" target="navTab">用户管理</a></li>
                            <li><a href="role_list.html" target="navTab">角色管理</a></li>
                            <li><a href="permission_list.html" target="navTab">权限管理</a></li>
                        </ul>
                    </li>
                    <li><a>业务管理</a>
                        <ul>
                            <li><a href="order_list.html" target="navTab">订单管理</a></li>
                            <li><a href="product_list.html" target="navTab">产品管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            
            <div style="width: 300px; float: left; margin-left: 20px;">
                <h2>可折叠树形菜单</h2>
                <ul class="tree treeFolder">
                    <li><a>系统管理</a>
                        <ul>
                            <li><a href="user_list.html" target="navTab" rel="users">用户管理</a></li>
                            <li><a href="role_list.html" target="navTab" rel="roles">角色管理</a></li>
                            <li><a href="permission_list.html" target="navTab" rel="permissions">权限管理</a></li>
                        </ul>
                    </li>
                    <li><a>业务管理</a>
                        <ul>
                            <li><a href="order_list.html" target="navTab" rel="orders">订单管理</a></li>
                            <li><a href="product_list.html" target="navTab" rel="products">产品管理</a></li>
                        </ul>
                    </li>
                    <li><a>统计报表</a>
                        <ul>
                            <li><a href="sales_report.html" target="navTab" rel="sales">销售报表</a></li>
                            <li><a href="finance_report.html" target="navTab" rel="finance">财务报表</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            
            <div style="width: 300px; float: left; margin-left: 20px;">
                <h2>默认展开树形菜单</h2>
                <ul class="tree treeFolder treeExpand">
                    <li><a>系统管理</a>
                        <ul>
                            <li><a href="user_list.html" target="navTab">用户管理</a></li>
                            <li><a href="role_list.html" target="navTab">角色管理</a></li>
                        </ul>
                    </li>
                    <li><a>业务管理</a>
                        <ul>
                            <li><a href="order_list.html" target="navTab">订单管理</a></li>
                            <li><a href="product_list.html" target="navTab">产品管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            
            <div style="clear: both;"></div>
        </div>
    </div>
</body>
</html>
```

## 任务3：实践创建带分页的数据列表和树形菜单

现在我们将结合前面学到的表格分页和树形组件知识，创建一个综合演示页面。

### 综合示例

data_display_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据展示组件综合演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.tree.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.pagination.js" type="text/javascript"></script>
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
    <style>
        .left-tree {
            float: left;
            width: 200px;
            margin-right: 20px;
        }
        
        .right-content {
            float: left;
            width: calc(100% - 220px);
        }
        
        .clearfix::after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
</head>
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>数据展示组件综合演示</h1>
            
            <div class="clearfix">
                <div class="left-tree">
                    <div class="panel">
                        <h1>功能菜单</h1>
                        <div>
                            <ul class="tree treeFolder">
                                <li><a>系统管理</a>
                                    <ul>
                                        <li><a href="user_management.html" target="navTab" rel="users">用户管理</a></li>
                                        <li><a href="role_management.html" target="navTab" rel="roles">角色管理</a></li>
                                    </ul>
                                </li>
                                <li><a>业务管理</a>
                                    <ul>
                                        <li><a href="order_management.html" target="navTab" rel="orders">订单管理</a></li>
                                        <li><a href="product_management.html" target="navTab" rel="products">产品管理</a></li>
                                    </ul>
                                </li>
                                <li><a>统计报表</a>
                                    <ul>
                                        <li><a href="sales_report.html" target="navTab" rel="sales">销售报表</a></li>
                                        <li><a href="finance_report.html" target="navTab" rel="finance">财务报表</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                <div class="right-content">
                    <div class="panel">
                        <h1>用户列表</h1>
                        <div>
                            <div class="pageContent">
                                <table class="table" width="100%">
                                    <thead>
                                        <tr>
                                            <th width="30"><input type="checkbox" class="checkboxCtrl" group="ids" /></th>
                                            <th width="50">ID</th>
                                            <th orderField="name" class="asc">姓名</th>
                                            <th orderField="email">邮箱</th>
                                            <th orderField="department">部门</th>
                                            <th orderField="createTime">创建时间</th>
                                            <th width="120">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><input type="checkbox" name="ids" value="1" /></td>
                                            <td>1</td>
                                            <td>张三</td>
                                            <td>zhangsan@example.com</td>
                                            <td>技术部</td>
                                            <td>2023-01-15</td>
                                            <td>
                                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="ids" value="2" /></td>
                                            <td>2</td>
                                            <td>李四</td>
                                            <td>lisi@example.com</td>
                                            <td>销售部</td>
                                            <td>2023-02-20</td>
                                            <td>
                                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="ids" value="3" /></td>
                                            <td>3</td>
                                            <td>王五</td>
                                            <td>wangwu@example.com</td>
                                            <td>人事部</td>
                                            <td>2023-03-10</td>
                                            <td>
                                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="ids" value="4" /></td>
                                            <td>4</td>
                                            <td>赵六</td>
                                            <td>zhaoliu@example.com</td>
                                            <td>财务部</td>
                                            <td>2023-04-05</td>
                                            <td>
                                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="ids" value="5" /></td>
                                            <td>5</td>
                                            <td>钱七</td>
                                            <td>qianqi@example.com</td>
                                            <td>技术部</td>
                                            <td>2023-05-12</td>
                                            <td>
                                                <a href="user_edit.html" target="navTab" title="编辑用户">编辑</a>
                                                <a href="user_delete.html" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
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
                                        </select>
                                        <span>条，共<label class="totalCount">120</label>条</span>
                                    </div>
                                    
                                    <div class="pagination" targetType="navTab" totalCount="120" numPerPage="50" pageNumShown="10" currentPage="1"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="panel">
                        <h1>订单列表</h1>
                        <div>
                            <div class="pageContent">
                                <table class="table" width="100%">
                                    <thead>
                                        <tr>
                                            <th width="30"><input type="checkbox" class="checkboxCtrl" group="orderIds" /></th>
                                            <th width="100">订单号</th>
                                            <th orderField="customer">客户</th>
                                            <th orderField="amount">金额</th>
                                            <th orderField="status">状态</th>
                                            <th orderField="createTime">创建时间</th>
                                            <th width="120">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><input type="checkbox" name="orderIds" value="1" /></td>
                                            <td>ORD202301001</td>
                                            <td>张三</td>
                                            <td>¥1,280.00</td>
                                            <td>已发货</td>
                                            <td>2023-01-15</td>
                                            <td>
                                                <a href="order_detail.html" target="navTab" title="订单详情">详情</a>
                                                <a href="order_cancel.html" target="ajaxTodo" title="确定要取消该订单吗?">取消</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="orderIds" value="2" /></td>
                                            <td>ORD202301002</td>
                                            <td>李四</td>
                                            <td>¥2,560.00</td>
                                            <td>待付款</td>
                                            <td>2023-01-16</td>
                                            <td>
                                                <a href="order_detail.html" target="navTab" title="订单详情">详情</a>
                                                <a href="order_cancel.html" target="ajaxTodo" title="确定要取消该订单吗?">取消</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" name="orderIds" value="3" /></td>
                                            <td>ORD202301003</td>
                                            <td>王五</td>
                                            <td>¥890.00</td>
                                            <td>已完成</td>
                                            <td>2023-01-17</td>
                                            <td>
                                                <a href="order_detail.html" target="navTab" title="订单详情">详情</a>
                                                <a href="order_cancel.html" target="ajaxTodo" title="确定要取消该订单吗?">取消</a>
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
                                        </select>
                                        <span>条，共<label class="totalCount">85</label>条</span>
                                    </div>
                                    
                                    <div class="pagination" targetType="navTab" totalCount="85" numPerPage="50" pageNumShown="10" currentPage="1"></div>
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

## 总结

通过今天的任务，您应该已经掌握了：

1. **表格和分页组件**：理解了其工作原理和使用方法，能够创建带分页和排序功能的数据表格
2. **树形组件**：学会了使用树形菜单展示层级结构数据
3. **综合实践**：能够结合使用表格、分页和树形组件创建丰富的数据展示界面

明天我们将学习Ajax与数据交互，包括Ajax集成和异步请求处理以及数据联动功能。