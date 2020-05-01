package com.Carservice.service;

import com.Carservice.dao.OrderDao;

import com.Carservice.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService
{

    @Autowired
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao)
    {
        this.orderDao = orderDao;
    }

    @Transactional
    public List<Order> listOrders()
    {
        return this.orderDao.listOrders();
    }

    
    @Transactional
    public void addOrder(Order order)
    {
        this.orderDao.addOrder(order);
     /*
       if(!listOrder().contains(order))

       {
           System.err.println("Сервис Адд попал в иф");
           this.orderDao.addOrder(order);
           System.err.println("Сервис Адд попал в иф после дао");
       }
       else
           {
               System.err.println("Сервис Адд попал в елсу");
              this.orderDao.updateOrder(order);
           }

      */
    }
    @Transactional
    public void updateOrder(Order order)
    {
        this.orderDao.updateOrder(order);
      /*
      if(this.listOrder().contains(order))
        {
            System.err.println("Сервис Апдейт попал в иф");
            this.orderDao.updateOrder(order);
        }
        else
        {
            System.err.println("Сервис Апдейт попал в елсу");
            this.orderDao.addOrder(order);
        }

       */
    }
    @Transactional
  public void removeOrder(int id)
    {
        this.orderDao.removeOrder(id);
    }

    @Transactional
    public Order getOrderById(int id)
    {
        return this.orderDao.getOrderById(id);
    }

}
