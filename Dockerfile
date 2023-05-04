FROM amazoncorretto:19

EXPOSE 8080

RUN mkdir /app

COPY build/libs/*.jar worker.jar

ENTRYPOINT ["java","-jar","worker.jar"]