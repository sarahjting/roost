#https://medium.com/trantor-inc/developing-spring-boot-applications-in-docker-locally-4ec922f4cb45
FROM eclipse-temurin:19
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix imagemagick
ENV HOME=/app
RUN mkdir -p $HOME
WORKDIR $HOME