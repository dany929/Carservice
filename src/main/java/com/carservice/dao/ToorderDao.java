package com.carservice.dao;


import com.carservice.model.Toorder;
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
public class ToorderDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    Logger logger = LoggerFactory.getLogger(ToorderDao.class);

    /**
     * Вывод всех клиентов
     */
    public List<Toorder> listToorders()
    {
        Session session = this.sessionFactory.openSession();
        List<Toorder> toorderList =
                session.createQuery("SELECT p FROM Toorder p ORDER BY p.order.orderid").list();

        //вывод в консоль сервера
        for(Toorder p: toorderList)
        {
            logger.info(p.toString()+p.getOrder().getCustomer().getGosznak().toString());
        }
        session.close();
        return toorderList;
    }

    public int listLastToorder()
    {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session
                .createCriteria(Toorder.class)
                .setProjection(Projections.max("toorderid"));
        Integer maxOrder = (Integer)criteria.uniqueResult();

        if(maxOrder==null)
        {
            maxOrder=0;
        }
        session.close();
        return maxOrder;
        /*

        List<Toorder> toorderList =
                session.createQuery("SELECT max(p.toorderid) as p.toorderid FROM Toorder p").list();

        //вывод в консоль сервера
        for(Toorder p: toorderList)
        {
            logger.info(p.toString()+p.getOrder().getCustomer().getGosznak().toString());
        }
        session.close();
        return toorderList;

         */
    }


    /**
     * Добавление клиента
     */
    public void addToorder(Toorder toorder) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.merge(toorder);
        session.getTransaction().commit();
        logger.info("Toorder added: " + toorder);
        session.close();
    }

    public void addToordertoExistingOrder(Toorder toorder) {

        Session session = this.sessionFactory.openSession();

        session.beginTransaction();
        logger.info("Toorder before: " + toorder);
        System.err.println("Адд ту эксзистинг");

        session.save(toorder);

        session.getTransaction().commit();
        logger.info("Toorder added to existing : " + toorder);
        session.close();
    }


    /**
     * Обновление клиента
     */
    public void updateToorder(Toorder c) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(c);
        session.getTransaction().commit();

        logger.info("Customer updated: " + c);
        session.close();
    }

    /**
     * Удаление клиента
     */
    public void removeToorder(int id) {
        Session session = this.sessionFactory.openSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        List<Toorder> toorderList  =
                session.createQuery("select  c from Toorder c where c.toorderid ="+id).list();
        for (Toorder c : toorderList)
        {
            session.beginTransaction();
            session.delete(c);
            session.getTransaction().commit();
            logger.info("Toorder deleted: " + c+c.getOrder().toString());
        }
        session.close();
    }

    /**
     * Нахождение клиента по ид
     */
    public Toorder getToorderById(int id) {
        Session session = this.sessionFactory.openSession();
        Toorder toorder = new Toorder();
        List<Toorder> toorderList  =
                session.createQuery("select  c from Toorder c where c.toorderid ="+id).list();
        for (Toorder c : toorderList)
        {
            toorder=c;
        }

        logger.info("Toorder found: " + toorder+toorder.getOrder());
        session.close();
        return toorder;
    }

    public void deleteToorder(Toorder o)
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

}
