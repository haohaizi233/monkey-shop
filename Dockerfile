# 1. 基础镜像 (使用华为云加速源)
FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/openjdk:21-jdk-slim

# 2. 替换 Debian 12 (Bookworm) 默认的官方源为阿里云源 (解决 apt-get 连不上的问题)
RUN sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list.d/debian.sources 2>/dev/null || true && \
    sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list 2>/dev/null || true

# 3. 安装字体库 (验证码 Graphics2D 依赖)
RUN apt-get update && apt-get install -y \
    fontconfig \
    libfreetype6 \
    fonts-dejavu \
    && rm -rf /var/lib/apt/lists/*

# 4. 配置项目工作目录
WORKDIR /app

# 5. 拷贝 jar 包
COPY target/*.jar app.jar

# 6. 暴露端口并启动
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]