# DWZ框架第六天学习指南

## 学习目标

通过今天的学习，您将：
1. 深入理解Ajax集成和异步请求处理的工作原理和使用方法
2. 掌握数据联动功能(lookup, suggest)的使用技巧
3. 能够实现异步数据交互功能
4. 熟练运用Ajax组件提升用户体验和数据交互效率

## 任务1：学习Ajax集成和异步请求处理

### 什么是Ajax？

Ajax（Asynchronous JavaScript and XML）是一种在不重新加载整个页面的情况下，能够更新部分网页的技术。通过在后台与服务器进行少量数据交换，Ajax可以使网页实现异步更新。

### DWZ框架中的Ajax处理

DWZ框架对Ajax进行了很好的封装，提供了以下功能：

1. **统一的Ajax请求处理**：通过内置方法处理各种Ajax请求
2. **标准化响应处理**：统一处理服务器返回的数据格式
3. **错误处理机制**：提供完善的错误处理和用户提示
4. **表单提交处理**：支持Ajax方式提交表单数据

### Ajax核心方法和属性

#### 核心方法
- `DWZ.ajaxTodo(url, callback)`：处理简单的Ajax请求
- `DWZ.ajaxForm(form, callback)`：Ajax方式提交表单
- `navTabAjaxDone(json)`：navTab页面Ajax处理完成回调
- `dialogAjaxDone(json)`：dialog页面Ajax处理完成回调

#### Ajax属性
- `target="ajaxTodo"`：标识链接使用Ajax处理
- `target="ajaxForm"`：标识表单使用Ajax提交
- `rel="标识"`：关联组件标识
- `callback="函数名"`：处理完成后的回调函数

### Ajax响应数据格式

DWZ框架约定Ajax响应数据格式如下：
```json
{
  "statusCode": 200,    // 状态码(200:成功, 300:失败, 301:超时)
  "message": "操作成功", // 提示信息
  "navTabId": "",       // 需要刷新的navTab标识
  "dialogId": "",       // 需要关闭的dialog标识
  "forward": "",        // 跳转地址
  "forwardConfirm":""   // 跳转确认信息
}
```

### Ajax使用示例

#### 基本Ajax链接使用
```html
<!-- 删除操作 -->
<a href="delete_user.php?id=1" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>

<!-- 刷新当前navTab -->
<a href="refresh_data.php" target="ajaxTodo" rel="refreshData">刷新数据</a>
```

#### Ajax表单提交使用
```html
<form method="post" action="save_user.php" class="pageForm required-validate" target="ajaxForm">
    <div class="unit">
        <label>用户名：</label>
        <input type="text" name="username" class="required" />
    </div>
    
    <div class="unit">
        <label>邮箱：</label>
        <input type="email" name="email" class="required email" />
    </div>
    
    <div class="unit">
        <button type="submit">保存</button>
    </div>
</form>
```

#### JavaScript方式调用Ajax
```javascript
// 直接调用Ajax请求
DWZ.ajaxTodo("delete_user.php?id=1", function(data){
    if(data.statusCode == 200){
        alertMsg.correct(data.message);
    }else{
        alertMsg.error(data.message);
    }
});

// Ajax提交表单
DWZ.ajaxForm(formElement, function(data){
    if(data.statusCode == 200){
        alertMsg.correct(data.message);
        navTab.reload(); // 刷新当前navTab
    }else{
        alertMsg.error(data.message);
    }
});
```

### 实践练习

让我们通过实际例子来掌握Ajax的使用：

1. 创建一个新的HTML文件 `ajax_demo.html`
2. 引入DWZ框架所需的CSS和JS文件
3. 创建模拟的Ajax处理页面
4. 测试不同的Ajax交互场景

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajax集成演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.alertMsg.js" type="text/javascript"></script>
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
            <h1>Ajax集成演示</h1>
            
            <div class="panel">
                <h1>基本Ajax操作</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="ajax_success.json" target="ajaxTodo" title="确定要执行操作吗?">
                            <span>成功操作</span>
                        </a>
                        <a class="button" href="ajax_error.json" target="ajaxTodo">
                            <span>失败操作</span>
                        </a>
                        <a class="button" href="ajax_refresh.json" target="ajaxTodo" rel="refreshData">
                            <span>刷新数据</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>Ajax表单提交</h1>
                <div>
                    <form method="post" action="ajax_form_submit.json" class="pageForm required-validate" target="ajaxForm">
                        <div class="unit">
                            <label>用户名：</label>
                            <input type="text" name="username" class="required" />
                        </div>
                        
                        <div class="unit">
                            <label>邮箱：</label>
                            <input type="email" name="email" class="required email" />
                        </div>
                        
                        <div class="unit">
                            <button type="submit">Ajax提交</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="panel">
                <h1>数据列表（可刷新）</h1>
                <div id="refreshData">
                    <table class="table" width="100%">
                        <thead>
                            <tr>
                                <th width="50">ID</th>
                                <th>姓名</th>
                                <th>邮箱</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>张三</td>
                                <td>zhangsan@example.com</td>
                                <td>
                                    <a href="ajax_delete_success.json" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                </td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>李四</td>
                                <td>lisi@example.com</td>
                                <td>
                                    <a href="ajax_delete_success.json" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## 任务2：掌握数据联动功能(lookup, suggest)

### lookup组件

lookup组件是DWZ框架提供的数据选择组件，通常用于从弹出窗口中选择数据并回填到当前表单中。

#### lookup使用方法
```html
<!-- lookup输入框 -->
<input type="text" name="orgName" readonly="readonly" />

<!-- lookup按钮 -->
<a class="btnLook" href="lookup_page.html" lookupGroup="org">查找带回</a>

<!-- 隐藏字段用于存储选中值 -->
<input type="hidden" name="org.id" lookupGroup="org" />
```

#### lookupGroup属性
- 用于关联一组相关的lookup字段
- 弹出窗口返回的数据会根据lookupGroup自动填充相应字段

### suggest组件

suggest组件是DWZ框架提供的自动补全组件，当用户在输入框中输入内容时，会自动显示匹配的建议列表。

#### suggest使用方法
```html
<!-- suggest输入框 -->
<input type="text" name="userName" suggestUrl="suggest_users.php?key={value}" suggestFields="name,email" />

<!-- suggestFields指定建议列表中显示的字段 -->
```

#### suggest属性
- `suggestUrl`：获取建议数据的URL
- `suggestFields`：指定建议列表中显示的字段
- `lookupGroup`：与lookup组件配合使用

### 实践练习

创建一个数据联动功能演示页面：

data_linkage_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据联动功能演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.database.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.suggest.js" type="text/javascript"></script>
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
            <h1>数据联动功能演示</h1>
            
            <div class="panel">
                <h1>Lookup数据选择</h1>
                <div>
                    <div class="unit">
                        <label>组织机构：</label>
                        <input type="text" name="org.name" readonly="readonly" />
                        <a class="btnLook" href="lookup_org.html" lookupGroup="org">查找带回</a>
                        <input type="hidden" name="org.id" lookupGroup="org" />
                    </div>
                    
                    <div class="unit">
                        <label>用户信息：</label>
                        <input type="text" name="user.name" readonly="readonly" />
                        <a class="btnLook" href="lookup_user.html" lookupGroup="user">查找带回</a>
                        <input type="hidden" name="user.id" lookupGroup="user" />
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>Suggest自动补全</h1>
                <div>
                    <div class="unit">
                        <label>用户搜索：</label>
                        <input type="text" name="userName" suggestUrl="suggest_users.json?key={value}" suggestFields="name,email" lookupGroup="suggestUser" />
                        <input type="hidden" name="userId" lookupGroup="suggestUser" />
                    </div>
                    
                    <div class="unit">
                        <label>产品搜索：</label>
                        <input type="text" name="productName" suggestUrl="suggest_products.json?key={value}" suggestFields="name,code" lookupGroup="suggestProduct" />
                        <input type="hidden" name="productId" lookupGroup="suggestProduct" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## 任务3：实践实现异步数据交互功能

现在我们将结合前面学到的Ajax和数据联动知识，创建一个综合演示页面。

### 综合示例

ajax_integration_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajax集成综合演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.database.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.suggest.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.alertMsg.js" type="text/javascript"></script>
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
            <h1>Ajax集成综合演示</h1>
            
            <div class="alertInfo">
                <p>本页面演示了DWZ框架中Ajax集成的各种功能，包括基本Ajax操作、表单提交、数据选择等。</p>
            </div>
            
            <div class="panel">
                <h1>基本Ajax操作</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="ajax_success.json" target="ajaxTodo" title="确定要执行操作吗?">
                            <span>添加数据</span>
                        </a>
                        <a class="button" href="ajax_refresh.json" target="ajaxTodo" rel="dataListContainer">
                            <span>刷新列表</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>Ajax表单提交</h1>
                <div>
                    <form method="post" action="ajax_form_submit.json" class="pageForm required-validate" target="ajaxForm">
                        <div class="unit">
                            <label>姓名：</label>
                            <input type="text" name="name" class="required" />
                        </div>
                        
                        <div class="unit">
                            <label>邮箱：</label>
                            <input type="email" name="email" class="required email" />
                        </div>
                        
                        <div class="unit">
                            <label>部门：</label>
                            <input type="text" name="departmentName" readonly="readonly" />
                            <a class="btnLook" href="lookup_department.html" lookupGroup="department">选择部门</a>
                            <input type="hidden" name="departmentId" lookupGroup="department" />
                        </div>
                        
                        <div class="unit">
                            <label>经理：</label>
                            <input type="text" name="managerName" suggestUrl="suggest_managers.json?key={value}" suggestFields="name,email" lookupGroup="manager" />
                            <input type="hidden" name="managerId" lookupGroup="manager" />
                        </div>
                        
                        <div class="unit">
                            <button type="submit">提交表单</button>
                        </div>
                    </form>
                </div>
            </div>
            
            <div class="panel">
                <h1>数据列表</h1>
                <div id="dataListContainer">
                    <table class="table" width="100%">
                        <thead>
                            <tr>
                                <th width="30"><input type="checkbox" class="checkboxCtrl" group="ids" /></th>
                                <th width="50">ID</th>
                                <th>姓名</th>
                                <th>邮箱</th>
                                <th>部门</th>
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
                                <td>
                                    <a href="ajax_edit.json?id=1" target="dialog" title="编辑用户" width="600" height="400">编辑</a>
                                    <a href="ajax_delete.json?id=1" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name="ids" value="2" /></td>
                                <td>2</td>
                                <td>李四</td>
                                <td>lisi@example.com</td>
                                <td>销售部</td>
                                <td>
                                    <a href="ajax_edit.json?id=2" target="dialog" title="编辑用户" width="600" height="400">编辑</a>
                                    <a href="ajax_delete.json?id=2" target="ajaxTodo" title="确定要删除该用户吗?">删除</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="panelBar">
                        <div class="pages">
                            <span>显示</span>
                            <select class="combox" name="numPerPage">
                                <option value="20">20</option>
                                <option value="50" selected>50</option>
                            </select>
                            <span>条，共<label class="totalCount">2</label>条</span>
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

1. **Ajax集成和异步请求处理**：理解了其工作原理和使用方法，能够处理各种Ajax请求和表单提交
2. **数据联动功能**：学会了使用lookup和suggest组件实现数据选择和自动补全功能
3. **综合实践**：能够结合使用Ajax和数据联动功能创建丰富的数据交互界面

明天我们将学习扩展功能与主题，包括扩展插件机制和主题切换功能。