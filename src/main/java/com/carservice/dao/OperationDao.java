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
     * Вывод всех клиентов
     */
    public List<Operation> listOperations()
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<Operation> operationList =
                session.createQuery("SELECT p FROM Operation p" +
                        " ORDER By p.operationid").list();

        //вывод в консоль сервера
        for(Operation p: operationList)
        {
            logger.info(p.toString());
        }
        return operationList;
    }



    /**
     * Добавление клиента
     */
    public void addOperation(Operation operation) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(operation);
        logger.info("Customer added: " + operation);
    }

    /**
     * Обновление клиента
     */
    public void updateOperation(Operation c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Customer updated: " + c);
    }

    /**
     * Удаление клиента
     */
    public void removeOperation(int id) {
        Session session = this.sessionFactory.getCurrentSession();
      //  Customer c = (Customer) session.load(Customer.class, new String(id));
        Operation operation = (Operation) session.load(Operation.class, new Integer(id));

        if ( operation != null)
        {
            session.delete(operation);
        }
        logger.info("Operation deleted "+operation);

    }

    /**
     * Нахождение клиента по ид
     */
    public Operation getOperationById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Operation operation = (Operation) session.load(Operation.class, new Integer(id));


        logger.info("Operation found: " + operation);

        return operation;
    }


}
