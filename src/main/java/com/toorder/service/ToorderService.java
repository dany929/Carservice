package com.toorder.service;

import com.toorder.dao.ToorderDao;
import com.toorder.model.Toorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ToorderService
{

    @Autowired
    private ToorderDao toorderDao;

    public void setToorderDao(ToorderDao toorderDao)
    {
        this.toorderDao = toorderDao;
    }

    @Transactional
    public List<Toorder> listToorders()
    {
        return this.toorderDao.listToorders();
    }

    
    @Transactional
    public void addToorder(Toorder toorder)
    {
        this.toorderDao.addToorder(toorder);
     /*
       if(!listToorder().contains(toorder))

       {
           System.err.println("Сервис Адд попал в иф");
           this.toorderDao.addToorder(toorder);
           System.err.println("Сервис Адд попал в иф после дао");
       }
       else
           {
               System.err.println("Сервис Адд попал в елсу");
              this.toorderDao.updateToorder(toorder);
           }

      */
    }
    @Transactional
    public void updateToorder(Toorder toorder)
    {
        this.toorderDao.updateToorder(toorder);
      /*
      if(this.listToorder().contains(toorder))
        {
            System.err.println("Сервис Апдейт попал в иф");
            this.toorderDao.updateToorder(toorder);
        }
        else
        {
            System.err.println("Сервис Апдейт попал в елсу");
            this.toorderDao.addToorder(toorder);
        }

       */
    }
    @Transactional
  public void removeToorder(int id)
    {
        this.toorderDao.removeToorder(id);
    }

    @Transactional
    public Toorder getToorderById(int id)
    {
        return this.toorderDao.getToorderById(id);
    }

}
