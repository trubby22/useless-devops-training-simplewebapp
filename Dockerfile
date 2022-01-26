FROM ubuntu
# Install Java dependencies
RUN apt-get update && apt-get install -y bash maven pandoc openjdk-11-jre openjdk-11-jdk
RUN apt-get install -y ruby
# Install TeX dependencies
RUN apt-get install -y texlive-latex-base texlive-fonts-recommended texlive-fonts-extra texlive texlive-latex-extra
WORKDIR /simplewebapp
COPY . .
RUN mvn compile
RUN mvn package
CMD ["sh", "target/bin/simplewebapp"]
