package com.part.dao;

import com.part.model.Part;


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
        Session session = this.sessionFactory.getCurrentSession();
        List<Part> partList =
                session.createQuery("SELECT p FROM Part p ORDER BY p.partid").list();

        //вывод в консоль сервера
        for(Part p: partList)
        {
            logger.info(p.toString());
        }
        return partList;
    }



    /**
     * Добавление клиента
     */
    public void addPart(Part part) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(part);
        logger.info("Customer added: " + part);
    }

    /**
     * Обновление клиента
     */
    public void updatePart(Part c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Customer updated: " + c);
    }

    /**
     * Удаление клиента
     */
    public void removePart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        Part part = (Part) session.load(Part.class, new Integer(id));

        if ( part != null)
        {
            session.delete(part);
        }
        logger.info("Part deleted "+part);

    }

    /**
     * Нахождение клиента по ид
     */
    public Part getPartById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Part part = (Part) session.load(Part.class, new Integer(id));


        logger.info("Part found: " + part);

        return part;
    }


}
