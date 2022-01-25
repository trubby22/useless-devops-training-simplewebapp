FROM ubuntu
RUN apt-get update && apt-get install -y bash maven pandoc openjdk-11-jre openjdk-11-jdk
RUN apt-get install -y texlive-base
RUN apt-get install -y texlive
RUN apt-get install -y texlive-latex-extra
WORKDIR /simplewebapp
COPY . .
RUN mvn compile
RUN mvn package
CMD ["sh", "target/bin/simplewebapp"]