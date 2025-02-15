# 기본 이미지 설정
FROM amazoncorretto:17

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너로 복사
COPY build/libs/club-pedia-server.jar app.jar

# 포트 개방
EXPOSE 8080

# 실행 명령
CMD ["java", "-jar", "app.jar"]
