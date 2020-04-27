FROM openjdk:11.0.2-jre-slim
COPY target/order-mgmt-app-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m  -XX:TieredStopAtLevel=1 -noverify -jar order-mgmt-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
