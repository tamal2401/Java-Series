## Server Ports identifier ##

> 1. Kafka_Order_Generation   : 8081
> 2. Kafka_Order_Inventory    : 8083
> 3. Kafka_Order_Confirmation : 8082



1. STREAM_ORDER_GENERATION_REQUESTED
2. STREAM_VALIDATE_AND_CHECK_INVENTORY
3. IF NO
    > a. STREAM_ITEM_UNAVAILABLE and notify user via mail or notification 
         AND STREAM_REFUND_INITIATED
4. IF YES
    > b. STREAM_ORDER_PLACED_SUCCESSFUL and notify user via mail or notification
