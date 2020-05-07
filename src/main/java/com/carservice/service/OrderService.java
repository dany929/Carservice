package com.carservice.service;

import com.carservice.dao.OrderDao;
import com.carservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


    }

    public void updateOrder(Order order)
    {
        this.orderDao.updateOrder(order);

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

    public void competeOrder(int id)
    {
        LocalDate localDate = LocalDate.now();
        Order order = this.findOrder(id);
        order.setDateout(DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate));
        this.updateOrder(order);
    }

}
