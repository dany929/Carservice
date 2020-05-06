package com.carservice.service;

import com.carservice.dao.OrderDao;
import com.carservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//////////////////////
    /*
    @Transactional
    public List<Part> listPartsByOrder(){
        return this.orderDao.listPartsByOrder();
    }

     */
    //////////////////////



    public List<Order> listOrders()
    {
        return this.orderDao.listOrders();
    }

    public int listLastOrder(){return this.orderDao.listLastOrder();}


    public void addOrder(Order order)
    {
        System.err.println("ADDORDER SERVICE");
/*
        for(Toorder line : order.getToorders())
        {
            System.err.println("до сета одера");
            line.setOrder(order);
            System.err.println("После сета");
        }
*/
        System.err.println("Пошел в дао");
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

  public void removeOrder(int id)
    {



        this.orderDao.removeOrder(id);
    }


    //////
    public void deleteOrders(int id)
    {
        this.orderDao.deleteOrders(findOrder(id));
    }

    public Order findOrder(int id) {
        return this.orderDao.findOrders(id);
    }
/////////////

    public Order getOrderById(int id)
    {
        return this.orderDao.getOrderById(id);
    }

}
