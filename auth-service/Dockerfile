FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package || true
EXPOSE 8081
CMD ["java","-jar","target/auth-service-0.1.0.jar"]
