FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package || true
CMD ["java","-jar","target/worker-0.1.0.jar"]
