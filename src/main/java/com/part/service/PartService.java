package com.part.service;

import com.part.dao.PartDao;
import com.part.model.Part;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;


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

    @Transactional
    public List<Part> listParts()
    {
        return this.partDao.listParts();
    }

    
    @Transactional
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
    @Transactional
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
    @Transactional
  public void removePart(int id)
    {
        this.partDao.removePart(id);
    }

    @Transactional
    public Part getPartById(int id)
    {
        return this.partDao.getPartById(id);
    }

}
