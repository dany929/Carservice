package com.carservice.service;

import com.carservice.dao.PartDao;
import com.carservice.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService
{

    @Autowired
    private PartDao partDao;

    public void setPartDao(PartDao partDao)
    {
        this.partDao = partDao;
    }


    public List<Part> listParts()
    {
        return this.partDao.listParts();
    }

    

    public void addPart(Part part)
    {
        this.partDao.addPart(part);
     /*
       if(!listPart().contains(part))

       {
           System.err.println("Сервис Адд попал в иф");
           this.partDao.addPart(part);
           System.err.println("Сервис Адд попал в иф после дао");
       }
       else
           {
               System.err.println("Сервис Адд попал в елсу");
              this.partDao.updatePart(part);
           }

      */
    }

    public void updatePart(Part part)
    {
        this.partDao.updatePart(part);
      /*
      if(this.listPart().contains(part))
        {
            System.err.println("Сервис Апдейт попал в иф");
            this.partDao.updatePart(part);
        }
        else
        {
            System.err.println("Сервис Апдейт попал в елсу");
            this.partDao.addPart(part);
        }

       */
    }

  public void removePart(int id)
    {
        this.partDao.removePart(id);
    }


    public Part getPartById(int id)
    {
        return this.partDao.getPartById(id);
    }

}
