# DWZ-JUI Spring Boot 后端项目

这是一个为 DWZ-JUI 框架练习而创建的简单 Spring Boot 后端项目。

## 项目结构

```
dwz-web/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/dwz/
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java
│   │   │       │   └── UserController.java
│   │   │       ├── entity/
│   │   │       │   └── User.java
│   │   │       ├── mapper/
│   │   │       │   └── UserMapper.java
│   │   │       ├── service/
│   │   │       │   └── UserService.java
│   │   │       ├── config/
│   │   │       │   ├── WebConfig.java
│   │   │       │   └── MybatisPlusConfig.java
│   │   │       └── DwzWebApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html
│   └── test/
│       └── java/
│           └── com/example/dwz/
├── pom.xml
└── README.md
```

## 启动项目

### 1. 使用Docker启动数据库（推荐）

项目根目录的`docker`目录中包含了PostgreSQL数据库的Docker Compose配置：

```bash
cd ../docker
docker-compose up -d
```

这将启动PostgreSQL数据库和pgAdmin管理界面。

### 2. 数据库准备（手动安装方式）

如果不想使用Docker，也可以手动安装PostgreSQL数据库，并执行以下操作：

```sql
CREATE DATABASE dwz_db;
CREATE USER dwz_user WITH PASSWORD 'dwz_password';
GRANT ALL PRIVILEGES ON DATABASE dwz_db TO dwz_user;
```

### 3. 修改数据库配置

在 `src/main/resources/application.properties` 中修改数据库连接参数以匹配你的环境：

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/dwz_db
spring.datasource.username=dwz_user
spring.datasource.password=dwz_password
```

如果使用Docker环境，默认配置应该可以直接工作。

### 4. 启动应用

```bash
cd dwz-web
mvn spring-boot:run
```

或者打包运行：

```bash
mvn clean package
java -jar target/dwz-web-1.0.0.jar
```

## API 接口

默认情况下，API 通过 `/dwz` 上下文路径提供服务：

- `GET /dwz/api/hello` - 简单测试接口
- `GET /dwz/api/users` - 获取用户列表（支持分页）
- `GET /dwz/api/users/{id}` - 获取特定用户
- `POST /dwz/api/users` - 创建新用户
- `PUT /dwz/api/users/{id}` - 更新用户
- `DELETE /dwz/api/users/{id}` - 删除用户

## 技术栈

- Spring Boot 2.7.0
- MyBatis-Plus 3.5.2
- PostgreSQL 数据库
- HikariCP 连接池

## 集成 DWZ-JUI

将 DWZ-JUI 前端文件放置在 `src/main/resources/static/` 目录下，这样就可以通过 HTTP 访问它们了。

例如，如果你有一个 `demo_page1.html` 文件，将其放在 `static` 目录下后，就可以通过 `http://localhost:8080/dwz/demo_page1.html` 访问它。