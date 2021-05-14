package com.course.kafka.command.action;

import com.course.kafka.api.request.OrderItemrequest;
import com.course.kafka.api.request.OrderRequest;
import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.entity.Order;
import com.course.kafka.entity.OrderItem;
import com.course.kafka.producer.OrderProducer;
import com.course.kafka.repository.OrderItemRepository;
import com.course.kafka.repository.OrderRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class OrderAction {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderProducer producer;

    public Order convertToOrder(OrderRequest request) {
        Order newOrder = new Order();
        newOrder.setCreditCardNumber(request.getCreditCardNumber());
        newOrder.setOrderLocation(request.getLocation());
        newOrder.setOrderDateTime(LocalDateTime.now());
        newOrder.setOrderNumber(RandomStringUtils.random(8).toUpperCase());

        List<OrderItem> items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());

        items.forEach(each -> each.setOrder(newOrder));

        newOrder.setItems(items);

        return newOrder;
    }

    private OrderItem convertToOrderItem(OrderItemrequest orderItemrequest) {
        OrderItem item = new OrderItem();
        item.setItemName(orderItemrequest.getItemName());
        item.setQuantity(orderItemrequest.getQuantity());
        item.setPrice(orderItemrequest.getPrice());
        return item;
    }

    public void saveToDb(Order order) {
        orderRepository.save(order);
    }

    public void publishToKafka(OrderItem orderItem) {
        OrderMessage msg = new OrderMessage();
        msg.setOrderLocation(orderItem.getOrder().getOrderLocation());
        msg.setOrderNumber(orderItem.getOrder().getOrderNumber());
        msg.setOrderDateTime(orderItem.getOrder().getOrderDateTime());
        msg.setPrice(orderItem.getPrice());
        msg.setCreditCardNumber(orderItem.getOrder().getCreditCardNumber());
        msg.setQuantity(orderItem.getQuantity());
        msg.setItemName(orderItem.getItemName());
        producer.send(msg);
    }
}
