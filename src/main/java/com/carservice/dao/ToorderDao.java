package com.carservice.dao;


import com.carservice.model.Toorder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Session session = this.sessionFactory.getCurrentSession();
        List<Toorder> toorderList =
                session.createQuery("SELECT p FROM Toorder p ORDER BY p.order.orderid").list();

        //вывод в консоль сервера
        for(Toorder p: toorderList)
        {
            logger.info(p.toString());
        }
        return toorderList;
    }



    /**
     * Добавление клиента
     */
    public void addToorder(Toorder toorder) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(toorder);
        logger.info("Customer added: " + toorder);
    }

    /**
     * Обновление клиента
     */
    public void updateToorder(Toorder c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Customer updated: " + c);
    }

    /**
     * Удаление клиента
     */
    public void removeToorder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        List<Toorder> toorderList  =
                session.createQuery("select  c from Toorder c" +
                        " where c.orderid ="+id/100+
                        " and c.partid="+id/10%10+
                        " and c.operationid="+id%10).list();
        for (Toorder c : toorderList)
        {
            session.delete(c);
        }

    }

    /**
     * Нахождение клиента по ид
     */
    public Toorder getToorderById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Toorder toorder = new Toorder();
        List<Toorder> toorderList  =
                session.createQuery("select  c from Toorder c" +
                        " where c.orderid ="+id/100+
                        " and c.partid="+id/10%10+
                        " and c.operationid="+id%10).list();
        for (Toorder c : toorderList)
        {
            toorder=c;
        }

        logger.info("Toorder found: " + toorder);

        return toorder;
    }


}
