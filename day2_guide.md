# DWZ框架第二天学习指南

## 学习目标

通过今天的学习，您将：

1. 深入理解navTab组件的工作原理和使用方法
2. 掌握菜单栏和导航栏的实现技术
3. 熟练使用面板组件（accordion等）
4. 能够构建完整的导航结构页面

## 任务1：学习navTab组件工作原理

### 什么是navTab组件？

navTab是DWZ框架中最核心的组件之一，它提供了一种类似浏览器标签页的方式来管理和展示不同的页面内容。每个navTab页面都是一个独立的视图，可以在同一个页面内进行切换而无需重新加载整个页面。

### navTab工作原理详解

navTab组件基于以下技术实现：
1. **DOM操作**：通过动态创建和销毁DOM元素来管理页面
2. **Ajax加载**：使用Ajax技术异步加载页面内容
3. **事件绑定**：通过事件监听机制处理用户的操作
4. **状态管理**：维护各个标签页的状态信息

### navTab核心属性和方法

#### 属性
- `target="navTab"`：标识链接在navTab中打开
- `rel="identifier"`：页面唯一标识符
- `title="页面标题"`：标签页上显示的标题
- `fresh="true/false"`：是否每次都重新加载页面（默认true）
- `external="true/false"`：是否以外部链接方式打开

#### JavaScript方法
- `navTab.openTab(tabid, url, options)`：打开新标签页
- `navTab.reload(options)`：重新加载当前标签页
- `navTab.closeTab(tabid)`：关闭指定标签页
- `navTab.getCurrentPanel()`：获取当前标签页的内容面板

### navTab使用示例

```html
<!-- 基本使用 -->
<a href="page1.html" target="navTab" rel="page1" title="页面1">打开页面1</a>

<!-- 不重新加载页面 -->
<a href="page2.html" target="navTab" rel="page2" fresh="false">打开页面2</a>

<!-- 外部链接 -->
<a href="http://www.example.com" target="navTab" external="true">外部链接</a>
```

### navTab结构分析

navTab的HTML结构包含以下关键部分：

```html
<div id="navTab" class="tabsPage">
    <!-- 标签页头部 -->
    <div class="tabsPageHeader">
        <div class="tabsPageHeaderContent">
            <ul class="navTab-tab">
                <li tabid="main" class="main">
                    <a href="javascript:;">
                        <span>
                            <span class="home_icon">我的主页</span>
                        </span>
                    </a>
                </li>
            </ul>
        </div>
        <div class="tabsLeft">left</div>
        <div class="tabsRight">right</div>
        <div class="tabsMore">more</div>
    </div>
    
    <!-- 更多标签列表 -->
    <ul class="tabsMoreList">
        <li><a href="javascript:;">我的主页</a></li>
    </ul>
    
    <!-- 标签页内容区域 -->
    <div class="navTab-panel tabsPageContent layoutBox">
        <div class="page unitBox">
            <!-- 页面内容 -->
        </div>
    </div>
</div>
```

### 实践练习

让我们通过实际例子来掌握navTab的使用：

1. 创建一个新的HTML文件 `navtab_demo.html`
2. 引入DWZ框架所需的CSS和JS文件
3. 创建基本的页面结构
4. 添加几个可以打开navTab的链接
5. 测试不同的属性组合

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>navTab组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
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
            <h1>navTab组件演示</h1>
            
            <div class="unit">
                <a class="button" href="demo_page1.html" target="navTab" rel="page1" title="页面1">
                    <span>打开页面1</span>
                </a>
                <a class="button" href="demo_page2.html" target="navTab" rel="page2" title="页面2">
                    <span>打开页面2</span>
                </a>
                <a class="button" href="demo_page1.html" target="navTab" rel="page1" fresh="false">
                    <span>打开页面1(不刷新)</span>
                </a>
            </div>
        </div>
        
        <div style="margin:20px;">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navTab-tab">
                            <li tabid="main" class="main">
                                <a href="javascript:;">
                                    <span><span class="home_icon">我的主页</span></span>
                                </a>
                            </li>
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
                        <div style="padding:20px;">
                            <h2>欢迎使用navTab组件</h2>
                            <p>点击上面的按钮打开不同的标签页</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

创建两个简单的演示页面：

demo_page1.html:
```html
<div style="padding:20px;">
    <h2>页面1</h2>
    <p>这是第一个演示页面</p>
    <p>当前时间：<span id="time1"></span></p>
    <script>
        document.getElementById('time1').innerHTML = new Date().toLocaleString();
    </script>
</div>
```

demo_page2.html:
```html
<div style="padding:20px;">
    <h2>页面2</h2>
    <p>这是第二个演示页面</p>
    <form>
        <div class="unit">
            <label>姓名：</label>
            <input type="text" />
        </div>
        <div class="unit">
            <label>邮箱：</label>
            <input type="email" />
        </div>
    </form>
</div>
```

## 任务2：掌握菜单栏和导航栏使用方法

### 菜单栏类型

DWZ框架支持多种类型的菜单栏：
1. **左侧垂直菜单**：通常用于主菜单导航
2. **顶部水平导航**：通常用于功能快捷方式
3. **上下文菜单**：右键弹出的功能菜单

### 左侧垂直菜单实现

左侧菜单通常包含在sidebar元素中，支持手风琴式的折叠效果。

#### 基本结构

```html
<div id="sidebar">
    <div class="toggleCollapse">
        <h2>主菜单</h2>
        <div>收缩</div>
    </div>
    
    <div class="accordion" fillSpace="sidebar">
        <div class="accordionHeader">
            <h2><span>Folder</span>菜单组1</h2>
        </div>
        <div class="accordionContent">
            <ul class="tree treeFolder">
                <li><a href="page1.html" target="navTab" rel="page1">页面1</a></li>
                <li><a href="page2.html" target="navTab" rel="page2">页面2</a></li>
            </ul>
        </div>
        
        <div class="accordionHeader">
            <h2><span>Folder</span>菜单组2</h2>
        </div>
        <div class="accordionContent">
            <ul class="tree treeFolder">
                <li><a href="page3.html" target="navTab" rel="page3">页面3</a></li>
                <li><a href="page4.html" target="navTab" rel="page4">页面4</a></li>
            </ul>
        </div>
    </div>
</div>
```

#### 关键CSS类说明

- `.toggleCollapse`：菜单收缩/展开按钮
- `.accordion`：手风琴容器
- `.accordionHeader`：手风琴头部
- `.accordionContent`：手风琴内容
- `.tree`：树形菜单样式
- `.treeFolder`：可折叠树形菜单

### 顶部水平导航实现

顶部导航通常包含网站Logo、功能链接和用户操作选项。

#### 基本结构

```html
<div id="header">
    <div class="headerNav">
        <a class="logo" href="#">网站标志</a>
        <ul class="nav">
            <li><a href="settings.html" target="dialog">设置</a></li>
            <li><a href="logout.html">退出</a></li>
        </ul>
    </div>
</div>
```

#### 关键CSS类说明

- `.headerNav`：顶部导航容器
- `.logo`：网站标志样式
- `.nav`：导航菜单样式

### 实践练习

创建一个包含完整菜单结构的页面：

menu_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜单组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.accordion.js" type="text/javascript"></script>
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
    <style>
        #container {
            position: absolute;
            top: 80px;
            left: 200px;
            right: 0;
            bottom: 0;
        }
    </style>
</head>
<body>
    <div id="layout">
        <!-- 顶部导航 -->
        <div id="header">
            <div class="headerNav">
                <a class="logo" href="#">DWZ演示</a>
                <ul class="nav">
                    <li><a href="javascript:void(0)">设置</a></li>
                    <li><a href="javascript:void(0)">帮助</a></li>
                    <li><a href="javascript:void(0)">退出</a></li>
                </ul>
            </div>
        </div>
        
        <!-- 左侧菜单 -->
        <div style="position: absolute; top: 80px; left: 0; bottom: 0; width: 200px;">
            <div id="sidebar">
                <div class="toggleCollapse">
                    <h2>主菜单</h2>
                    <div>收缩</div>
                </div>
                
                <div class="accordion" fillSpace="sidebar">
                    <div class="accordionHeader">
                        <h2><span>Folder</span>系统管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="user_management.html" target="navTab" rel="users">用户管理</a></li>
                            <li><a href="role_management.html" target="navTab" rel="roles">角色管理</a></li>
                        </ul>
                    </div>
                    
                    <div class="accordionHeader">
                        <h2><span>Folder</span>业务管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="order_management.html" target="navTab" rel="orders">订单管理</a></li>
                            <li><a href="product_management.html" target="navTab" rel="products">产品管理</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 主内容区域 -->
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navTab-tab">
                            <li tabid="main" class="main">
                                <a href="javascript:;">
                                    <span><span class="home_icon">我的主页</span></span>
                                </a>
                            </li>
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
                        <div style="padding:20px;">
                            <h2>欢迎使用DWZ框架</h2>
                            <p>请使用左侧菜单导航到不同功能页面</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

创建几个简单的菜单目标页面：

user_management.html:
```html
<div style="padding:20px;">
    <h2>用户管理</h2>
    <p>这里是用户管理页面</p>
    <table class="table" width="100%">
        <thead>
            <tr>
                <th>用户名</th>
                <th>邮箱</th>
                <th>角色</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>张三</td>
                <td>zhangsan@example.com</td>
                <td>管理员</td>
            </tr>
            <tr>
                <td>李四</td>
                <td>lisi@example.com</td>
                <td>用户</td>
            </tr>
        </tbody>
    </table>
</div>
```

## 任务3：学习面板组件(accordion等)

### Accordion手风琴组件

Accordion是一种垂直堆叠的面板组件，每次只能展开一个面板。

#### 基本使用

```html
<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2><span>Folder</span>面板标题1</h2>
    </div>
    <div class="accordionContent">
        <p>面板1的内容</p>
    </div>
    
    <div class="accordionHeader">
        <h2><span>Folder</span>面板标题2</h2>
    </div>
    <div class="accordionContent">
        <p>面板2的内容</p>
    </div>
</div>
```

#### 关键属性

- `fillSpace`：指定填充哪个元素的空间
- `accordionHeader`：手风琴面板的头部
- `accordionContent`：手风琴面板的内容

### Panel面板组件

Panel是最基本的面板组件，用于组织页面内容。

#### 基本使用

```html
<div class="panel">
    <h1>面板标题</h1>
    <div>
        <p>面板内容</p>
    </div>
</div>
```

### Tab选项卡组件

Tab是在同一区域内展示不同内容的组件。

#### 基本使用

```html
<div class="tabs">
    <div class="tabsHeader">
        <div class="tabsHeaderContent">
            <ul>
                <li><a href="#"><span>标签1</span></a></li>
                <li><a href="#"><span>标签2</span></a></li>
            </ul>
        </div>
    </div>
    <div class="tabsContent">
        <div>内容1</div>
        <div>内容2</div>
    </div>
</div>
```

### 实践练习

创建一个综合使用各种面板组件的页面：

panels_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>面板组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.tab.js" type="text/javascript"></script>
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
            <h1>面板组件演示</h1>
            
            <div style="width: 50%; float: left; padding-right: 20px;">
                <h2>Accordion手风琴组件</h2>
                <div class="accordion">
                    <div class="accordionHeader">
                        <h2><span>Folder</span>第一部分</h2>
                    </div>
                    <div class="accordionContent">
                        <p>这是手风琴的第一部分内容</p>
                        <ul>
                            <li>列表项1</li>
                            <li>列表项2</li>
                            <li>列表项3</li>
                        </ul>
                    </div>
                    
                    <div class="accordionHeader">
                        <h2><span>Folder</span>第二部分</h2>
                    </div>
                    <div class="accordionContent">
                        <p>这是手风琴的第二部分内容</p>
                        <p>可以放置任何HTML内容</p>
                    </div>
                </div>
            </div>
            
            <div style="width: 45%; float: left;">
                <h2>Tab选项卡组件</h2>
                <div class="tabs">
                    <div class="tabsHeader">
                        <div class="tabsHeaderContent">
                            <ul>
                                <li><a href="#"><span>选项卡1</span></a></li>
                                <li><a href="#"><span>选项卡2</span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="tabsContent">
                        <div>
                            <p>这是第一个选项卡的内容</p>
                            <form>
                                <div class="unit">
                                    <label>姓名：</label>
                                    <input type="text" />
                                </div>
                            </form>
                        </div>
                        <div>
                            <p>这是第二个选项卡的内容</p>
                            <table class="table" width="100%">
                                <thead>
                                    <tr>
                                        <th>列1</th>
                                        <th>列2</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>数据1</td>
                                        <td>数据2</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <h2 style="margin-top: 20px;">Panel面板组件</h2>
                <div class="panel">
                    <h1>面板标题</h1>
                    <div>
                        <p>这是一个基本的面板组件</p>
                        <p>可以用来组织页面上的内容</p>
                    </div>
                </div>
            </div>
            
            <div style="clear: both;"></div>
        </div>
    </div>
</body>
</html>
```

## 任务4：实践构建完整导航结构页面

现在我们将把前面学到的所有知识结合起来，构建一个完整的具有导航结构的页面。

### 完整示例

full_nav_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>完整导航结构演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.accordion.js" type="text/javascript"></script>
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
    <style>
        #leftside {
            position: absolute;
            top: 80px;
            left: 0;
            bottom: 0;
            width: 200px;
        }
        
        #container {
            position: absolute;
            top: 80px;
            left: 200px;
            right: 0;
            bottom: 0;
        }
    </style>
</head>
<body>
    <div id="layout">
        <!-- 顶部导航栏 -->
        <div id="header">
            <div class="headerNav">
                <a class="logo" href="#">DWZ管理系统</a>
                <ul class="nav">
                    <li><a href="changepwd.html" target="dialog">修改密码</a></li>
                    <li><a href="javascript:void(0)">帮助</a></li>
                    <li><a href="login.html">退出</a></li>
                </ul>
            </div>
        </div>
        
        <!-- 左侧菜单栏 -->
        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse">
                        <div></div>
                    </div>
                </div>
            </div>
            
            <div id="sidebar">
                <div class="toggleCollapse">
                    <h2>主菜单</h2>
                    <div>收缩</div>
                </div>
                
                <div class="accordion" fillSpace="sidebar">
                    <div class="accordionHeader">
                        <h2><span>Folder</span>系统管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li>
                                <a>用户权限管理</a>
                                <ul>
                                    <li><a href="user_list.html" target="navTab" rel="userList">用户管理</a></li>
                                    <li><a href="role_list.html" target="navTab" rel="roleList">角色管理</a></li>
                                    <li><a href="permission_list.html" target="navTab" rel="permList">权限管理</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    
                    <div class="accordionHeader">
                        <h2><span>Folder</span>业务管理</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li>
                                <a>订单中心</a>
                                <ul>
                                    <li><a href="order_list.html" target="navTab" rel="orderList">订单列表</a></li>
                                    <li><a href="refund_list.html" target="navTab" rel="refundList">退款管理</a></li>
                                </ul>
                            </li>
                            <li>
                                <a>商品管理</a>
                                <ul>
                                    <li><a href="product_list.html" target="navTab" rel="productList">商品列表</a></li>
                                    <li><a href="category_list.html" target="navTab" rel="categoryList">分类管理</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    
                    <div class="accordionHeader">
                        <h2><span>Folder</span>统计报表</h2>
                    </div>
                    <div class="accordionContent">
                        <ul class="tree treeFolder">
                            <li><a href="sales_report.html" target="navTab" rel="salesReport">销售报表</a></li>
                            <li><a href="finance_report.html" target="navTab" rel="financeReport">财务报表</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- 主内容区域 -->
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <ul class="navTab-tab">
                            <li tabid="main" class="main">
                                <a href="javascript:;">
                                    <span><span class="home_icon">我的主页</span></span>
                                </a>
                            </li>
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
                                <h2><a href="#">DWZ框架完整导航结构演示</a></h2>
                                <a>这是一个完整的导航结构示例，展示了DWZ框架的主要导航组件</a>
                            </div>
                        </div>
                        
                        <div style="padding:20px;">
                            <h3>功能说明</h3>
                            <ol>
                                <li>顶部导航栏：包含网站Logo和常用功能链接</li>
                                <li>左侧菜单栏：使用手风琴和树形菜单组织功能模块</li>
                                <li>主内容区域：使用navTab展示具体内容页面</li>
                            </ol>
                            
                            <h3>操作指南</h3>
                            <ul>
                                <li>点击左侧菜单可以打开对应的功能页面</li>
                                <li>点击顶部"收缩"按钮可以收起左侧菜单</li>
                                <li>在主内容区域可以打开多个标签页</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

创建几个简单的功能页面：

user_list.html:
```html
<div style="padding:20px;">
    <h2>用户管理</h2>
    <div class="searchBar">
        <form>
            <div class="subBar">
                <ul>
                    <li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
                </ul>
            </div>
        </form>
    </div>
    <div class="pageContent">
        <table class="table" width="100%">
            <thead>
                <tr>
                    <th width="50">ID</th>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>角色</th>
                    <th width="100">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>admin</td>
                    <td>admin@example.com</td>
                    <td>超级管理员</td>
                    <td>
                        <a href="user_edit.html" target="navTab" rel="userEdit">编辑</a>
                        <a href="javascript:void(0)" onclick="alertMsg.confirm('确定要删除该用户吗？')">删除</a>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>zhangsan</td>
                    <td>zhangsan@example.com</td>
                    <td>普通用户</td>
                    <td>
                        <a href="user_edit.html" target="navTab" rel="userEdit">编辑</a>
                        <a href="javascript:void(0)" onclick="alertMsg.confirm('确定要删除该用户吗？')">删除</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
```

## 总结

通过今天的任务，您应该已经掌握了：

1. **navTab组件**：理解了其工作原理和使用方法，能够创建和管理多个标签页
2. **菜单栏和导航栏**：学会了创建左侧垂直菜单和顶部水平导航，掌握了相关的CSS类和结构
3. **面板组件**：熟悉了accordion、panel和tab等面板组件的使用方法
4. **综合实践**：能够构建完整的导航结构页面，整合了今天学到的所有知识

明天我们将学习对话框与弹窗组件，包括dialog组件和alert组件的使用方法。