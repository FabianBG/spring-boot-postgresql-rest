
# SOURCE BUILDER

FROM gradle:6.4.1-jdk11 AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon 


# IMAGE BUILDER

FROM adoptopenjdk/openjdk11:jre-11.0.7_10-alpine

RUN addgroup -S app && adduser -S app -G app

USER app:app

COPY --chown=app:app --from=builder /home/gradle/src/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
