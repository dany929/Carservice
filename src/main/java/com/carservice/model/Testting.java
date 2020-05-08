package com.carservice.model;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import java.util.List;

public class Testting {

    @Test
    public void crud()
    {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        read1(session);

    }

    private void read(Session session) {
        List<Part> partList = session.createQuery("SELECT s FROM Part s where s.partid IN (SELECT a.part.partid FROM Toorder a) ").list();
        for (Part c : partList)
        {

                System.out.println("PRODUCT:" +c.toString() );

        }
    }


    private void read1(Session session) {
        List<Toorder> partList = session.createQuery("SELECT sum(t.numofparts) FROM Toorder t").list();
        for (Toorder c : partList)
        {

            System.out.println("PRODUCT:" +c.toString() );

        }
    }

}