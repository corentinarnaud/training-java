FROM jenkins:latest
MAINTAINER corentin

RUN install-plugins.sh git-client git blueocean scm-api greenballs

USER root
RUN mkdir /var/log/jenkins
RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN mkdir /cdb
RUN chown -R  jenkins:jenkins /cdb
RUN apt-get update \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN groupadd -g 994 docker && usermod -a -G docker jenkins
## CHANGER LE GROUP ID '994' avec le groupe ID de docker `getent group`

USER jenkins

