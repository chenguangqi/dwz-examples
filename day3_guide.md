# DWZ框架第三天学习指南

## 学习目标

通过今天的学习，您将：
1. 深入理解dialog组件的工作原理和使用方法
2. 掌握alert组件和提醒消息的使用技巧
3. 能够创建多种类型的弹窗页面
4. 熟练运用弹窗组件提升用户体验

## 任务1：学习dialog组件使用方法

### 什么是dialog组件？

dialog组件是DWZ框架中用于创建模态或非模态对话框的重要组件。它可以用来显示表单、确认信息、警告信息或其他内容，而无需跳转到新页面。dialog组件支持多种配置选项，可以满足各种弹窗需求。

### dialog组件工作原理

dialog组件基于以下技术实现：
1. **DOM操作**：动态创建和管理对话框元素
2. **定位计算**：自动计算对话框在页面中的位置
3. **遮罩层**：创建半透明遮罩层以突出对话框
4. **事件绑定**：处理对话框的打开、关闭等操作
5. **拖拽功能**：允许用户拖拽移动对话框位置

### dialog核心属性和方法

#### 属性
- `target="dialog"`：标识链接在dialog中打开
- `rel="identifier"`：对话框唯一标识符
- `title="对话框标题"`：对话框标题
- `width="数值"`：对话框宽度（像素）
- `height="数值"`：对话框高度（像素）
- `mask="true/false"`：是否显示遮罩层（默认false）
- `max="true/false"`：是否可最大化（默认false）
- `resizable="true/false"`：是否可调整大小（默认true）

#### JavaScript方法
- `$.pdialog.open(url, dlgid, title, options)`：打开对话框
- `$.pdialog.close(dlgid)`：关闭指定对话框
- `$.pdialog.reload(dlgid, options)`：重新加载对话框内容
- `$.pdialog.checkTimeout(json)`：检查会话超时

### dialog使用示例

#### 基本使用
```html
<!-- 基本弹窗 -->
<a href="form.html" target="dialog" title="表单窗口">打开表单</a>

<!-- 带尺寸设置的弹窗 -->
<a href="form.html" target="dialog" title="表单窗口" width="600" height="400">打开大表单</a>

<!-- 带遮罩层的弹窗 -->
<a href="alert.html" target="dialog" title="提醒窗口" mask="true">打开提醒</a>

<!-- 可最大化的弹窗 -->
<a href="content.html" target="dialog" title="内容窗口" max="true">打开内容</a>
```

#### JavaScript方式调用
```javascript
// 打开对话框
$.pdialog.open('form.html', 'formDialog', '表单窗口', {width: 600, height: 400});

// 关闭对话框
$.pdialog.close('formDialog');

// 重新加载对话框
$.pdialog.reload('formDialog');
```

### dialog结构分析

dialog的HTML结构包含以下关键部分：

```html
<div class="dialog" style="position:absolute;top:150px;left:300px;">
    <!-- 对话框头部 -->
    <div class="dialogHeader">
        <div class="dialogHeader_r">
            <div class="dialogHeader_c">
                <a class="close" href="#close">关闭</a>
                <a class="maximize" href="#maximize">最大化</a>
                <a class="restore" href="#restore">恢复</a>
                <a class="minimize" href="#minimize">最小化</a>
                <h1>对话框标题</h1>
            </div>
        </div>
    </div>
    
    <!-- 对话框内容 -->
    <div class="dialogContent layoutBox unitBox">
        <!-- 页面内容 -->
    </div>
    
    <!-- 对话框底部 -->
    <div class="dialogFooter">
        <div class="dialogFooter_r">
            <div class="dialogFooter_c"></div>
        </div>
    </div>
    
    <!-- 调整大小控件 -->
    <div class="resizable_h_l"></div>
    <div class="resizable_h_r"></div>
    <div class="resizable_h_c"></div>
    <div class="resizable_c_l"></div>
    <div class="resizable_c_r"></div>
    <div class="resizable_f_l"></div>
    <div class="resizable_f_r"></div>
    <div class="resizable_f_c"></div>
</div>
```

### 实践练习

让我们通过实际例子来掌握dialog的使用：

1. 创建一个新的HTML文件 `dialog_demo.html`
2. 引入DWZ框架所需的CSS和JS文件
3. 创建基本的页面结构
4. 添加几个可以打开dialog的链接
5. 测试不同的属性组合

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>dialog组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.drag.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialogDrag.js" type="text/javascript"></script>
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
            <h1>dialog组件演示</h1>
            
            <div class="unit">
                <a class="button" href="simple_form.html" target="dialog" title="简单表单">
                    <span>打开简单表单</span>
                </a>
                <a class="button" href="confirm_dialog.html" target="dialog" title="确认对话框" mask="true">
                    <span>打开确认对话框</span>
                </a>
                <a class="button" href="large_content.html" target="dialog" title="大内容窗口" width="800" height="600">
                    <span>打开大内容窗口</span>
                </a>
                <a class="button" href="javascript:void(0)" onclick="openJsDialog()">
                    <span>JavaScript方式打开</span>
                </a>
            </div>
        </div>
    </div>
    
    <script>
    function openJsDialog() {
        $.pdialog.open('dynamic_content.html', 'dynamicDialog', '动态内容', {width: 500, height: 300});
    }
    </script>
</body>
</html>
```

创建几个简单的演示页面：

simple_form.html:
```html
<div style="padding:20px;">
    <h2>简单表单</h2>
    <form>
        <div class="unit">
            <label>姓名：</label>
            <input type="text" name="name" />
        </div>
        <div class="unit">
            <label>邮箱：</label>
            <input type="email" name="email" />
        </div>
        <div class="unit">
            <label>备注：</label>
            <textarea name="remark" rows="3" cols="30"></textarea>
        </div>
        <div class="unit">
            <button type="button" onclick="$.pdialog.closeCurrent()">取消</button>
            <button type="submit">保存</button>
        </div>
    </form>
</div>
```

confirm_dialog.html:
```html
<div style="padding:20px; text-align:center;">
    <h2>确认操作</h2>
    <p>您确定要执行此操作吗？</p>
    <div class="buttonActive" style="display:inline-block;margin:0 10px;">
        <div class="buttonContent">
            <button type="button" onclick="alert('执行了操作');$.pdialog.closeCurrent()">确定</button>
        </div>
    </div>
    <div class="button" style="display:inline-block;margin:0 10px;">
        <div class="buttonContent">
            <button type="button" onclick="$.pdialog.closeCurrent()">取消</button>
        </div>
    </div>
</div>
```

## 任务2：掌握alert组件和提醒消息

### 什么是alert组件？

alert组件是DWZ框架中用于向用户显示各种提示信息的轻量级组件。它不需要用户交互即可自动消失，或者需要用户点击确认后才消失。alert组件包括成功、错误、警告、信息等多种类型的消息。

### alert组件类型

DWZ框架提供了多种类型的提醒消息：

1. **正确信息**：`alertMsg.correct(message, options)`
2. **错误信息**：`alertMsg.error(message, options)`
3. **警告信息**：`alertMsg.warn(message, options)`
4. **普通信息**：`alertMsg.info(message, options)`
5. **确认对话框**：`alertMsg.confirm(message, options)`

### alert组件使用方法

#### 基本使用
```javascript
// 正确信息
alertMsg.correct('操作成功');

// 错误信息
alertMsg.error('操作失败');

// 警告信息
alertMsg.warn('请注意');

// 普通信息
alertMsg.info('这是一条信息');

// 确认对话框
alertMsg.confirm('确定要删除吗？', {
    okCall: function() {
        alert('执行了删除操作');
    }
});
```

#### 带选项的使用
```javascript
// 带回调函数的提醒
alertMsg.correct('操作成功', {
    okCall: function() {
        // 点击确定后的回调
        console.log('用户点击了确定');
    }
});

// 带标题的提醒
alertMsg.info('这是一条信息', {
    title: '提示'
});
```

### alert组件结构分析

alert组件的HTML结构如下：

```html
<div class="alertDiv">
    <div class="alertInner">
        <h1>标题</h1>
        <div class="alertContent">
            <p>消息内容</p>
        </div>
        <div class="alertFooter">
            <div class="buttonActive">
                <div class="buttonContent">
                    <button>确定</button>
                </div>
            </div>
            <div class="button">
                <div class="buttonContent">
                    <button>取消</button>
                </div>
            </div>
        </div>
    </div>
    <div class="alertBg"></div>
</div>
```

### 实践练习

创建一个alert组件演示页面：

alert_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>alert组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
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
            <h1>alert组件演示</h1>
            
            <div class="unit">
                <a class="button" href="javascript:void(0)" onclick="showCorrect()">
                    <span>显示正确信息</span>
                </a>
                <a class="button" href="javascript:void(0)" onclick="showError()">
                    <span>显示错误信息</span>
                </a>
                <a class="button" href="javascript:void(0)" onclick="showWarn()">
                    <span>显示警告信息</span>
                </a>
                <a class="button" href="javascript:void(0)" onclick="showInfo()">
                    <span>显示普通信息</span>
                </a>
                <a class="button" href="javascript:void(0)" onclick="showConfirm()">
                    <span>显示确认对话框</span>
                </a>
            </div>
            
            <div class="unit">
                <h2>带回调函数的提醒</h2>
                <a class="button" href="javascript:void(0)" onclick="showWithCallback()">
                    <span>显示带回调的提醒</span>
                </a>
            </div>
        </div>
    </div>
    
    <script>
    function showCorrect() {
        alertMsg.correct('操作成功！');
    }
    
    function showError() {
        alertMsg.error('操作失败，请重试！');
    }
    
    function showWarn() {
        alertMsg.warn('请注意，这是一条警告信息！');
    }
    
    function showInfo() {
        alertMsg.info('这是一条普通的提示信息。');
    }
    
    function showConfirm() {
        alertMsg.confirm('确定要执行此操作吗？', {
            okCall: function() {
                alertMsg.correct('执行了操作！');
            }
        });
    }
    
    function showWithCallback() {
        alertMsg.info('点击确定按钮查看回调效果', {
            okCall: function() {
                alert('您点击了确定按钮！');
            }
        });
    }
    </script>
</body>
</html>
```

## 任务3：实践创建多种弹窗类型页面

现在我们将结合前面学到的dialog和alert组件知识，创建一个综合演示页面。

### 综合示例

full_popup_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>弹窗组件综合演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.drag.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.dialogDrag.js" type="text/javascript"></script>
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
            <h1>弹窗组件综合演示</h1>
            
            <div class="panel">
                <h1>Dialog组件演示</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="user_form.html" target="dialog" title="用户信息">
                            <span>打开用户表单</span>
                        </a>
                        <a class="button" href="product_detail.html" target="dialog" title="产品详情" width="700" height="500">
                            <span>打开产品详情</span>
                        </a>
                        <a class="button" href="confirm_action.html" target="dialog" title="确认操作" mask="true">
                            <span>打开确认操作</span>
                        </a>
                        <a class="button" href="javascript:void(0)" onclick="openDynamicDialog()">
                            <span>动态打开对话框</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>alert组件演示</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="javascript:void(0)" onclick="showSuccessMessage()">
                            <span>显示成功消息</span>
                        </a>
                        <a class="button" href="javascript:void(0)" onclick="showErrorMessage()">
                            <span>显示错误消息</span>
                        </a>
                        <a class="button" href="javascript:void(0)" onclick="showWarningMessage()">
                            <span>显示警告消息</span>
                        </a>
                        <a class="button" href="javascript:void(0)" onclick="showInfoMessage()">
                            <span>显示信息消息</span>
                        </a>
                    </div>
                    
                    <div class="unit">
                        <a class="button" href="javascript:void(0)" onclick="showConfirmDialog()">
                            <span>显示确认对话框</span>
                        </a>
                        <a class="button" href="javascript:void(0)" onclick="showCustomAlert()">
                            <span>显示自定义提醒</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>实际应用场景</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="delete_confirm.html" target="dialog" title="删除确认" mask="true">
                            <span>删除操作确认</span>
                        </a>
                        <a class="button" href="user_profile.html" target="dialog" title="用户资料" width="600" height="400">
                            <span>查看用户资料</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script>
    function openDynamicDialog() {
        $.pdialog.open('dynamic_content.html', 'dynamicDialog', '动态内容', {width: 500, height: 300});
    }
    
    function showSuccessMessage() {
        alertMsg.correct('操作成功完成！');
    }
    
    function showErrorMessage() {
        alertMsg.error('操作失败，请检查输入信息！');
    }
    
    function showWarningMessage() {
        alertMsg.warn('请注意，您的操作可能存在风险！');
    }
    
    function showInfoMessage() {
        alertMsg.info('这是一条提示信息，仅供参考。');
    }
    
    function showConfirmDialog() {
        alertMsg.confirm('确定要执行此操作吗？此操作不可撤销。', {
            okCall: function() {
                alertMsg.correct('操作已执行！');
            }
        });
    }
    
    function showCustomAlert() {
        alertMsg.info('这是一个自定义的提醒消息，带有标题和回调函数。', {
            title: '自定义提醒',
            okCall: function() {
                alert('您点击了确定按钮！');
            }
        });
    }
    </script>
</body>
</html>
```

创建支持页面：

user_form.html:
```html
<div style="padding:20px;">
    <h2>用户信息表单</h2>
    <form>
        <div class="unit">
            <label>用户名：</label>
            <input type="text" name="username" />
        </div>
        <div class="unit">
            <label>邮箱：</label>
            <input type="email" name="email" />
        </div>
        <div class="unit">
            <label>电话：</label>
            <input type="tel" name="phone" />
        </div>
        <div class="unit">
            <label>角色：</label>
            <select name="role">
                <option value="admin">管理员</option>
                <option value="user">普通用户</option>
                <option value="guest">访客</option>
            </select>
        </div>
        <div class="unit">
            <button type="button" onclick="$.pdialog.closeCurrent()">取消</button>
            <button type="submit">保存</button>
        </div>
    </form>
</div>
```

product_detail.html:
```html
<div style="padding:20px;">
    <h2>产品详情</h2>
    <div style="float:left; width: 300px; height: 300px; background: #f0f0f0; margin-right: 20px; text-align: center; line-height: 300px;">
        产品图片
    </div>
    <div style="float:left; width: calc(100% - 320px);">
        <table class="list" width="100%">
            <tbody>
                <tr>
                    <td>产品名称：</td>
                    <td>iPhone 13 Pro</td>
                </tr>
                <tr>
                    <td>产品型号：</td>
                    <td>A2639</td>
                </tr>
                <tr>
                    <td>价格：</td>
                    <td>￥8999</td>
                </tr>
                <tr>
                    <td>库存：</td>
                    <td>150台</td>
                </tr>
                <tr>
                    <td>颜色：</td>
                    <td>石墨色、金色、银色、远峰蓝色</td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td colspan="3">iPhone 13 Pro配备超 Retina XDR 显示屏，A15 仿生芯片，以及突破性的摄像头系统。</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div style="clear:both; margin-top: 20px; text-align: center;">
        <button type="button" onclick="$.pdialog.closeCurrent()">关闭</button>
        <button type="button">加入购物车</button>
    </div>
</div>
```

confirm_action.html:
```html
<div style="padding:30px; text-align:center;">
    <div class="alertInfo" style="margin-bottom: 20px;">
        <h2>重要操作确认</h2>
        <p>您即将执行一个重要操作，请确认是否继续。</p>
    </div>
    <p>操作描述：此操作将永久删除选定的数据，且无法恢复。</p>
    <div style="margin-top: 30px;">
        <div class="buttonActive" style="display:inline-block;margin:0 10px;">
            <div class="buttonContent">
                <button type="button" onclick="executeAction()">确定执行</button>
            </div>
        </div>
        <div class="button" style="display:inline-block;margin:0 10px;">
            <div class="buttonContent">
                <button type="button" onclick="$.pdialog.closeCurrent()">取消</button>
            </div>
        </div>
    </div>
</div>

<script>
function executeAction() {
    alertMsg.correct('操作已成功执行！', {
        okCall: function() {
            $.pdialog.closeCurrent();
        }
    });
}
</script>
```

## 总结

通过今天的任务，您应该已经掌握了：

1. **dialog组件**：理解了其工作原理和使用方法，能够创建和管理各种类型的对话框
2. **alert组件**：学会了使用各种类型的提醒消息，包括正确、错误、警告和信息提示
3. **综合实践**：能够结合使用dialog和alert组件创建丰富的用户交互体验

明天我们将学习表单相关组件，包括表单验证机制和datepicker、combox等表单组件的使用方法。
