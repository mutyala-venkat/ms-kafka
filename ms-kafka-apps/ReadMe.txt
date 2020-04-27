AOT code assignment  .

The project creates Docker containers.

It uses three microservices:

Order to create orders. This services sends messages to Kafka. It uses the KafkaTemplate.
Fulfilment app receives  Book orders and extract the information needed to ship the items.
Fulfilment1 app receives  Computer orders and extract the information needed to ship the items, too.
 It extracts all information to send out an invoice. 
 It uses @KafkaListener just like Shipment.
This is done using a topic order&orderStatus. It has 1 partition each. Fulfilment and Fulfilment1 each have a separate consumer group.
 So multiple instances of Fulfilment and Fulfilment1 can be run. 

Technologies
Java
Spring Boot
Spring Kafka
Apache httpd
Kafka

Docker Compose to link the containers.


Once you create an order in the order application, after a while the order information can be seen in orderStatus topic.

http://localhost:8080/order/BookOrder --For Book orders to create
http://localhost:8080/order/ComputerOrder --For computer orders to create

The microservices are:

order-mgmt-app to create the orders
order-fulflmnt-app for the Book orders fulfilment
order-fulflmnt1-app for the  Computer orders fulfilment
