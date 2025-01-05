#Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

#set current working directory inside the image
WORKDIR /app

#copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

#copy pom.xml file
COPY pom.xml .

#Build all dependencies in preparation to go offline.
#This is a separate step so the dependencies will be cached unless the pom.xml file has changed.
RUN ./mvnw dependency:go-offline

#copy project source
COPY src src

#package the application
RUN ./mvnw package 
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

##stage 2: A minimal docker image with command to run the app
FROM eclipse-temurin:21-jre

ARG DEPENDENCY=/app/target/dependency

#copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.calculate.coins.CoinApplication"]