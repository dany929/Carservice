package com.carservice.dao;

import com.carservice.model.Part;
import org.hibernate.SQLQuery;
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


    //Сложный запрос выводящий товары цена которых выше средней
    @SuppressWarnings("unchecked")
    public List<Part> filterProductByCost()
    {
        Session session = this.sessionFactory.openSession();
        List<Part> partList = session.createQuery(
                "SELECT s FROM Part s WHERE s.price > (SELECT avg(s.price) FROM Part s)").list();
        for(Part product : partList)
        {
            logger.info(product.toString());
        }
        session.close();
        return partList;
    }
////////////////////////НЕ РОБИТ////
    //Сложный запрос выводящий все товары которые входят в заказы цены которых выше 30000
    @SuppressWarnings("unchecked")
    public List<Part> filterByOrderCostProducts()
    {
        Session session = this.sessionFactory.openSession();
        String sql = "SELECT parts.partid, parts.category, parts.price, parts.title from parts ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Part.class);
        List<Part> partList = query.list();

        /*
        List<Part> partList = session.createQuery("SELECT part.category, sum(part.price) " +
                "FROM Part part GROUP BY part.category ").list();
        for(Part product : partList)
        {
            logger.info(product.toString());
        }
        */
        session.close();
        return partList;
    }

}
