package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrderServ(Order order){

        orderRepository.addOrderRepo(order);
    }

    public void addPartnerServ(String partnerId){

        orderRepository.addPartnerRepo(partnerId);
    }

    public void addOrderPartnerPairServ(String orderId, String partnerId){

        orderRepository.addOrderPartnerPairRepo(orderId, partnerId);

    }

    public Order getOrderByIdServ(String orderId){

        return orderRepository.getOrderByIdRepo(orderId);
    }

    public DeliveryPartner getPartnerByIdServ(String partnerId){

        return orderRepository.getPartnerByIdRepo(partnerId);
    }

    public int getOrderCountByPartnerIdServ(String partnerId){

        return orderRepository.getOrderCountByPartnerIdRepo(partnerId);
    }

    public List<String>  getOrdersByPartnerIdServ(String partnerId){

        return orderRepository.getOrdersByPartnerIdRepo(partnerId);
    }

    public List<String> getAllOrdersServ(){

        return orderRepository.getAllOrdersRepo();
    }


    public int getCountOfUnassignedOrdersServ(){

        return orderRepository.getCountOfUnassignedOrdersRepo();
    }


   public int getOrdersLeftAfterGivenTimeByPartnerIdServ(String time, String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerIdRepo(time, partnerId);
   }

   public String getLastDeliveryTimeByPartnerIdServ(String partnerId) {
       return orderRepository.getLastDeliveryTimeByPartnerIdRepo(partnerId);
   }


   public void deletePartnerByIdServ(String partnerId) {

       orderRepository.deletePartnerByIdRepo(partnerId);
   }


    public void deleteOrderByIdServ(String orderId){

        orderRepository.deleteOrderByIdRepo(orderId);
    }

}
