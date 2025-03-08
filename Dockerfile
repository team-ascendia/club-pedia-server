# 기본 이미지 설정
FROM amazoncorretto:17 AS build

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 소스 코드 복사
COPY . .

# Gradle을 사용해 빌드
RUN ./gradlew clean build -x test

# Gradle을 사용해 migrate 실행
RUN ./gradlew flywayMigrate

# 런타임 이미지 설정
FROM amazoncorretto:17

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 개방
EXPOSE 8080

# 실행 명령
CMD ["java", "-jar", "app.jar"]
