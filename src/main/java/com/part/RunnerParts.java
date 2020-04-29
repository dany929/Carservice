package com.part;

import com.part.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;


public class RunnerParts {
    @Test
    public void crud()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        getAll(session);
       // add(session);
        findById(session,"2");
        /*
        updateById(session,"A666AA152","Bartolomeo","Simpson","8-800-555-35-35");
        deleteById(session," ");
        getAll(session);
        */
        session.close();
    }

    private void getAll(Session session) {
        List<Part> customerList = session.createQuery("SELECT c FROM Part c ").list();
        System.out.println("Full list of Customers---------");
        for (Part c : customerList)
        {
            System.out.println(c);
        }
        System.out.println("------------------------------");
    }

    private void findById(Session session,String id)
    {
        List<Part> customerList  = session.createQuery("select  c from Part c where c.partid ="+"'"+id+"'").list();
        for (Part c : customerList) {

            System.out.println("Customer FOUND by id: " + c);
            System.out.println(" ");
        }
        getAll(session);
    }
/*
    private void deleteById(Session session,String id)
    {
        List<Customer> customerList  = session.createQuery("select c from Customer c where c.gosznak =" + "'"+id+"'").list();
        session.beginTransaction();
        for (Customer c : customerList)
        {
            session.delete(c);
        }
        session.getTransaction().commit();
        System.out.println("DELETED customer with GOSZNAK="+id);
        System.out.println(" ");
    }

    private void updateById(Session session,String id, String firstname, String lastname, String tel)
    {
        List<Customer> customerList  = session.createQuery("select c from Customer c where c.gosznak =" + "'"+id+"'").list();
        session.beginTransaction();
        for (Customer c : customerList)
        {
            c.setFirstname(firstname);
            c.setLastname(lastname);
            c.setTel(tel);
            session.update(c);
        }
        session.getTransaction().commit();
        for (Customer c : customerList)
        {
            System.out.println("UPDATED customer: " + c);
            System.out.println(" ");
        }
        getAll(session);
    }


    private void add(Session session)
    {
        Customer customer = new Customer();
        customer.setGosznak("A666AA152");
        customer.setFirstname("Lisa");
        customer.setLastname("Simpson");
        customer.setTel("8-910-145-88-33");
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        System.out.println("ADDED new customer: "+customer);
        System.out.println(" ");
        getAll(session);
    }



 */
}
