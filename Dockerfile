# 베이스 이미지로 OpenJDK 사용
FROM openjdk:21-jdk-slim

# 메인테이너 정보
LABEL maintainer="ghdlrn@gmail.com"

# 작업 디렉토리 설정
WORKDIR /app

# 필수 패키지 설치
RUN apt-get update && apt-get install -y libsqlite3-dev dos2unix

# Gradle 빌드를 위해 Gradle 래퍼 스크립트 복사
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Gradle 빌드 파일 복사
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Convert line endings to Unix style
RUN dos2unix /app/gradlew

# Debug step: List the contents of the directory to ensure gradlew is copied
RUN ls -l /app

# Gradle 실행 권한 추가
RUN chmod +x /app/gradlew

# Debug step: Check if gradlew is executable
RUN ls -l /app/gradlew

# 의존성 캐싱을 위해 Gradle 의존성만 먼저 설치
RUN /app/gradlew build --no-daemon || return 0

# 애플리케이션 소스 코드 복사
COPY src /app/src

# Debug step: List the contents of the src directory to ensure it is copied correctly
RUN ls -l /app/src

# 애플리케이션 빌드
RUN /app/gradlew clean build -x test --no-daemon --rerun-tasks

# 포트 설정
EXPOSE 8080

# 환경 변수 설정
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/ACCOUNTING
ENV SPRING_DATASOURCE_USERNAME=LKM
ENV SPRING_DATASOURCE_PASSWORD=1234
ENV SPRING_JWT_SECRET=TIXETPdVIWbK3qiczAmeddDlZVrV235oZVCoPLfniYwdshjdlfhGDSLKn

# JAR 파일 실행
CMD ["sh", "-c", "java -jar build/libs/*SNAPSHOT.jar"]
