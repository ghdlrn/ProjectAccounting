# 베이스 이미지로 node:lts-alpine 사용
FROM node:lts-alpine
# 작업 디렉토리 설정
WORKDIR /app
# 의존성 설치를 위해 package.json과 package-lock.json 복사
COPY package.json ./
COPY package-lock.json ./
# 의존성 설치
RUN npm install
# 애플리케이션 소스 코드 복사
COPY . .
# 애플리케이션 빌드
RUN npm run build
# 포트 설정
EXPOSE 3000
# 환경 변수 설정 (필요 시)
ENV VITE_API_URL=http://spring:8080
# 애플리케이션 시작 명령어
CMD ["npm", "run", "preview"]
