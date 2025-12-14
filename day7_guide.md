# DWZ框架第七天学习指南

## 学习目标

通过今天的学习，您将：
1. 深入理解扩展插件机制的工作原理和使用方法
2. 掌握主题切换功能的实现方式
3. 能够实现文件上传和拖拽排序功能
4. 熟练运用扩展功能提升应用的用户体验

## 任务1：学习扩展插件机制

### DWZ框架插件机制概述

DWZ框架具有良好的扩展性，支持多种插件来增强功能。这些插件包括文件上传、图表展示、富文本编辑器等。框架通过统一的接口和配置方式来管理和使用这些插件。

### 主要扩展插件

1. **uploadify** - jQuery文件上传插件
2. **xheditor** - 在线HTML编辑器
3. **echarts** - 百度图表插件
4. **sortDrag** - 拖拽排序插件

### 插件集成方式

插件通常通过以下方式集成到DWZ框架中：
1. 引入插件的CSS和JS文件
2. 在HTML中使用插件特定的类或属性
3. 通过JavaScript进行初始化配置

### 实践练习

让我们通过实际例子来掌握插件的使用：

upload_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件上传插件演示</title>
    <link href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="./dwz_jui-master/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
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
            <h1>文件上传插件演示</h1>
            
            <div class="panel">
                <h1>基本文件上传</h1>
                <div>
                    <div class="unit">
                        <input type="file" name="file" id="file_upload" />
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>多文件上传</h1>
                <div>
                    <div class="unit">
                        <input type="file" name="files" id="files_upload" multiple="true" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    $(function() {
        $('#file_upload').uploadify({
            'swf': './dwz_jui-master/uploadify/scripts/uploadify.swf',
            'uploader': 'upload_handler.php',
            'buttonText': '选择文件',
            'onUploadSuccess': function(file, data, response) {
                alert('文件 ' + file.name + ' 上传成功!');
            }
        });
        
        $('#files_upload').uploadify({
            'swf': './dwz_jui-master/uploadify/scripts/uploadify.swf',
            'uploader': 'upload_handler.php',
            'buttonText': '选择多个文件',
            'multi': true,
            'onUploadSuccess': function(file, data, response) {
                alert('文件 ' + file.name + ' 上传成功!');
            }
        });
    });
    </script>
</body>
</html>
```

## 任务2：掌握主题切换功能

### DWZ主题机制

DWZ框架支持多种主题切换，用户可以根据喜好选择不同的界面风格。主题通过CSS文件实现，框架提供了一套完整的主题切换机制。

### 支持的主题

DWZ框架默认提供以下几种主题：
1. **default** - 默认蓝色主题
2. **green** - 绿色主题
3. **purple** - 紫色主题
4. **silver** - 银色主题
5. **azure** - 天蓝色主题

### 主题切换实现原理

主题切换通过以下方式实现：
1. 为主题CSS文件建立统一的命名规范
2. 通过JavaScript动态切换引用的CSS文件
3. 使用Cookie或LocalStorage保存用户选择的主题

### 主题切换使用示例

theme_switch_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>主题切换演示</title>
    <link id="themeLink" href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.theme.js" type="text/javascript"></script>
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
    
    function switchTheme(themeName) {
        $('#themeLink').attr('href', './dwz_jui-master/themes/' + themeName + '/style.css');
        // 保存用户选择的主题到cookie
        $.cookie('dwz_theme', themeName, { expires: 30, path: '/' });
    }
    </script>
</head>
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>主题切换演示</h1>
            
            <div class="panel">
                <h1>选择主题</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="javascript:switchTheme('default')">
                            <span>默认主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('green')">
                            <span>绿色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('purple')">
                            <span>紫色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('silver')">
                            <span>银色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('azure')">
                            <span>天蓝主题</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>界面元素展示</h1>
                <div>
                    <div class="unit">
                        <h2>按钮样式</h2>
                        <a class="button" href="#"><span>普通按钮</span></a>
                        <a class="buttonActive" href="#"><span>激活按钮</span></a>
                    </div>
                    
                    <div class="unit">
                        <h2>表单元素</h2>
                        <input type="text" placeholder="文本输入框" />
                        <select>
                            <option>选项1</option>
                            <option>选项2</option>
                        </select>
                    </div>
                    
                    <div class="unit">
                        <h2>表格样式</h2>
                        <table class="table" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>名称</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>项目1</td>
                                    <td><a href="#">编辑</a></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>项目2</td>
                                    <td><a href="#">编辑</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
```

## 任务3：实践实现文件上传和拖拽排序功能

### 文件上传功能

DWZ框架通过集成uploadify插件实现文件上传功能，支持多文件上传、进度显示、文件类型限制等功能。

### 拖拽排序功能

DWZ框架提供了sortDrag插件，可以实现列表项的拖拽排序功能，适用于任务列表、菜单排序等场景。

### 综合示例

extended_features_demo.html:
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>扩展功能综合演示</title>
    <link id="themeLink" href="./dwz_jui-master/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/themes/css/core.css" rel="stylesheet" type="text/css" />
    <link href="./dwz_jui-master/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./dwz_jui-master/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="./dwz_jui-master/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="./dwz_jui-master/js/dwz.core.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.theme.js" type="text/javascript"></script>
    <script src="./dwz_jui-master/js/dwz.sortDrag.js" type="text/javascript"></script>
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
    
    function switchTheme(themeName) {
        $('#themeLink').attr('href', './dwz_jui-master/themes/' + themeName + '/style.css');
        $.cookie('dwz_theme', themeName, { expires: 30, path: '/' });
    }
    </script>
</head>
<body>
    <div id="layout">
        <div style="padding:20px;">
            <h1>扩展功能综合演示</h1>
            
            <div class="alertInfo">
                <p>本页面演示了DWZ框架的扩展功能，包括主题切换、文件上传和拖拽排序。</p>
            </div>
            
            <div class="panel">
                <h1>主题切换</h1>
                <div>
                    <div class="unit">
                        <a class="button" href="javascript:switchTheme('default')">
                            <span>默认主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('green')">
                            <span>绿色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('purple')">
                            <span>紫色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('silver')">
                            <span>银色主题</span>
                        </a>
                        <a class="button" href="javascript:switchTheme('azure')">
                            <span>天蓝主题</span>
                        </a>
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>文件上传</h1>
                <div>
                    <div class="unit">
                        <input type="file" name="file" id="file_upload" />
                    </div>
                </div>
            </div>
            
            <div class="panel">
                <h1>拖拽排序</h1>
                <div>
                    <div class="unit">
                        <ul class="sortable-list" style="list-style: none; padding: 0;">
                            <li class="sort-item" style="padding: 10px; margin: 5px 0; background: #f0f0f0; cursor: move;">
                                项目 1
                            </li>
                            <li class="sort-item" style="padding: 10px; margin: 5px 0; background: #f0f0f0; cursor: move;">
                                项目 2
                            </li>
                            <li class="sort-item" style="padding: 10px; margin: 5px 0; background: #f0f0f0; cursor: move;">
                                项目 3
                            </li>
                            <li class="sort-item" style="padding: 10px; margin: 5px 0; background: #f0f0f0; cursor: move;">
                                项目 4
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    $(function() {
        $('#file_upload').uploadify({
            'swf': './dwz_jui-master/uploadify/scripts/uploadify.swf',
            'uploader': 'upload_handler.php',
            'buttonText': '选择文件',
            'onUploadSuccess': function(file, data, response) {
                alert('文件 ' + file.name + ' 上传成功!');
            }
        });
        
        $('.sortable-list').sortDrag({
            selector: '.sort-item',
            replace: true
        });
    });
    </script>
</body>
</html>
```

## 总结

通过今天的任务，您应该已经掌握了：

1. **扩展插件机制**：理解了插件的集成方式和使用方法，能够使用文件上传等插件
2. **主题切换功能**：学会了实现界面主题切换功能，提升用户体验
3. **综合实践**：能够结合使用多种扩展功能创建丰富的用户界面

明天我们将进入最后一个阶段，开始开发综合管理系统，将前面学到的所有知识综合运用起来。