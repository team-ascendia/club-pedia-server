# 기본 이미지 설정
# 1️⃣ 기본 이미지 설정
FROM amazoncorretto:17 AS build

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ 프로젝트 소스 코드 복사
COPY . .

# 4️⃣ Gradle을 사용해 빌드
RUN ./gradlew clean build -x test

# 5️⃣ 런타임 이미지 설정
FROM amazoncorretto:17

# 작업 디렉토리 설정
# 6️⃣ 작업 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너로 복사
COPY build/libs/club-pedia-server.jar app.jar
# 7️⃣ 빌드된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 개방
# 8️⃣ 포트 개방
EXPOSE 8080

# 실행 명령
# 9️⃣ 실행 명령
CMD ["java", "-jar", "app.jar"]

