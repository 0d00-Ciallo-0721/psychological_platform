@echo off
title 心理健康平台一键启动脚本
CHCP 65001
cls

echo ======================================================
echo           心理健康平台 (Spring Boot) 启动中
echo ======================================================

:: 1. 检查本地环境
echo [1/3] 正在检查数据库连接环境...
echo 请确保 MySQL (端口3306) 和 Redis 已在后台启动。
echo.

:: 2. 编译并启动后端
echo [2/3] 正在编译并启动后端服务 (使用 mvnw)...
echo 首次运行可能需要下载依赖，请耐心等待...
echo.

:: 使用 start 命令新开一个窗口运行后端，防止阻塞当前脚本查看文档
start "后端服务-运行中" cmd /k "mvnw.cmd clean spring-boot:run"

:: 3. 等待并打开接口文档
echo [3/3] 正在等待服务初始化...
echo 脚本将在 15 秒后尝试自动打开 Swagger 接口文档。
echo 如果启动较慢，请稍后在浏览器手动刷新。
echo.

:: 等待 15 秒（Spring Boot 启动预估时间）
timeout /t 15 /nobreak > nul

echo 正在打开接口文档: http://localhost:8080/swagger-ui/index.html
start http://localhost:8080/swagger-ui/index.html

echo ======================================================
echo 后端服务已在独立窗口运行。
echo 如需停止服务，请直接关闭那个名为 [后端服务-运行中] 的窗口。
echo ======================================================
pause