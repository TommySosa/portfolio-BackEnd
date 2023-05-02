FROM amazoncorretto:19-alpine-jdk
MAINTAINER TommySosa 
COPY  target/portfolio-0.0.1-SNAPSHOT.jar portfolio-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/portfolio-tomassosa.jar"]
