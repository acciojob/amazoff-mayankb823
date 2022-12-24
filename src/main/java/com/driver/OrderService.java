package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
   OrderRepository orderRepository;

    public void addOrder(Order order){
       orderRepository.saveOrder(order);
    }

    public void addPartner(DeliveryPartner deliveryPartner){
        orderRepository.savedeliveryPartner(deliveryPartner);
    }

    public void addOrderPartnerPair(String order, String Partner){
        orderRepository.savePartnerOrderPair(order,Partner);
    }

    public Order getOrderById(String orderId){
        return orderRepository.findorder(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerid){
        return orderRepository.findPartner(partnerid);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return orderRepository.countOrderFromPartner(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.OrdersofPartner(partnerId);
    }

    public List<String> getAllOrders(){
        return orderRepository.findAllOrders();
    }

    public int getCountofUnassignedOrders(){
        return orderRepository.unassignedorder();
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String PartnerId){
      return  orderRepository.orderleft(time,PartnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String PartnerId){
        return orderRepository.totaltimebypartner(PartnerId);
    }

    public void deleteOrderById(String orderid){
        orderRepository.deleteorder(orderid);
    }

    public void deletePartnerById(String Partnerid){
        orderRepository.deletePartner(Partnerid);
    }
}
