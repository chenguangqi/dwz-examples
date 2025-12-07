# DWZ框架第四天学习指南

## 学习目标

通过今天的学习，您将：
1. 深入理解表单验证机制的工作原理和使用方法
2. 掌握datepicker、combox等表单组件的使用技巧
3. 能够创建功能完整的表单页面
4. 熟练运用表单组件提升用户体验和数据准确性

## 任务1：学习表单验证机制

### 什么是表单验证？

表单验证是确保用户输入数据符合预期格式和规则的过程。在Web应用中，表单验证对于保证数据质量和系统安全至关重要。DWZ框架集成了强大的表单验证功能，可以帮助开发者轻松实现各种验证需求。

### 表单验证工作原理

DWZ框架的表单验证基于以下技术实现：
1. **HTML属性驱动**：通过在表单元素上添加特定属性来定义验证规则
2. **实时验证**：在用户输入过程中实时检查数据有效性
3. **提交时验证**：在表单提交前进行全面验证
4. **错误提示**：以友好的方式向用户展示验证结果

### 表单验证核心属性

#### 基本验证属性
- `class="required"`：必填字段
- `minlength="n"`：最小长度
- `maxlength="n"`：最大长度
- `equalto="field"`：与另一个字段值相等
- `remote="url"`：远程验证

#### 类型验证属性
- `type="email"`：电子邮件格式
- `type="url"`：网址格式
- `type="date"`：日期格式
- `type="number"`：数字格式
- `type="digits"`：整数格式

#### 自定义验证属性
- `customvalid="function"`：自定义验证函数
- `alt="提示信息"`：自定义提示信息

### 表单验证使用示例

#### 基本使用
```html
<form method="post" action="save.php" class="pageForm required-validate">
    <div class="unit">
        <label>用户名：</label>
        <input type="text" name="username" class="required" minlength="3" maxlength="20" alt="请输入3-20位用户名"/>
    </div>
    
    <div class="unit">
        <label>邮箱：</label>
        <input type="email" name="email" class="required email" alt="请输入正确的邮箱地址"/>
    </div>
    
    <div class="unit">
        <label>密码：</label>
        <input type="password" id="password" name="password" class="required" minlength="6" alt="密码至少6位"/>
    </div>
    
    <div class="unit">
        <label>确认密码：</label>
        <input type="password" name="confirm_password" class="required" equalto="#password" alt="两次输入的密码不一致"/>
    </div>
    
    <div class="unit">
        <label>年龄：</label>
        <input type="number" name="age" min="1" max="120" alt="请输入有效的年龄"/>
    </div>
    
    <div class="unit">
        <button type="submit">保存</button>
        <button type="button" class="close">取消</button>
    </div>
</form>
```

#### JavaScript方式调用
```javascript
// 手动触发表单验证
$("form").valid();

// 检查表单是否有效
if ($("form").valid()) {
    // 表单验证通过，可以提交
    $("form").submit();
}
```

### 实践练习

让我们通过实际例子来掌握表单验证的使用：

1. 创建一个新的HTML文件 `form_validation_demo.html`
2. 引入DWZ框架所需的CSS和JS文件
3. 创建包含各种验证规则的表单
4. 测试不同的验证场景

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>表单验证演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/jquery.validate.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.validate.method.js" type="text/javascript"></script>
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
            <h1>表单验证演示</h1>
            
            <form method="post" action="save_user.php" class="pageForm required-validate">
                <div class="panel">
                    <h1>基本信息</h1>
                    <div>
                        <div class="unit">
                            <label>用户名：</label>
                            <input type="text" name="username" class="required" minlength="3" maxlength="20" alt="请输入3-20位用户名"/>
                        </div>
                        
                        <div class="unit">
                            <label>邮箱：</label>
                            <input type="email" name="email" class="required email" alt="请输入正确的邮箱地址"/>
                        </div>
                        
                        <div class="unit">
                            <label>密码：</label>
                            <input type="password" id="password" name="password" class="required" minlength="6" alt="密码至少6位"/>
                        </div>
                        
                        <div class="unit">
                            <label>确认密码：</label>
                            <input type="password" name="confirm_password" class="required" equalto="#password" alt="两次输入的密码不一致"/>
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>详细信息</h1>
                    <div>
                        <div class="unit">
                            <label>真实姓名：</label>
                            <input type="text" name="realname" />
                        </div>
                        
                        <div class="unit">
                            <label>手机号：</label>
                            <input type="tel" name="mobile" class="phone" alt="请输入正确的手机号"/>
                        </div>
                        
                        <div class="unit">
                            <label>年龄：</label>
                            <input type="number" name="age" min="1" max="120" alt="请输入有效的年龄"/>
                        </div>
                        
                        <div class="unit">
                            <label>个人简介：</label>
                            <textarea name="bio" maxlength="200" alt="简介不能超过200字"></textarea>
                        </div>
                    </div>
                </div>
                
                <div class="unit">
                    <button type="submit">保存</button>
                    <button type="button" class="close">取消</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

## 任务2：掌握datepicker、combox等表单组件

### datepicker组件

datepicker是DWZ框架提供的日期选择组件，可以让用户通过图形化界面选择日期，避免手动输入可能出现的格式错误。

#### datepicker使用方法
```html
<!-- 基本使用 -->
<input type="text" name="birthday" class="date" />

<!-- 指定日期格式 -->
<input type="text" name="startdate" class="date" dateFmt="yyyy-MM-dd" />

<!-- 限制日期范围 -->
<input type="text" name="enddate" class="date" minDate="{%y-1}-%M-{%d}" maxDate="{%y+1}-%M-{%d}" />
```

#### datepicker属性
- `class="date"`：标识为日期选择器
- `dateFmt="格式"`：指定日期格式（如：yyyy-MM-dd）
- `minDate="日期"`：最小可选日期
- `maxDate="日期"`：最大可选日期

### combox组件

combox是DWZ框架提供的下拉选择框组件，支持本地数据和远程数据加载，还可以支持搜索功能。

#### combox使用方法
```html
<!-- 基本使用 -->
<select name="department" class="combox">
    <option value="">请选择部门</option>
    <option value="tech">技术部</option>
    <option value="sales">销售部</option>
    <option value="hr">人事部</option>
</select>

<!-- 远程数据加载 -->
<select name="city" class="combox" ref="district" url="get_district.php?cityId={value}">
    <option value="">请选择城市</option>
    <option value="1">北京</option>
    <option value="2">上海</option>
</select>

<!-- 联动下拉框 -->
<select name="district" class="combox" refUrl="get_district.php?cityId={value}">
    <option value="">请选择区县</option>
</select>
```

#### combox属性
- `class="combox"`：标识为下拉选择框组件
- `ref="关联组件"`：关联的下级组件名称
- `url="数据地址"`：远程数据加载地址
- `refUrl="联动地址"`：联动数据加载地址

### 其他表单组件

DWZ框架还提供了许多其他实用的表单组件：

1. **spinner**：数字微调器
```html
<input type="text" name="quantity" class="spinner" min="1" max="100" />
```

2. **checkbox**：复选框组件
```html
<label><input type="checkbox" name="hobby" value="reading" />阅读</label>
<label><input type="checkbox" name="hobby" value="music" />音乐</label>
```

3. **radio**：单选框组件
```html
<label><input type="radio" name="gender" value="male" />男</label>
<label><input type="radio" name="gender" value="female" />女</label>
```

### 实践练习

创建一个包含各种表单组件的演示页面：

form_components_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>表单组件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/jquery.validate.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.combox.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.spinner.js" type="text/javascript"></script>
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
            <h1>表单组件演示</h1>
            
            <form method="post" action="save_form.php" class="pageForm required-validate">
                <div class="panel">
                    <h1>日期选择器</h1>
                    <div>
                        <div class="unit">
                            <label>出生日期：</label>
                            <input type="text" name="birthday" class="date required" dateFmt="yyyy-MM-dd" />
                        </div>
                        
                        <div class="unit">
                            <label>入职日期：</label>
                            <input type="text" name="hiredate" class="date" minDate="2000-01-01" maxDate="2030-12-31" />
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>下拉选择框</h1>
                    <div>
                        <div class="unit">
                            <label>部门：</label>
                            <select name="department" class="combox required">
                                <option value="">请选择部门</option>
                                <option value="tech">技术部</option>
                                <option value="sales">销售部</option>
                                <option value="hr">人事部</option>
                                <option value="finance">财务部</option>
                            </select>
                        </div>
                        
                        <div class="unit">
                            <label>省份：</label>
                            <select name="province" class="combox" ref="city">
                                <option value="">请选择省份</option>
                                <option value="beijing">北京市</option>
                                <option value="shanghai">上海市</option>
                                <option value="guangdong">广东省</option>
                            </select>
                        </div>
                        
                        <div class="unit">
                            <label>城市：</label>
                            <select name="city" class="combox" ref="district">
                                <option value="">请选择城市</option>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>其他组件</h1>
                    <div>
                        <div class="unit">
                            <label>年龄：</label>
                            <input type="text" name="age" class="spinner required digits" min="1" max="120" />
                        </div>
                        
                        <div class="unit">
                            <label>性别：</label>
                            <label><input type="radio" name="gender" value="male" class="required" />男</label>
                            <label><input type="radio" name="gender" value="female" />女</label>
                        </div>
                        
                        <div class="unit">
                            <label>爱好：</label>
                            <label><input type="checkbox" name="hobby" value="reading" />阅读</label>
                            <label><input type="checkbox" name="hobby" value="music" />音乐</label>
                            <label><input type="checkbox" name="hobby" value="sports" />运动</label>
                            <label><input type="checkbox" name="hobby" value="travel" />旅游</label>
                        </div>
                    </div>
                </div>
                
                <div class="unit">
                    <button type="submit">保存</button>
                    <button type="button" class="close">取消</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

## 任务3：实践创建完整表单页面

现在我们将结合前面学到的表单验证和组件知识，创建一个完整的用户注册表单页面。

### 综合示例

complete_form_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>完整表单页面演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/jquery.validate.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.combox.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.spinner.js" type="text/javascript"></script>
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
    
    // 模拟联动数据
    function loadCities() {
        var province = $("select[name='province']").val();
        var citySelect = $("select[name='city']");
        citySelect.empty().append('<option value="">请选择城市</option>');
        
        if (province === "beijing") {
            citySelect.append('<option value="beijing">北京市</option>');
        } else if (province === "shanghai") {
            citySelect.append('<option value="shanghai">上海市</option>');
        } else if (province === "guangdong") {
            citySelect.append('<option value="guangzhou">广州市</option>');
            citySelect.append('<option value="shenzhen">深圳市</option>');
        }
        
        // 重新初始化combox
        citySelect.combox();
    }
    </script>
</head>
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>完整表单页面演示</h1>
            <div class="alertInfo">
                <p>这是一个包含各种表单组件和验证规则的完整示例。</p>
            </div>
            
            <form method="post" action="register.php" class="pageForm required-validate">
                <div class="panel">
                    <h1>账户信息</h1>
                    <div>
                        <div class="unit">
                            <label>用户名：</label>
                            <input type="text" name="username" class="required alphanumeric" minlength="3" maxlength="20" alt="请输入3-20位字母数字组合"/>
                        </div>
                        
                        <div class="unit">
                            <label>邮箱：</label>
                            <input type="email" name="email" class="required email" alt="请输入正确的邮箱地址"/>
                        </div>
                        
                        <div class="unit">
                            <label>密码：</label>
                            <input type="password" id="password" name="password" class="required" minlength="6" maxlength="20" alt="密码长度为6-20位"/>
                        </div>
                        
                        <div class="unit">
                            <label>确认密码：</label>
                            <input type="password" name="confirm_password" class="required" equalto="#password" alt="两次输入的密码不一致"/>
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>个人信息</h1>
                    <div>
                        <div class="unit">
                            <label>真实姓名：</label>
                            <input type="text" name="realname" class="required" />
                        </div>
                        
                        <div class="unit">
                            <label>性别：</label>
                            <label><input type="radio" name="gender" value="male" class="required" />男</label>
                            <label><input type="radio" name="gender" value="female" />女</label>
                        </div>
                        
                        <div class="unit">
                            <label>出生日期：</label>
                            <input type="text" name="birthday" class="date required" dateFmt="yyyy-MM-dd" />
                        </div>
                        
                        <div class="unit">
                            <label>年龄：</label>
                            <input type="text" name="age" class="spinner required digits" min="1" max="120" />
                        </div>
                        
                        <div class="unit">
                            <label>手机号：</label>
                            <input type="tel" name="mobile" class="required phone" alt="请输入正确的手机号"/>
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>地址信息</h1>
                    <div>
                        <div class="unit">
                            <label>省份：</label>
                            <select name="province" class="combox required" onchange="loadCities()">
                                <option value="">请选择省份</option>
                                <option value="beijing">北京市</option>
                                <option value="shanghai">上海市</option>
                                <option value="guangdong">广东省</option>
                            </select>
                        </div>
                        
                        <div class="unit">
                            <label>城市：</label>
                            <select name="city" class="combox required">
                                <option value="">请选择城市</option>
                            </select>
                        </div>
                        
                        <div class="unit">
                            <label>详细地址：</label>
                            <textarea name="address" class="required" rows="3" cols="50"></textarea>
                        </div>
                    </div>
                </div>
                
                <div class="panel">
                    <h1>其他信息</h1>
                    <div>
                        <div class="unit">
                            <label>爱好：</label>
                            <label><input type="checkbox" name="hobby" value="reading" />阅读</label>
                            <label><input type="checkbox" name="hobby" value="music" />音乐</label>
                            <label><input type="checkbox" name="hobby" value="sports" />运动</label>
                            <label><input type="checkbox" name="hobby" value="travel" />旅游</label>
                        </div>
                        
                        <div class="unit">
                            <label>个人简介：</label>
                            <textarea name="bio" maxlength="200" alt="简介不能超过200字"></textarea>
                        </div>
                        
                        <div class="unit">
                            <label>同意协议：</label>
                            <label><input type="checkbox" name="agreement" class="required" alt="请先同意用户协议"/>我已阅读并同意用户协议</label>
                        </div>
                    </div>
                </div>
                
                <div class="unit">
                    <button type="submit">注册</button>
                    <button type="reset">重置</button>
                    <button type="button" class="close">取消</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
```

## 总结

通过今天的任务，您应该已经掌握了：

1. **表单验证机制**：理解了其工作原理和使用方法，能够为表单字段添加各种验证规则
2. **表单组件**：学会了使用datepicker、combox等常用表单组件
3. **综合实践**：能够创建功能完整、验证齐全的表单页面

明天我们将学习数据展示组件，包括表格和分页组件以及树形组件的使用方法。