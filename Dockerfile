FROM amazoncorretto:19-alpine-jdk
MAINTAINER TommySosa 
EXPOSE 8080
COPY  target/portfolio-0.0.1-SNAPSHOT.jar portfolio-tomassosa.jar
ENTRYPOINT ["java", "-jar", "/portfolio-tomassosa.jar"]
