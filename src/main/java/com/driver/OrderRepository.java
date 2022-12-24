package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private HashMap<String,Order> orderMap;
    private HashMap<String,DeliveryPartner> deliveryPartnerMap;
    private HashMap<String,List<String>> deliveryPatnerorderMapping;

    public OrderRepository() {
        this.orderMap = new HashMap<String, Order>();
        this.deliveryPartnerMap =new HashMap<String, DeliveryPartner>();
        this.deliveryPatnerorderMapping =new HashMap<String, List<String>>();
    }
    public void saveOrder(Order order){
        orderMap.put(order.getId(), order);
    }
    public void savedeliveryPartner(DeliveryPartner deliveryPartner){
        deliveryPartnerMap.put(deliveryPartner.getId(),deliveryPartner);
    }
    public void savePartnerOrderPair(String order,String deliveryPartner)
   {
        if(orderMap.containsKey(order) && deliveryPartnerMap.containsKey(deliveryPartner)){
            orderMap.put(order, orderMap.get(order));
            deliveryPartnerMap.put(deliveryPartner, deliveryPartnerMap.get(deliveryPartner));
            List<String> currentOrders = new ArrayList<String>();
            if(deliveryPatnerorderMapping.containsKey(deliveryPartner)) currentOrders = deliveryPatnerorderMapping.get(deliveryPartner);
           currentOrders.add(order);
            deliveryPatnerorderMapping.put(deliveryPartner, currentOrders);
        }
       //deliveryPatnerorderMapping.put(order,deliveryPartner);
    }

    public Order findorder(String orderid){
        return orderMap.get(orderid);
    }

    public DeliveryPartner findPartner(String deliveryPartnerid){
        return deliveryPartnerMap.get(deliveryPartnerid);
    }

    public int countOrderFromPartner(String deliveryPartnerid){
        List<String> orderList = new ArrayList<String>();
        if(deliveryPatnerorderMapping.containsKey(deliveryPartnerid)) orderList = deliveryPatnerorderMapping.get(deliveryPartnerid);
        return orderList.size();
    }

    public List<String> OrdersofPartner(String deliveryPartnerid){
        List<String> orderList = new ArrayList<String>();
        if(deliveryPatnerorderMapping.containsKey(deliveryPartnerid)) orderList = deliveryPatnerorderMapping.get(deliveryPartnerid);
        return orderList;
    }

    public List<String> findAllOrders(){
        return new ArrayList<>(orderMap.keySet());
    }

    public void deletePartner(String deliveryPartnerid){
        List<String> orders = new ArrayList<String>();
        if(deliveryPatnerorderMapping.containsKey(deliveryPartnerid)){
            orders = deliveryPatnerorderMapping.get(deliveryPartnerid);
            for(String movie: orders){
                if(orderMap.containsKey(movie)){
                    orderMap.remove(movie);
                }
            }

            deliveryPatnerorderMapping.remove(deliveryPartnerid);
        }

        if(deliveryPartnerMap.containsKey(deliveryPartnerid)){
            deliveryPartnerMap.remove(deliveryPartnerid);
        }
    }

    public void deleteorder(String id) {
        List<String> orders = new ArrayList<String>();
        if(deliveryPatnerorderMapping.containsKey(id)){
            orders = deliveryPatnerorderMapping.get(id);
            for(String movie: orders){
                if(deliveryPartnerMap.containsKey(id)){
                    deliveryPartnerMap.remove(id);
                }
            }
            deliveryPatnerorderMapping.remove((id));
        }


        if(orderMap.containsKey(id))
            orderMap.remove(id);
    }
    public int unassignedorder(){
        int count=0;
        for(String id:deliveryPartnerMap.keySet()){
            if(!deliveryPatnerorderMapping.containsKey(id)) count++;
        }
        return count;

    }
    public int orderleft(String Time,String Partnerid){
        int count=0;
        int time=convert(Time);
       for(int i=0;i<deliveryPatnerorderMapping.get(Partnerid).size();i++){
           String temp=deliveryPatnerorderMapping.get(Partnerid).get(i);
           int timetaken=orderMap.get(temp).getDeliveryTime();
           if(time<timetaken) count++;
       }
       return count;
    }
    public String totaltimebypartner(String Partnerid){
        int count=0;
        for(int i=0;i<deliveryPatnerorderMapping.get(Partnerid).size();i++){
            String temp=deliveryPatnerorderMapping.get(Partnerid).get(i);
            int timetaken=orderMap.get(temp).getDeliveryTime();
            count=count+timetaken;
        }
        return timeinhours(count);
    }
    public String timeinhours(int count){
        String t="";
        t="count/60"+":"+"count%60";
        return t;
    }
    public int convert(String time){
        int a=Integer.valueOf(time.substring(0,2))*60;
        int b=Integer.valueOf(time.substring(3,5));

        return a+b;
    }
public void time(){
        System.out.print();
}

}
