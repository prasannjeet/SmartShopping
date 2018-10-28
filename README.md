# SmartShopping/Gateway
API gateway between client and server side of the SmartShopping App.

1.Clone the repository

2.Open terminal and navigate to SmartShopping/gateway-service

3. input command mvn compile, mvn package and mvn install

4 then input java -jar target/gs-gateway-0.1.0.jar to start gateway.

4.The gateway will be running on localhost:8080. Currently the only API is /get and it is route to http://httpbin.org:80. (carts still in test)
