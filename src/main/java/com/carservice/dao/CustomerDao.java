package com.carservice.dao;

import com.carservice.model.Customer;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CustomerDao
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf)
    {
        this.sessionFactory = sf;
    }

    Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    /**
     * Вывод всех клиентов
     */
    public List<Customer> listCustomers()
    {
        Session session = this.sessionFactory.openSession();
        List<Customer> customerList =
                session.createQuery("SELECT p FROM Customer p ").list();

        //вывод в консоль сервера
        for(Customer p: customerList)
        {
            logger.info(p.toString());
        }
        session.close();
        return customerList;
    }

    /**
     * Добавление клиента
     */
    public void addCustomer(Customer customer) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(customer);
        session.getTransaction().commit();
        logger.info("Customer added: " + customer);
        session.close();
    }

    /**
     * Обновление клиента
     */
    public void updateCustomer(Customer c) {
        Session session = this.sessionFactory.openSession();
        session.update(c);
        logger.info("Customer updated: " + c);
        session.close();
    }

    /**
     * Удаление клиента
     */
    public void removeCustomer(String id) {
        Session session = this.sessionFactory.openSession();
        List<Customer> customerList  =
                session.createQuery("select c from Customer c where c.gosznak =" + "'"+id+"'").list();
        for (Customer c : customerList)
        {
            session.beginTransaction();
            session.delete(c);
            session.getTransaction().commit();
            logger.info("Customer removed: " + c);
        }
        session.close();
    }

    /**
     * Нахождение клиента по ид
     */
    public Customer getCustomerById(String id) {
        Session session = this.sessionFactory.openSession();
        Customer customer = new Customer();
        List<Customer> customerList  =
                session.createQuery("select  c from Customer c where c.gosznak ="+"'"+id+"'").list();
        for (Customer c : customerList)
        {
            customer=c;
        }

        logger.info("Customer found: " + customer);
        session.close();
        return customer;
    }


}
