# DWZ框架学习计划

`dwz_jui-master`目录是DWZ开源项目的完整源码，用于学习和参考。

## 学习目标

通过系统性学习和实践，全面掌握DWZ框架的各项功能，最终能够独立使用该框架开发复杂的Web应用程序。

## 项目结构说明

```shell
dwz-examples/
├── dwz_jui-master/           # DWZ框架完整源码
│   ├── bin/                  # 编译和部署脚本（压缩版JS/CSS）
│   ├── chart/                # 图表插件（ECharts）
│   ├── demo/                 # 功能演示示例
│   ├── doc/                  # 文档资料
│   ├── js/                   # JavaScript组件源码（核心）
│   │   ├── dwz.*.js          # DWZ框架各组件源码
│   │   └── jquery.*.js       # jQuery及相关插件
│   ├── themes/               # 主题样式文件
│   │   ├── default/          # 默认蓝色主题
│   │   ├── green/            # 绿色主题
│   │   ├── purple/           # 紫色主题
│   │   ├── silver/           # 银色主题
│   │   ├── azure/            # 天蓝色主题
│   │   └── css/             # 核心样式文件
│   ├── uploadify/            # jQuery文件上传插件
│   ├── xheditor/             # 在线HTML编辑器
│   ├── index.html            # 主入口文件（功能演示）
│   ├── dwz.frag.xml          # 页面片段配置文件
│   └── ...                  # 其他示例页面文件
├── day1_guide.md             # 第一天学习指南
└── README.md                 # 本文件
```

## 学习计划与进度跟踪

### 第一阶段：基础入门 (2天)

- [x] **第1天：了解DWZ框架基础架构**
  - [x] 理解DWZ框架概述和整体结构 ([指南](day1_guide.md))
  - [x] 学习DWZ的初始化过程 ([指南](day1_guide.md))
  - [x] 掌握核心组件和配置文件 ([指南](day1_guide.md))
  - [x] 实践：创建简单页面集成基础功能 ([示例](day1_demo.html))

- [ ] **第2天：导航与布局组件**
  - [ ] 学习navTab组件工作原理
  - [ ] 掌握菜单栏和导航栏使用方法
  - [ ] 学习面板组件(accordion等)
  - [ ] 实践：构建完整导航结构页面

### 第二阶段：UI组件学习 (3天)

- [ ] **第3天：对话框与弹窗组件**
  - [ ] 学习dialog组件使用方法
  - [ ] 掌握alert组件和提醒消息
  - [ ] 实践：创建多种弹窗类型页面

- [ ] **第4天：表单相关组件**
  - [ ] 学习表单验证机制
  - [ ] 掌握datepicker、combox等表单组件
  - [ ] 实践：创建完整表单页面

- [ ] **第5天：数据展示组件**
  - [ ] 学习表格和分页组件
  - [ ] 掌握树形组件(tree)
  - [ ] 实践：创建带分页的数据列表和树形菜单

### 第三阶段：高级功能 (2天)

- [ ] **第6天：Ajax与数据交互**
  - [ ] 学习Ajax集成和异步请求处理
  - [ ] 掌握数据联动功能(lookup, suggest)
  - [ ] 实践：实现异步数据交互功能

- [ ] **第7天：扩展功能与主题**
  - [ ] 学习扩展插件机制
  - [ ] 掌握主题切换功能
  - [ ] 实践：实现文件上传和拖拽排序功能

### 第四阶段：综合应用示例 (3天)

- [ ] **第8-10天：开发综合管理系统**
  - [ ] 需求分析和设计
  - [ ] 功能开发和实现
  - [ ] 测试和完善
  - [ ] 最终交付：完整可运行的员工管理系统

## 学习资源

- DWZ官方文档和示例代码 (`dwz_jui-master`目录)
- 源码文件：`dwz_jui-master/js/`目录下的各个组件实现
- 入口文件：`dwz_jui-master/index.html`中的示例用法
- 第一天学习指南：[day1_guide.md](file:///opt/workspace/dwz-examples/day1_guide.md)
- 第一天实践示例：[day1_demo.html](file:///opt/workspace/dwz-examples/day1_demo.html)
