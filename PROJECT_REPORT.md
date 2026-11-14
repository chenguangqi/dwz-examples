# DWZ-Examples 项目全景介绍报告 (Project Overview Report)

## 1. 项目概述 (Project Overview)

`dwz-examples` 是一个围绕 **DWZ 富客户端框架 (jQuery RIA Framework)** 构建的系统化学习、实战演示与现代化全栈工程实践项目。

项目旨在通过“理论指南 + 阶段演练 + 综合系统 + 现代后端 + 容器化支撑”的完整链路，帮助开发者全面掌握 DWZ 框架的核心技术原理与开发模式。无论是用于维护和重构遗留的企业级 DWZ 系统，还是学习经典 RIA (Rich Internet Application) 架构的组件设计思想，本项目都提供了开箱即用的代码和文档参考。

### 1.1 核心价值与特色

- **系统化学习路径**：精心规划的 10 天渐进式学习计划（涵盖从框架初始化、布局导航、弹窗表单、数据展示到 Ajax 联动与主题扩展），每天均配有详细教程文档与可独立运行的示例。
- **真实完整业务实战**：内置完整的企业级“员工管理系统”（`employee_system`），涵盖登录鉴权、主框架工作台、员工管理、部门管理、岗位管理等核心业务功能。
- **现代全栈架构融合**：不仅包含前端 DWZ 原型，还提供了基于 **Spring Boot 2.7 + MyBatis-Plus + PostgreSQL** 的现代化微服务后端工程（`dwz-web`），展示传统 RIA 客户端与现代 RESTful/JSON API 接口的无缝对接。
- **开箱即用容器化**：提供基于 **Docker Compose** 的一键式数据库与管理界面环境（PostgreSQL 14 + pgAdmin 4），并配备自动化 SQL 初始化脚本。

---

## 2. 系统架构与模块构成 (System Architecture & Modules)

### 2.1 全局架构示意图

```
+-----------------------------------------------------------------------------------+
|                                  DWZ-Examples                                      |
+-----------------------------------------------------------------------------------+
|  [统一入口与导航]                                                                   |
|   index.html (API 概览与测试入口)                                                  |
+-----------------------------------------------------------------------------------+
|  [前端展示层 / 学习与实战]                                                        |
|   ├── dwz_jui-master/       : DWZ 框架官方源码、核心组件库、5套主题皮肤、插件扩展    |
|   ├── day1 ~ day7_examples/ : 1~7天阶段性功能演练 (navTab/dialog/form/table/ajax)   |
|   ├── day1 ~ day8_10_guide  : 1~10天渐进式指导文档 (Markdown)                       |
|   └── employee_system/      : 综合实战项目 (现代化登录界面 + 主工作台 + 业务 CRUD) |
+-----------------------------------------------------------------------------------+
|  [后端服务层] (dwz-web)                                                           |
|   ├── Controller 层 : RESTful API (/api/users, /test/db-connection 等)            |
|   ├── Service / Mapper : MyBatis-Plus 业务逻辑与数据访问                           |
|   └── 统一响应协议   : 遵循 DWZ 规范的 Ajax 报文结构 (statusCode, message, data)    |
+-----------------------------------------------------------------------------------+
|  [基础设施与数据层] (docker)                                                      |
|   ├── PostgreSQL 14 : 业务数据库 (dwz_db)                                         |
|   ├── pgAdmin 4     : Web 数据库管理平台 (端口 5050)                               |
|   └── init-scripts  : 容器初始化自动化执行 SQL (表结构、索引与触发器)             |
+-----------------------------------------------------------------------------------+
```

### 2.2 核心模块详细介绍

#### 模块一：DWZ 框架完整源码 (`dwz_jui-master`)
DWZ (jQuery RIA Framework) 是一套由国内团队开源的基于 jQuery 的 RIA Web 前端框架。其核心理念为：**以 HTML 扩展属性和 class 代替复杂的 JavaScript 编码**，开发人员编写标准 HTML 即可快速构建具备丰富交互的 Web 管理系统。
- **核心组件 (`js/`)**：`dwz.core.js` (核心引擎)、`dwz.ui.js` (UI 自动化绑定)、`dwz.navTab.js` (多标签工作区)、`dwz.dialog.js` (弹出层)、`dwz.ajax.js` (异步请求与回调管理)、`dwz.tree.js` (树控件)、`dwz.datepicker.js` (日期选择) 等。
- **模版片段 (`dwz.frag.xml`)**：存储框架弹窗、分页条、标签页等 UI 片段的 XML 模板，系统初始化时一次性加载。
- **内置主题 (`themes/`)**：提供 default (经典蓝)、green (清新绿)、purple (雅致紫)、silver (商务银)、azure (天蓝) 共 5 套皮肤。
- **集成插件**：ECharts 图表库、xheditor 在线 HTML 编辑器、uploadify 文件上传组件等。

#### 模块二：十天渐进式学习体系 (Day 1 - Day 10)
为降低初学者门槛，工程将 DWZ 的庞大功能体系拆解为四个进阶阶段：
1. **第一阶段：基础入门 (2天)**
   - **Day 1**：框架架构与初始化流程 (`DWZ.init`)，单页面引入核心依赖 (`day1_guide.md`, `day1_demo.html`)。
   - **Day 2**：导航与主页面布局，深入掌握 `navTab` 多标签切换、手风琴折叠菜单 `accordion` (`day2_guide.md`, `day2_examples/`)。
2. **第二阶段：UI 组件实战 (3天)**
   - **Day 3**：对话框与弹窗组件，包括模态与非模态 `dialog`、确认框 `alertMsg.confirm` (`day3_guide.md`, `day3_examples/`)。
   - **Day 4**：表单验证与高级组件，涵盖表单自动校验规则、日期时间组件、下拉组合框 `combox` (`day4_guide.md`, `day4_examples/`)。
   - **Day 5**：数据展示组件，包含多功能分页表格 `table`、自定义树形控件 `tree` (`day5_guide.md`, `day5_examples/`)。
3. **第三阶段：高级功能与交互 (2天)**
   - **Day 6**：Ajax 与前后端数据交互，包括异步操作 `ajaxTodo`、带参导出 `dwzExport`、查找带回 `lookup` 及自动补全 `suggest` (`day6_guide.md`, `day6_examples/`)。
   - **Day 7**：系统扩展与皮肤定制，多主题动态切换、文件上传与拖拽排序 (`day7_guide.md`, `day7_examples/`)。
4. **第四阶段：综合项目落地 (3天)**
   - **Day 8-10**：企业级综合管理系统全流程开发指南，从需求分析、数据库表设计、前后端交互设计到测试部署全过程 (`day8_10_guide.md`)。

#### 模块三：员工管理系统实战 (`employee_system`)
基于 DWZ 架构实现的企业级人事员工管理系统完整原型：
- **现代化登录界面 (`login.html`)**：全新响应式布局设计，采用渐变视觉质感与卡片化表单，支持客户端账号密码校验。
- **管理主控制台 (`main.html`)**：完整的 DWZ 后台骨架，支持顶部状态栏、主题即时切换、左侧业务菜单树、中央多标签工作台。
- **业务功能页面 (`pages/`)**：
  - 员工管理：`employee_list.html` (支持多条件组合检索、分页、批量操作)、`employee_form.html` (员工档案录入与修改)。
  - 部门管理：`department_list.html`、`department_form.html`。
  - 岗位管理：`position_list.html`、`position_form.html`。
- **Mock 数据驱动 (`data/`)**：内置 `users.json`, `employees.json`, `departments.json`, `positions.json`，无依赖即可完整演示数据加载与表单联动。

#### 模块四：Spring Boot 后端工程 (`dwz-web`)
现代化 Java 后端项目，用于配合前端 DWZ 示例实现真正的前后端联调：
- **核心依赖**：Spring Boot 2.7.0, MyBatis-Plus 3.5.2, PostgreSQL Driver, HikariCP 连接池。
- **规范适配**：在控制器层（`UserController.java`）中原生遵循 DWZ Ajax 交互响应格式：
  ```json
  {
    "statusCode": 200,
    "message": "操作成功",
    "data": [...],
    "pageNum": 1,
    "pageSize": 10,
    "total": 3
  }
  ```
- **连通性保障**：内置 `DatabaseTestController` 与静态测试页 (`database-test.html`)，可实时验证与 PostgreSQL 数据库的连通状态。

#### 模块五：Docker 容器化基础设施 (`docker`)
提供一键起停的生产级数据库容器化环境：
- `docker-compose.yml`：编排 PostgreSQL 14 与 pgAdmin 4 容器服务。
- `init-scripts/01-init-db.sql`：容器启动时自动创建 `users` 表、建立查询优化索引、配置自动更新 `updated_at` 触发器，并预埋初始演示数据。

---

## 3. 技术栈矩阵 (Technology Stack Matrix)

| 领域 | 技术 / 组件 | 版本 | 说明 |
| :--- | :--- | :--- | :--- |
| **前端核心** | jQuery | 1.x / 3.4.1 | 基础 JavaScript DOM 库 |
| **前端框架** | DWZ Rich Internet Application (JUI) | 1.5.x | 基于 jQuery 的 RIA 开源框架 |
| **前端扩展** | xheditor / ECharts / Uploadify | - | 富文本编辑器、图表可视化、文件上传 |
| **后端框架** | Spring Boot | 2.7.0 | Java 企业级后端脚手架 |
| **持久层** | MyBatis-Plus & Spring Data JPA | 3.5.2 | ORM 与数据库持久化 |
| **数据库连接池** | HikariCP | 4.0.3 | 高性能 JDBC 连接池 |
| **数据库** | PostgreSQL | 14 | 关系型数据库 |
| **数据库管理** | pgAdmin 4 | Latest | 基于 Web 的 PostgreSQL 可视化管理客户端 |
| **容器化编排** | Docker / Docker Compose | 3.8 Spec | 数据库与管理工具一键编排 |

---

## 4. DWZ 框架核心机制解析 (Core Mechanisms)

### 4.1 声明式 HTML 属性驱动机制 (Declarative HTML Binding)
DWZ 最显著的特征是极少需要手写 JavaScript 事件监听，通过标准 HTML 标签上的属性声明即可触发对应行为：
- `target="navTab"`: 在主工作区的多标签页中打开链接，配合 `rel="tabId"` 保证唯一打开。
- `target="dialog"`: 弹出模态或非模态浮层窗口，可配置 `mask="true"`、`width="800"`、`height="600"`。
- `target="ajaxTodo"`: 点击后弹出确认框并执行异步 HTTP 请求。
- `target="selectedTodo"`: 表格批量选中的行执行统一 Ajax 操作。

### 4.2 DWZ Ajax 统一响应契约
DWZ 前端定义了严格的服务器响应格式：
```json
{
  "statusCode": 200,           // 状态码: 200 (成功), 300 (失败), 301 (会话超时)
  "message": "操作成功",        // 提示信息 (系统会自动弹出提示条或 alertMsg)
  "navTabId": "userList",      // 操作成功后需要刷新的 navTab ID
  "rel": "",                   // 局部刷新的 DOM 容器 rel
  "callbackType": "closeCurrent", // 回调动作: closeCurrent (关闭当前窗口) 或 forward
  "forwardUrl": ""             // 重定向 URL
}
```

### 4.3 模版片段加载生命周期
1. 页面加载完成，调用 `DWZ.init(fragUrl, options)`。
2. 异步拉取 `dwz.frag.xml`，将常用 UI 模板缓存至内存对象 `DWZ.frag`。
3. 框架根据配置初始化环境 (`initEnv()`)，遍历 DOM 中带 DWZ 特征 class/attribute 的元素并自动绑定事件。

---

## 5. 项目目录结构总览 (Directory Tree)

```
dwz-examples/
├── README.md                      # 项目主说明文档与学习进度追踪
├── PROJECT_REPORT.md              # 本项目全景技术与架构介绍报告
├── index.html                     # 统一测试与后端 API 概览页面
│
├── dwz_jui-master/                # DWZ 官方完整源码包
│   ├── js/                        # DWZ 各组件与核心库源码
│   ├── themes/                    # 5套主题皮肤 (default/green/purple/silver/azure)
│   ├── doc/                       # 官方开发文档
│   ├── demo/                      # 官方组件演示示例
│   ├── dwz.frag.xml               # 页面片段模板定义
│   └── index.html                 # 官方示例主入口
│
├── day1_guide.md ~ day8_10_guide.md # 10天体系化教学与实战指南 (共8份指导文档)
├── day1_demo.html                 # 第1天: 基础初始化演示
├── day2_examples/                 # 第2天: navTab、菜单与面板布局示例
├── day3_examples/                 # 第3天: dialog 弹窗与 alert 消息示例
├── day4_examples/                 # 第4天: 表单输入、验证与高级组件示例
├── day5_examples/                 # 第5天: 表格分页与树形组件示例
├── day6_examples/                 # 第6天: Ajax 交互、lookup 数据联动示例
├── day7_examples/                 # 第7天: 主题切换、文件上传等扩展功能示例
│
├── employee_system/               # 综合实战项目: 员工管理系统
│   ├── index.html                 # 入口重定向
│   ├── login.html                 # 响应式登录界面
│   ├── main.html                  # 业务系统主工作台
│   ├── pages/                     # 员工/部门/岗位管理视图
│   ├── data/                      # 模拟 JSON 数据文件
│   └── js/app.js                  # 自定义业务辅助工具脚本
│
├── dwz-web/                       # Spring Boot 后端工程
│   ├── src/main/java/             # 控制器、服务层、MyBatis-Plus 映射器
│   ├── src/main/resources/        # application.properties 配置与静态资源
│   └── pom.xml                    # Maven 构建配置文件
│
└── docker/                        # 容器化基础设施
    ├── docker-compose.yml         # PostgreSQL 14 + pgAdmin 4 编排配置
    ├── init-scripts/              # 数据库初始化 SQL (建表、索引与测试数据)
    └── README.md                  # Docker 环境操作说明
```

---

## 6. 快速上手与运行指南 (Quick Start Guide)

### 6.1 查看前端示例与实战系统
无需编译，前端页面均支持静态运行：
1. **直接查看官方 Demo**：在浏览器中打开 `dwz_jui-master/index.html`。
2. **查看员工管理系统**：在浏览器中打开 `employee_system/login.html`。
3. **本地 HTTP 服务运行（推荐）**：
   使用 Python、Node.js 或任意静态服务器托管项目根目录，以避免部分浏览器对 `file://` 协议下 Ajax 请求本地 JSON/XML 的跨域限制：
   ```bash
   # 使用 Python 启动静态服务器
   python3 -m http.server 8000
   
   # 浏览器访问：
   # 员工管理系统: http://localhost:8000/employee_system/login.html
   # 学习示例索引: http://localhost:8000/day1_demo.html 等
   ```

### 6.2 启动数据库环境 (Docker Compose)
在项目根目录下进入 `docker` 目录：
```bash
cd docker
docker compose up -d
```
- PostgreSQL 数据库将监听宿主机端口 `5432`（数据库名：`dwz_db`，用户名：`dwz_user`，密码：`dwz_password`）。
- pgAdmin 管理界面将监听 `http://localhost:5050`（默认邮箱：`admin@example.com`，密码：`admin`）。

### 6.3 启动后端服务 (dwz-web)
确保数据库已启动后，在 `dwz-web` 目录下运行：
```bash
cd dwz-web
mvn spring-boot:run
```
- 服务端口：`8080`，上下文路径：`/dwz`。
- 测试 API：
  - 健康与连通性检查：`GET http://localhost:8080/dwz/test/db-connection`
  - 用户列表 API：`GET http://localhost:8080/dwz/api/users`
  - 接口概览入口：打开根目录 `index.html` 或访问 `http://localhost:8080/dwz/index.html`。

---

## 7. 总结与建议 (Summary & Recommendations)

`dwz-examples` 项目不仅是一份详尽的 DWZ 框架技术档案，更是传统轻量级富客户端架构的经典范例。

1. **对于维护遗留企业系统的开发者**：本项目是极佳的速查手册与参考架构，清晰展示了 DWZ 各核心组件的 HTML 属性配置、生命周期与前后端契约。
2. **对于现代全栈开发者**：通过对比 DWZ 的“声明式 HTML 增强”与现代 Vue/React 的“响应式数据驱动”，可以深入体会前端架构设计在不同时期的权衡取舍。
3. **扩展建议**：可进一步将 `employee_system` 中的纯前端 JSON Mock 切换为调用 `dwz-web` 的真实 REST API，实现真正端到端生产级的全栈闭环。
