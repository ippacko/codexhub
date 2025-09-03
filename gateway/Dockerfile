FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package || true
EXPOSE 8080
CMD ["java","-jar","target/gateway-0.1.0.jar"]
