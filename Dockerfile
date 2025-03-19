FROM alpine/java:21-jdk AS builder
WORKDIR /project
COPY build/libs/springBootAiChat.jar springBootAiChat.jar
RUN java -Djarmode=tools -jar springBootAiChat.jar extract --layers --launcher
EXPOSE 8080

FROM alpine/java:21-jdk
WORKDIR /project
COPY --from=builder project/springBootAiChat/dependencies/ ./
COPY --from=builder project/springBootAiChat/snapshot-dependencies/ ./
COPY --from=builder project/springBootAiChat/spring-boot-loader/ ./
COPY --from=builder project/springBootAiChat/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]