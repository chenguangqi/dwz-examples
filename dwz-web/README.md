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
│   │   │       ├── config/
│   │   │       │   └── WebConfig.java
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
- `GET /dwz/api/users` - 获取用户列表
- `GET /dwz/api/users/{id}` - 获取特定用户
- `POST /dwz/api/users` - 创建新用户
- `PUT /dwz/api/users/{id}` - 更新用户
- `DELETE /dwz/api/users/{id}` - 删除用户

## 集成 DWZ-JUI

将 DWZ-JUI 前端文件放置在 `src/main/resources/static/` 目录下，它们将可以通过 HTTP 访问。

例如，如果你有一个 `demo_page1.html` 文件，将其放在 `static` 目录下后，就可以通过 `http://localhost:8080/dwz/demo_page1.html` 访问它。