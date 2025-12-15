# Docker环境说明

本目录包含用于启动DWZ项目所需PostgreSQL数据库的Docker Compose配置。

## 目录结构

```
docker/
├── docker-compose.yml      # Docker Compose配置文件
├── init-scripts/           # 数据库初始化脚本
│   └── 01-init-db.sql      # 数据库初始化SQL脚本
├── volumes/                # 数据持久化目录
│   ├── postgres/           # PostgreSQL数据存储目录
│   └── pgadmin/            # pgAdmin数据存储目录
└── README.md               # 本说明文件
```

## 服务组成

1. **PostgreSQL数据库** (`postgres`)
   - 版本: 14
   - 端口映射: 5432:5432
   - 数据库名: dwz_db
   - 用户名: dwz_user
   - 密码: dwz_password

2. **pgAdmin管理界面** (`pgadmin`)
   - 端口映射: 5050:80
   - 默认登录邮箱: admin@example.com
   - 默认密码: admin

## 使用方法

### 启动服务

```bash
# 在docker目录下执行
docker-compose up -d
```

### 停止服务

```bash
docker-compose down
```

### 查看服务状态

```bash
docker-compose ps
```

## 连接信息

### 数据库连接

- Host: localhost
- Port: 5432
- Database: dwz_db
- Username: dwz_user
- Password: dwz_password

### pgAdmin访问

- URL: http://localhost:5050
- Email: admin@example.com
- Password: admin

在pgAdmin中配置数据库连接时，服务器地址应填写`postgres`（Docker容器名称）而不是localhost。

## 初始化数据

数据库启动时会自动执行`init-scripts`目录下的SQL脚本，创建表结构和初始数据。

## 数据持久化

数据库数据存储在本地`volumes`目录中，即使容器被删除，数据也不会丢失。这些目录位于：
- PostgreSQL数据: `./volumes/postgres`
- pgAdmin数据: `./volumes/pgadmin`