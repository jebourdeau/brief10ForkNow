FROM openjdk:17-slim AS builder

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew \
&& ./gradlew bootjar

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app

CMD ["java", ".jar", "/app/spring-security-*.jar"]