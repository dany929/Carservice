package com.carservice.dao;


import com.carservice.model.Order;
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
//////////////////////////////////////////
    /*
    public List<Part> listPartsByOrder()
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<Part> partList= session.createQuery("Select p FROM Part p where p.partid=" +
                "ANY(Select o.partid FROM Toorder o )").list();
        for(Part part    : partList)
        {
            logger.info(part.toString());
        }
        return partList;
    }

*//////////////////////////////////////////
    /**
     * Добавление клиента
     */
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        session.saveOrUpdate(order);
        logger.info("Order added: " + order);
        session.close();
    }

    /**
     * Обновление клиента
     */
    public void updateOrder(Order c) {
        Session session = this.sessionFactory.openSession();
        session.update(c);
        logger.info("Order updated: " + c);
        session.close();
    }

    /**
     * Удаление клиента
     */
    public void removeOrder(int id) {
        Session session = this.sessionFactory.openSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        Order order = (Order) session.load(Order.class, new Integer(id));

        if ( order != null)
        {
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        }
        logger.info("Order deleted "+order);
        session.close();
    }

    /**
     * Нахождение клиента по ид
     */
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Order order = (Order) session.load(Order.class, new Integer(id));


        logger.info("Order found: " + order);
        session.close();
        return order;
    }

///////////////////////////
    public void deleteOrders(Order o)
    {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
        }
        catch (Exception e)
        {
            logger.info("!!!!!!!!!!" + e.toString());
        }
        session.close();
    }

    @SuppressWarnings("unchecked")
    public Order findOrders(int id)
    {
        Session session = this.sessionFactory.openSession();
        Order c = new Order();
        try
        {
            session.load(c, id);
            //Из-за ленивой загрузки необходимо вызвать связанную сущность пока сессия ещё открыта
            logger.info(c.toString() +  c.getToorders().toString());
        }
        catch (Exception e)
        {
            logger.info("!!!!!!!!!!" + e.toString());
        }
        session.close();
        return c;
    }
}

