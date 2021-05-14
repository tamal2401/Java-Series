Kafka commands:

> 1. zookeeper-server-start.bat ../../config/zookeeper.properties -> To start zookeper server
> 2. kafka-server-start.bat ../../config/server.properties -> to start kafka server
> 3. kafka-topics.bat --bootstrap-server localhost:9092 --create --topic t_hello --partitions 1 --replication-factor 1 -> To create new kafka topic with replication factor and artitions
> 4. kafka-topics.bat --bootstrap-server localhost:9092 --list -> To list all the created partitions
> 5. kafka-topics.bat --bootstrap-server localhost:9092 --describe -> To list all the details and configs about the topic
>6. kafka-topics.bat --bootstrap-server localhost:9092 --delete --topic <topic name> -> to delete a topic from kafka

Kafka topics: 


