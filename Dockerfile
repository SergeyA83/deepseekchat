FROM alpine/java:21-jdk AS builder
WORKDIR /project
COPY build/libs/deepSeekChat.jar deepSeekChat.jar
RUN java -Djarmode=tools -jar deepSeekChat.jar extract --layers --launcher
EXPOSE 8080

FROM alpine/java:21-jdk
WORKDIR /project
COPY --from=builder project/deepSeekChat/dependencies/ ./
COPY --from=builder project/deepSeekChat/snapshot-dependencies/ ./
COPY --from=builder project/deepSeekChat/spring-boot-loader/ ./
COPY --from=builder project/deepSeekChat/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]