package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

    private Map<String,Order> OrderMap;
    private Map<String, List<String>> OrderPartnerPairMap;
    private Map<String,DeliveryPartner> PartnerMap;

    public OrderRepository() {

        OrderMap = new HashMap<String,Order>();
        OrderPartnerPairMap = new HashMap<String, List<String>>();
        PartnerMap = new HashMap<String,DeliveryPartner>();
    }

    public void addOrderRepo(Order order){

        OrderMap.put(order.getId(),order);
    }

    public void addPartnerRepo(String partnerId){
        DeliveryPartner deliveryPartner =new DeliveryPartner(partnerId);
        PartnerMap.put(partnerId, deliveryPartner);
    }

    public void addOrderPartnerPairRepo(String orderId, String partnerId){

        List<String> orderIds = new ArrayList<>();

        if(OrderMap.containsKey(orderId) && PartnerMap.containsKey(partnerId)){

            OrderMap.put(orderId , OrderMap.get(orderId));
            PartnerMap.put(partnerId , PartnerMap.get(partnerId));

            if(OrderPartnerPairMap.containsKey(partnerId)){
                orderIds = OrderPartnerPairMap.get(partnerId);
            }
            orderIds.add(orderId);
            PartnerMap.get(partnerId).setNumberOfOrders(orderIds.size());
        }

        OrderPartnerPairMap.put(partnerId , orderIds);


    }

    public Order getOrderByIdRepo(String orderId){

        return OrderMap.get(orderId);
    }

    public DeliveryPartner getPartnerByIdRepo(String partnerId){

        return PartnerMap.get(partnerId);
    }

    public int getOrderCountByPartnerIdRepo(String partnerId){

        int ans=0;
        if(PartnerMap.containsKey(partnerId)){

            ans=OrderPartnerPairMap.get(partnerId).size();
        }
        return ans;
    }

    public List<String>  getOrdersByPartnerIdRepo(String partnerId){

        List<String> orders = new ArrayList<>();

        if(PartnerMap.containsKey(partnerId)){

            orders = OrderPartnerPairMap.get(partnerId);
        }
        return orders;
    }

    public List<String> getAllOrdersRepo(){

        Set<String> getOrders = new HashSet<>();

        getOrders = OrderMap.keySet();

        List<String> getOrderlist = new ArrayList<>();

        for (String item : getOrders){
            getOrderlist.add(item);
        }

        return getOrderlist;
    }


    public int getCountOfUnassignedOrdersRepo(){

        int count = 0;

        for( String partner : OrderPartnerPairMap.keySet()){
            count = count + OrderPartnerPairMap.get(partner).size();
        }
        return (OrderMap.size()-count);
    }


    public int getOrdersLeftAfterGivenTimeByPartnerIdRepo(String time, String partnerId){

        int count = 0;

        int time1 = (Integer.parseInt(time.substring(0,2))*60) +
                (Integer.parseInt(time.substring(time.length()-2,time.length())));

        for(String order : OrderPartnerPairMap.get(partnerId) ){
            if(OrderMap.get(order).getDeliveryTime() > time1){
                count++;
            }
        }

        return count;
    }


    public String getLastDeliveryTimeByPartnerIdRepo(String partnerId){

        int time = 0;
        for(String order : OrderPartnerPairMap.get(partnerId) ){
            if(time<OrderMap.get(order).getDeliveryTime()){
                time = OrderMap.get(order).getDeliveryTime();
            }
        }

        int x= time/60;
        String a=String.format("%2d",x);
        String b=String.format("%2d",time%60);

        return a+":"+b;
    }




    public void deletePartnerByIdRepo(String partnerId){

        if(OrderPartnerPairMap.containsKey(partnerId)){
            for(String order : OrderPartnerPairMap.get(partnerId) ){
                if(OrderMap.containsKey(order)){
                    OrderMap.remove(order);
                }
            }
        }
        OrderPartnerPairMap.remove(partnerId);

        if(PartnerMap.containsKey(partnerId)){
            PartnerMap.remove(partnerId);
        }

    }


    public void deleteOrderByIdRepo(String orderId){

        List<String> ll =new ArrayList<>();
        for(String partner : OrderPartnerPairMap.keySet()){
            for(String order : OrderPartnerPairMap.get(partner)){
                if(order.equals(orderId)){
                    ll = OrderPartnerPairMap.get(partner);
                    OrderPartnerPairMap.remove(partner);
                    ll.remove(order);
                    OrderPartnerPairMap.put(partner,ll);
                    break;
                }
            }
        }

        if(OrderMap.containsKey(orderId)){
            OrderMap.remove(orderId);
        }
    }




}
