FROM openjdk:11-jdk-slim
RUN mkdir /opt/e-learning
COPY ./e-learning-web/target/e-learning-web.jar /opt/e-learning/
WORKDIR /opt/e-learning
EXPOSE 8080
CMD java -jar e-learning-web.jar
