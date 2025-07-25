FROM openjdk:21-jdk-slim
WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/finance-product-0.0.1-SNAPSHOT.jar"]
