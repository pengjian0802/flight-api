# 使用官方 OpenJDK 21 基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 Maven 构建的 JAR 文件到容器中
COPY target/*.jar app.jar

# 暴露 Spring Boot 应用的默认端口
EXPOSE 8080

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]