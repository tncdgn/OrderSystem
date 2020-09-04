FROM openjdk:8
ADD target/order-system.jar order-system.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","order-system.jar"]