FROM openjdk:11-jdk-slim
RUN mkdir /opt/elearning 
COPY ./elearning-web/target/elearning-web.jar /opt/elearning/
WORKDIR /opt/elearning
EXPOSE 8080
CMD java -jar elearning-web.jar
