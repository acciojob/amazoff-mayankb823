package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    //1. Add an Order
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }


    public void addPartner(String partnerId ) {
        orderRepository.addPartner(partnerId);
    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }


    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }


    public int getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    //7. Get List of all orders assigned to given partnerId
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }


    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }


    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    //12
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }


    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
