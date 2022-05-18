FROM eclipse-temurin:17-jre-focal
EXPOSE 8080

ADD build/libs/shorten-url-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-XX:MaxRAM=256M", "-Xmx128M", "-Xss256k", "-Xms128M", "-jar","app.jar"]