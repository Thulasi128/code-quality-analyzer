# 1️⃣ Use official Java runtime
FROM eclipse-temurin:17-jdk

# 2️⃣ Create app directory
WORKDIR /app

# 3️⃣ Copy Maven wrapper and source code
COPY . .

# 4️⃣ Build the JAR
RUN ./mvnw clean package -DskipTests

# 5️⃣ Run the application on Render port
EXPOSE 8080
CMD ["java", "-jar", "target/intelligent-code-quality-analyzer-0.0.1-SNAPSHOT.jar"]
