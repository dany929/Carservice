package com.carservice.dao;


import com.carservice.model.Order;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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
        Session session = this.sessionFactory.openSession();
        List<Order> orderList =
                session.createQuery("SELECT p FROM Order p ORDER BY p.orderid DESC").list();

        //вывод в консоль сервера
        for(Order p: orderList)
        {
            logger.info(p.toString()+p.getToorders().toString());
        }
        session.close();
        return orderList;
    }

   public List<Order> listFilteredOrders()
   {
       Session session = this.sessionFactory.openSession();

       List<Order> orderList =   session.createQuery("SELECT s FROM Order s ").list();
       for(Order p: orderList)
       {
           logger.info(p.toString()+p.getCustomer().toString());
       }
       session.close();
       return orderList;
   }


    public int listLastOrder()
    {


        Session session = this.sessionFactory.openSession();

        Criteria criteria = session
                .createCriteria(Order.class)
                .setProjection(Projections.max("orderid"));
        Integer maxOrder = (Integer)criteria.uniqueResult();
        session.close();
        if(maxOrder==null)
        {
            maxOrder=0;
        }
        return maxOrder;
        /*
        List<Order> orderList =
                session.createQuery("SELECT max(p.orderid) as FROM Order p ").list();

        //вывод в консоль сервера
        for(Order p: orderList)
        {
            logger.info(p.toString()+p.getToorders().toString());
        }
        session.close();
        return orderList;*/
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
        System.err.println("Зашел в дао");
        Session session = this.sessionFactory.openSession();
        System.err.println("Начинаем транзакцию");
        session.beginTransaction();

        System.err.println("Перед сейвапдейт");
        logger.warn("!!!!!!!!!!!!!!!!!!!!!!" +
                "Перед added: " + order + order.getToorders().toString());

        session.save(order);

        System.err.println("после сейв апдейт");

        session.getTransaction().commit();

        logger.warn("!!!!!!!!!!!!!!!!!!!!!!" +
                "Order added: " + order + order.getToorders().toString());
        session.close();
    }

    /**
     * Обновление клиента
     */
    public void updateOrder(Order c) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
        logger.info("Order updated: " + c+c.getToorders());
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
        logger.info("Order deleted "+order + order.getToorders().toString());
        session.close();
    }

    /**
     * Нахождение клиента по ид
     */
    public Order getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Order order = (Order) session.load(Order.class, new Integer(id));


        logger.info("Order found: " + order+order.getToorders().toString());
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

