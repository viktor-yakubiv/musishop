FROM maven:3.5-alpine as build
ARG LOCAL_ENV=false
COPY . /app/
RUN mkdir -p /app/target/
RUN touch /app/target/app.war
WORKDIR /app
# Run the following only if we are not in Dev Environment:
RUN test $LOCAL_ENV = "false" && mvn clean install

FROM tomcat:9-alpine
COPY --from=build /app/target/app.war /usr/local/tomcat/webapps