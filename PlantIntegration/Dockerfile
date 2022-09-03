FROM openjdk:11 as builder
RUN mkdir -p /app/src
COPY . /app/src
WORKDIR /app/src
RUN ./mvnw clean package

FROM builder
COPY --from=builder /app/src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]