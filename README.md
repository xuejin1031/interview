# 簡易社群媒體平台

## 系統介紹

這是一個簡易的社群媒體平台，允許使用者註冊、登入、發文和留言。系統採用三層式架構：前端 Web Server、後端 Application Server 和關聯式資料庫。

## 主要功能

- **註冊功能**：使用者可以透過手機號碼進行註冊。
- **登入驗證功能**：系統實作身份驗證，確保只有登入的使用者可以發文或留言。
- **發文功能**：
  - 新增發文
  - 列出所有發文
  - 編輯或刪除發文
- **留言功能**：使用者可以針對發文新增留言。

## 技術架構

- **前端**：Vue.js
- **後端**：Spring Boot，使用 RESTful API
- **資料庫**：MySQL，使用 Stored Procedure 存取資料
- **建構工具**：Maven (後端)
- **安全性**：防止 SQL Injection 和 XSS 攻擊
- **交易管理**：實作 Transaction 以避免資料錯亂

## 專案結構

```
interview/
├── DB/                    # 資料庫腳本
│   ├── ddl.sql           # 資料庫結構定義
│   ├── dml.sql           # 資料插入腳本
│   └── stored_procedures.sql  # 預存程序
├── backend/              # Spring Boot 後端
│   ├── src/
│   ├── pom.xml
│   └── ...
└── frontend/             # Vue.js 前端
    ├── src/
    ├── package.json
    └── ...
```

## 執行步驟

### 1. 環境準備

確保系統已安裝以下軟體：
- Java 17 或以上
- Node.js 和 npm
- MySQL 8.0 或以上
- Maven (通常隨 Spring Boot 一起安裝)

### 2. 設定資料庫

1. 啟動 MySQL 服務：
   ```bash
   sudo service mysql start
   ```

2. 建立資料庫並執行腳本：
   ```bash
   mysql -u root -p < DB/ddl.sql
   mysql -u root -p < DB/stored_procedures.sql
   mysql -u root -p < DB/dml.sql
   ```

   或者手動執行：
   ```sql
   CREATE DATABASE social_media;
   USE social_media;
   -- 然後執行 ddl.sql、stored_procedures.sql 和 dml.sql 的內容
   ```

3. 更新後端配置：
   編輯 `backend/src/main/resources/application.properties`，確保資料庫連線設定正確：
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/social_media
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. 啟動後端

1. 進入後端目錄：
   ```bash
   cd backend
   ```

2. 使用 Maven 編譯和啟動應用：
   ```bash
   mvn spring-boot:run
   ```

   後端將在 http://localhost:8080 啟動。

### 4. 啟動前端

1. 進入前端目錄：
   ```bash
   cd frontend
   ```

2. 安裝依賴：
   ```bash
   npm install
   ```

3. 啟動開發伺服器：
   ```bash
   npm run serve
   ```

   前端將在 http://localhost:8081 啟動。

### 5. 存取應用

開啟瀏覽器，前往 http://localhost:8081 開始使用社群媒體平台。
