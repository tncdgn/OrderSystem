# OrderSystem

This is the simple rest service to order with written spring boot.

# Description
When application starts, all necessary data will be ready to use.

For database, h2 is used. To see all tables and datas http://localhost:8087/h2-console

To see swagger screen to make rest request http://localhost:8087/swagger-ui.html

# Docker
To dockerize the application please follow

 * mvn clean install
  
 * docker build -f Dockerfile -t order-system .  (to Create docker image)
  
  This application is configurable for diffrent enviroment.There are 2 diffrent file for prod and dev.
  
  You can choose whatever enviroment you want and set variables for proper enviroment
  
  To run on docker and start the application 
  
 * docker-compose  -f docker-compose.dev.yml up
  
  or
  
  * docker-compose  -f docker-compose.prod.yml up

If you want, you can add file for diffrent enviroment whatever you want

To create order, post to this url **/order/create** 


