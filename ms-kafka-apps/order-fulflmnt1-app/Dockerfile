FROM openjdk:11.0.2-jre-slim
COPY target/order-fulflmnt1-app-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m  -XX:TieredStopAtLevel=1 -noverify -jar order-fulflmnt1-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
