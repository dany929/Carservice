package com.carservice.dao;

import com.carservice.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PartDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    Logger logger = LoggerFactory.getLogger(PartDao.class);

    /**
     * Вывод всех клиентов
     */

    public List<Part> listParts()
    {
        Session session = this.sessionFactory.openSession();
        List<Part> partList =
                session.createQuery("SELECT p FROM Part p ORDER BY p.partid").list();
        session.close();
        //вывод в консоль сервера
        for(Part p: partList)
        {
            logger.warn(p.toString());
        }

        return partList;
    }



    /**
     * Добавление клиента
     */
    public void addPart(Part part) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(part);
        session.getTransaction().commit();
        logger.warn("Part added: " + part);
        session.close();
    }

    /**
     * Обновление клиента
     */
    public void updatePart(Part c) {
        Session session = this.sessionFactory.openSession();
        session.update(c);
        logger.info("Customer updated: " + c);
        session.close();
    }

    /**
     * Удаление клиента
     */
    public void removePart(int id) {
        Session session = this.sessionFactory.openSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        Part part = (Part) session.load(Part.class, new Integer(id));

        if ( part != null)
        {
            session.beginTransaction();
            session.delete(part);
            session.getTransaction().commit();
        }
        logger.info("Part deleted "+part);
        session.close();
    }

    /**
     * Нахождение клиента по ид
     */
    public Part getPartById(int id) {
        Session session = this.sessionFactory.openSession();
       // Part part = (Part) session.load(Part.class, new Integer(id));
        Part p = new Part();
        session.load(p, id);
        logger.warn("Part found: " + p.toString());
        session.close();
        return p;
    }

    //Запрос на вывод заказанных запчастей

    public List<Part> filterByOrdered()
    {
        Session session = this.sessionFactory.openSession();
        List<Part> partList = session.createQuery("SELECT s FROM Part s where s.partid IN (SELECT a.part.partid FROM Toorder a)").list();
        for(Part part : partList)
        {
            logger.info("Фильтр по цене"+part.toString());
        }
        session.close();
        return partList;
    }

}
