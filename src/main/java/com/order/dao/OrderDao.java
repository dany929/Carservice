package com.order.dao;


import com.order.model.Order;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    Logger logger = LoggerFactory.getLogger(OrderDao.class);

    /**
     * Вывод всех клиентов
     */
    public List<Order> listOrders()
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> orderList =
                session.createQuery("SELECT p FROM Order p ORDER BY p.orderid").list();

        //вывод в консоль сервера
        for(Order p: orderList)
        {
            logger.info(p.toString());
        }
        return orderList;
    }



    /**
     * Добавление клиента
     */
    public void addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        logger.info("Customer added: " + order);
    }

    /**
     * Обновление клиента
     */
    public void updateOrder(Order c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Customer updated: " + c);
    }

    /**
     * Удаление клиента
     */
    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        Order order = (Order) session.load(Order.class, new Integer(id));

        if ( order != null)
        {
            session.delete(order);
        }
        logger.info("Order deleted "+order);

    }

    /**
     * Нахождение клиента по ид
     */
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, new Integer(id));


        logger.info("Order found: " + order);

        return order;
    }


}
