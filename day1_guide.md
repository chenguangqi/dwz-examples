# DWZ框架第一天学习指南

## 学习目标

通过今天的学习，您将：

1. 理解DWZ框架的概述和整体结构
2. 掌握DWZ框架的初始化过程
3. 熟悉核心组件和配置文件的作用
4. 能够创建简单页面集成DWZ基础功能

## 任务1：理解DWZ框架概述和整体结构

### 什么是DWZ框架？

DWZ是一个基于jQuery的富客户端框架，它提供了丰富的UI组件和便捷的Ajax封装，让Web应用开发变得更加简单高效。

### DWZ框架的特点

1. **开源免费**：完全开源，可用于商业项目
2. **轻量级**：相比Ext、EasyUI等框架更加轻量
3. **易于使用**：基于HTML扩展的开发方式，零JavaScript编码
4. **功能丰富**：提供丰富的UI组件和功能模块
5. **良好的兼容性**：兼容主流浏览器，包括IE6+

### DWZ J-UI框架源码结构分析

我们的项目中包含了完整的DWZ J-UI框架源码，目录结构如下：

```
dwz_jui-master/
├── bin/                  # 编译压缩后的生产环境版本
├── chart/                # 图表插件(echarts)
├── demo/                 # 各种功能演示示例
├── doc/                  # 文档资料
├── js/                   # JavaScript组件源码（核心）
├── themes/               # 主题样式文件
├── uploadify/            # jQuery文件上传插件
├── xheditor/             # 在线HTML编辑器
├── index.html            # 框架功能演示入口文件
├── dwz.frag.xml          # 页面片段配置文件
└── ...                   # 其他示例页面文件
```

#### 核心js目录详解

`dwz_jui-master/js/`目录包含了DWZ框架所有的JavaScript组件源码：

- **核心文件**：
  - `dwz.core.js`：框架核心，包含初始化逻辑
  - `dwz.ui.js`：UI组件基础功能
  - `dwz.regional.zh.js`：中文本地化支持

- **组件文件**：
  - `dwz.navTab.js`：导航标签页组件
  - `dwz.dialog.js`：弹出窗口组件
  - `dwz.tree.js`：树形菜单组件
  - `dwz.accordion.js`：手风琴菜单组件
  - `dwz.alertMsg.js`：提示信息组件
  - `dwz.ajax.js`：Ajax处理封装
  - `dwz.panel.js`：面板组件
  - `dwz.tab.js`：选项卡组件
  - `dwz.dialogDrag.js`：对话框拖拽组件
  - `dwz.sortDrag.js`：排序拖拽组件
  - `dwz.stable.js`：可排序表格组件
  - `dwz.pagination.js`：分页组件
  - `dwz.datepicker.js`：日期选择器组件
  - `dwz.combox.js`：下拉选择框组件
  - `dwz.checkbox.js`：复选框组件
  - `dwz.barDrag.js`：进度条拖拽组件
  - `dwz.taskBar.js`：任务栏组件
  - `dwz.switchEnv.js`：环境切换组件
  - `dwz.theme.js`：主题切换组件
  - `dwz.contextmenu.js`：上下文菜单组件
  - `dwz.miscDrag.js`：杂项拖拽组件
  - `dwz.file.js`：文件处理组件
  - `dwz.database.js`：数据库相关组件
  - `dwz.selectedLoad.js`：选中加载组件
  - `dwz.history.js`：历史记录组件
  - `dwz.print.js`：打印组件
  - `dwz.effects.js`：特效组件
  - `dwz.scrollCenter.js`：滚动居中组件
  - `dwz.resize.js`：调整大小组件

- **工具文件**：
  - `dwz.util.date.js`：日期处理工具
  - `dwz.util.number.js`：数字处理工具
  - `dwz.validate.method.js`：表单验证方法
  - `jquery-3.4.1.js`：jQuery库
  - `jquery-1.12.4.js`：jQuery库(兼容旧版IE)
  - `jquery.validate.js`：jQuery表单验证插件
  - `jquery.cookie.js`：Cookie处理插件
  - `jquery.bgiframe.js`：背景iframe插件(解决IE6选择框问题)
  - `jquery.easing.1.3.js`：缓动效果插件
  - `speedup.js`：加速脚本(针对IE)

#### themes主题目录

`dwz_jui-master/themes/`目录包含了框架的样式主题：

- `default/`：默认蓝色主题
- `green/`：绿色主题
- `purple/`：紫色主题
- `silver/`：银色主题
- `azure/`：天蓝色主题
- `miscDrag/`：拖拽相关样式
- `css/`：核心样式文件
  - `core.css`：核心样式
  - `ieHack.css`：IE兼容样式
  - `login.css`：登录页面样式
  - `print.css`：打印样式

### 核心概念

- **navTab**：导航标签页，是页面的主要容器
- **dialog**：弹出窗口，用于执行特定操作
- **Ajax**：异步通信，用于与服务器交换数据
- **组件化**：通过HTML属性声明式调用功能

## 任务2：学习DWZ的初始化过程

### DWZ初始化方法

DWZ框架的核心初始化方法是`DWZ.init()`，其语法如下：

```javascript
DWZ.init(fragUrl, options)
```

其中：
- `fragUrl`：页面片段配置文件地址（通常是dwz.frag.xml）
- `options`：初始化配置选项

### 源码分析：DWZ对象结构

查看`dwz_jui-master/js/dwz.core.js`文件，可以看到DWZ对象的定义：

```javascript
var DWZ = {
    version: '1.6.2',
    regPlugins: [], // 插件注册数组
    keyCode: {      // 键盘按键码常量
        ENTER: 13, ESC: 27, // ...
    },
    eventType: {    // 自定义事件类型
        pageClear:"pageClear",
        resizeGrid:"resizeGrid",
        initEnvAfter: "initEnvAfter"
    },
    pageInfo: {pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"},
    statusCode: {ok:200, error:300, timeout:301},
    keys: {statusCode:"statusCode", message:"message"},
    ui:{
        sbar:true,
        hideMode:'display'
    },
    frag:{}, // 页面片段缓存
    _msg:{}, // 消息模板
    _set:{
        loginUrl:"", 
        loginTitle:"", 
        debug:false
    },
    // ... 更多方法
};
```

### 初始化配置选项详解

常见的配置选项包括：

```javascript
{
    loginUrl: "login.html",              // 登录页面URL
    loginTitle: "登录",                  // 登录窗口标题
    statusCode: {ok:200, error:300, timeout:301}, // 状态码定义
    pageInfo: {                          // 分页信息字段定义
        pageNum:"pageNum", 
        numPerPage:"numPerPage", 
        orderField:"orderField", 
        orderDirection:"orderDirection"
    },
    keys: {                              // 返回结果键名定义
        statusCode:"statusCode", 
        message:"message"
    },
    ui: {                                // UI配置
        hideMode:'offsets'
    },
    debug: false,                        // 是否开启调试模式
    callback: function(){}               // 初始化完成后的回调函数
}
```

### 实际案例分析

查看`dwz_jui-master/index.html`文件中的初始化代码：

```
$(function(){
    DWZ.init("dwz.frag.xml", {
        loginUrl:"login_dialog.html", 
        loginTitle:"登录",
        statusCode:{ok:200, error:300, timeout:301},
        pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"},
        keys: {statusCode:"statusCode", message:"message"},
        ui:{hideMode:'offsets'},
        debug:false,
        callback:function(){
            initEnv();
            $("#themeList").theme({themeBase:"themes"});
        }
    });
});
```

关键步骤：
1. DOM加载完成后执行初始化
2. 调用`DWZ.init()`方法
3. 在callback中调用`initEnv()`初始化环境
4. 初始化主题切换组件

### 深入理解initEnv()函数

`initEnv()`函数在`dwz.core.js`中定义，主要完成以下工作：

1. 初始化页面组件
2. 绑定各种事件处理器
3. 设置页面布局
4. 初始化UI组件

```
function initEnv(){
    // 初始化页面环境
    $(window).resize(function(){
        // 窗口大小改变时的处理
        initLayout();
        $(document).trigger(DWZ.eventType.resizeGrid);
    });
    
    // 初始化各个组件
    initLayout();
    initUI();
    
    // 触发初始化完成事件
    $(document).trigger(DWZ.eventType.initEnvAfter);
}
```

## 任务3：掌握核心组件和配置文件

### 核心JavaScript文件

DWZ框架由多个JS文件组成，每个文件负责不同的功能：

| 文件 | 功能 |
|------|------|
| dwz.core.js | 核心框架，包含初始化逻辑 |
| dwz.navTab.js | 导航标签页组件 |
| dwz.dialog.js | 弹出窗口组件 |
| dwz.ajax.js | Ajax处理 |
| dwz.tree.js | 树形菜单组件 |
| dwz.accordion.js | 手风琴菜单组件 |
| dwz.alertMsg.js | 提示信息组件 |
| dwz.ui.js | UI组件基础功能 |
| dwz.panel.js | 面板组件 |
| dwz.tab.js | 选项卡组件 |
| dwz.dialogDrag.js | 对话框拖拽组件 |
| dwz.sortDrag.js | 排序拖拽组件 |
| dwz.stable.js | 可排序表格组件 |
| dwz.pagination.js | 分页组件 |
| dwz.datepicker.js | 日期选择器组件 |
| dwz.combox.js | 下拉选择框组件 |
| dwz.checkbox.js | 复选框组件 |
| dwz.barDrag.js | 进度条拖拽组件 |
| dwz.taskBar.js | 任务栏组件 |
| dwz.switchEnv.js | 环境切换组件 |
| dwz.theme.js | 主题切换组件 |
| dwz.contextmenu.js | 上下文菜单组件 |
| dwz.miscDrag.js | 杂项拖拽组件 |
| dwz.file.js | 文件处理组件 |
| dwz.database.js | 数据库相关组件 |
| dwz.selectedLoad.js | 选中加载组件 |
| dwz.history.js | 历史记录组件 |
| dwz.print.js | 打印组件 |
| dwz.effects.js | 特效组件 |
| dwz.scrollCenter.js | 滚动居中组件 |
| dwz.resize.js | 调整大小组件 |
| dwz.drag.js | 基础拖拽组件 |

### dwz.frag.xml配置文件

这个文件定义了各种组件所需的HTML模板片段。例如：

```
<_PAGE_ id="dialogFrag"><![CDATA[
<div class="dialog" style="top:150px; left:300px;">
    <div class="dialogHeader">
        <!-- 对话框头部 -->
    </div>
    <div class="dialogContent layoutBox unitBox">
        <!-- 对话框内容 -->
    </div>
    <div class="dialogFooter">
        <!-- 对话框底部 -->
    </div>
</div>
]]></_PAGE_>
```

当需要创建对话框时，框架会从这个配置文件中获取相应的HTML结构。

在`dwz_jui-master/dwz.frag.xml`中，你可以看到所有组件的模板定义。

### CSS样式文件

主题样式位于`dwz_jui-master/themes/`目录下，每个主题包含：
- style.css：主要样式文件
- core.css：核心样式
- ieHack.css：IE兼容样式
- print.css：打印样式

## 任务4：实践创建简单页面集成基础功能

### 创建HTML页面

创建一个简单的HTML页面，引入必要的CSS和JS文件：

```
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
    <!-- 引入必需的CSS文件 -->
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    
    <!-- 引入jQuery -->
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    
    <!-- 引入DWZ核心框架脚本 -->
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <!-- 引入其他必要组件 -->
    <script src="./dwz_jui-master/js/dwz.util.date.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.barDrag.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.drag.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.tree.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ui.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.theme.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.alertMsg.js" type="text/javascript"></script>
    
    <!-- 国际化支持 -->
    <script src="./dwz_jui-master/js/dwz.regional.zh.js" type="text/javascript"></script>
</head>
</html>
```

### 初始化框架

在页面底部添加初始化代码：

```
<script type="text/javascript">
$(function(){
    DWZ.init("./dwz_jui-master/dwz.frag.xml", {
        statusCode:{ok:200, error:300, timeout:301}, 
        keys: {statusCode:"statusCode", message:"message"},
        debug: true,
        callback:function(){
            initEnv();
            $("#themeList").theme({themeBase:"./dwz_jui-master/themes"});
        }
    });
});
</script>
```

### 添加页面结构

添加基本的页面布局结构：

```
<body>
    <div id="layout">
        <!-- 头部 -->
        <div id="header">
            <div class="headerNav">
                <a class="logo" href="#">DWZ学习实践</a>
                <ul class="themeList" id="themeList">
                    <li theme="default"><div class="selected">蓝色</div></li>
                    <li theme="green"><div>绿色</div></li>
                    <li theme="purple"><div>紫色</div></li>
                    <li theme="silver"><div>银色</div></li>
                    <li theme="azure"><div>天蓝</div></li>
                </ul>
            </div>
        </div>
        
        <!-- 内容区 -->
        <div id="container">
            <!-- 这里放置主要内容 -->
        </div>
    </div>
</body>
```

### 添加功能演示

在内容区添加一些基本功能的演示：

```
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
                    <h2>DWZ框架第一天学习实践</h2>
                    <a>演示DWZ框架的基础功能</a>
                </div>
            </div>
            
            <div class="pageFormContent" layoutH="80">
                <p>这是DWZ框架第一天学习实践页面。</p>
                
                <div class="divider"></div>
                
                <h2>DWZ框架基础功能演示：</h2>
                
                <div class="unit">
                    <a class="button" href="javascript:;" onclick="alertMsg.correct('这是一个正确的提示信息');">
                        <span>正确提示</span>
                    </a>
                    <a class="button" href="javascript:;" onclick="alertMsg.info('这是一个普通的信息提示');">
                        <span>信息提示</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
```

## 总结

通过今天的任务，您应该已经掌握了：

1. DWZ框架的基本概念和特点
2. DWZ框架的源码结构和各组件功能
3. 如何初始化DWZ框架
4. 核心组件和配置文件的作用
5. 如何创建一个简单的DWZ页面并集成基本功能

明天我们将深入学习导航和布局组件，包括navTab、菜单栏和面板组件等。