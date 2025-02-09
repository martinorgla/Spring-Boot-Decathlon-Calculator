FROM node:14 AS frontend-build
WORKDIR /app
COPY src/main/webapp /app
RUN npm install && npm run build

FROM gradle:6.9.4-jdk11 AS backend-build

WORKDIR /app
COPY --from=frontend-build /app/dist src/main/webapp/dist
COPY . /app
RUN gradle build --no-daemon


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=backend-build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
