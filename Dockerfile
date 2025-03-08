# 1️⃣ Gradle이 포함된 JDK 17 이미지 사용하여 빌드
FROM gradle:8.10-jdk17 AS build

WORKDIR /app
COPY . .

# 2️⃣ Gradle을 사용해 빌드 (Gradle 캐싱을 위해 --no-daemon 옵션 추가)
RUN gradle build -x test --no-daemon

# 3️⃣ 런타임 환경 설정 (Amazon Corretto)
FROM amazoncorretto:17

WORKDIR /app

# 4️⃣ 빌드된 JAR 파일 복사
COPY --from=build /app/. .

# 7️⃣ 포트 개방
EXPOSE 80

# 8️⃣ 실행 명령 (Flyway 마이그레이션 후 애플리케이션 실행)
CMD ["/bin/sh", "-c", "/app/gradlew flywayMigrate && java -jar /app/build/libs/*.jar"]
