FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package || true
EXPOSE 8082
CMD ["java","-jar","target/catalog-service-0.1.0.jar"]
