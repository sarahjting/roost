#https://medium.com/trantor-inc/developing-spring-boot-applications-in-docker-locally-4ec922f4cb45
FROM eclipse-temurin:19
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME