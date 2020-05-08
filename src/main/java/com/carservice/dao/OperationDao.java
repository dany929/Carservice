package com.carservice.dao;


import com.carservice.model.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OperationDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    Logger logger = LoggerFactory.getLogger(OperationDao.class);

    /**
     * Вывод всех операций
     */
    public List<Operation> listOperations()
    {
        Session session = this.sessionFactory.openSession();
        List<Operation> operationList =
                session.createQuery("SELECT p FROM Operation p" +
                        " ORDER By p.operationid").list();

        //вывод в консоль сервера
        for(Operation p: operationList)
        {
            logger.info(p.toString());
        }
        session.close();
        return operationList;

    }



    /**
     * Добавление операции
     */
    public void addOperation(Operation operation) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(operation);
        session.getTransaction().commit();
        logger.info("Customer added: " + operation);
        session.close();
    }

    /**
     * Обновление операции
     */
    public void updateOperation(Operation c) {
        Session session = this.sessionFactory.openSession();
        session.update(c);
        logger.info("Customer updated: " + c);
        session.close();
    }

    /**
     * Удаление операции
     */
    public void removeOperation(int id) {
        Session session = this.sessionFactory.openSession();
        Operation operation = (Operation) session.load(Operation.class, new Integer(id));

        if ( operation != null)
        {
            session.beginTransaction();
            session.delete(operation);
            session.getTransaction().commit();
        }
        logger.info("Operation deleted "+operation);
        session.close();
    }

    /**
     * Нахождение операции по ид
     */
    public Operation getOperationById(int id) {
        Session session = this.sessionFactory.openSession();
        Operation operation = (Operation) session.load(Operation.class, new Integer(id));


        logger.info("Operation found: " + operation);
        session.close();
        return operation;
    }


}
