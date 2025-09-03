FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package || true
EXPOSE 8083
CMD ["java","-jar","target/inventory-service-0.1.0.jar"]
