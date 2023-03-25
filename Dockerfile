FROM amazoncorretto:19-alpine-jdk
MAINTAINER TommySosa 
EXPOSE 80
EXPOSE 8080
EXPOSE 3306
COPY  target/portfolio-0.0.1-SNAPSHOT.jar portfolio-tomassosa.jar
ENTRYPOINT ["java", "-jar", "/portfolio-tomassosa.jar"]
