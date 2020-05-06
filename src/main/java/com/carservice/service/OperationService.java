package com.carservice.service;

import com.carservice.dao.OperationDao;
import com.carservice.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService
{

    @Autowired
    private OperationDao operationDao;

    public void setOperationDao(OperationDao operationDao)
    {
        this.operationDao = operationDao;
    }


    public List<Operation> listOperations()
    {
        return this.operationDao.listOperations();
    }



    public void addOperation(Operation Operation)
    {
        this.operationDao.addOperation(Operation);
     /*
       if(!listOperation().contains(Operation))

       {
           System.err.println("Сервис Адд попал в иф");
           this.OperationDao.addOperation(Operation);
           System.err.println("Сервис Адд попал в иф после дао");
       }
       else
           {
               System.err.println("Сервис Адд попал в елсу");
              this.OperationDao.updateOperation(Operation);
           }

      */
    }

    public void updateOperation(Operation Operation)
    {
        this.operationDao.updateOperation(Operation);
      /*
      if(this.listOperation().contains(Operation))
        {
            System.err.println("Сервис Апдейт попал в иф");
            this.OperationDao.updateOperation(Operation);
        }
        else
        {
            System.err.println("Сервис Апдейт попал в елсу");
            this.OperationDao.addOperation(Operation);
        }

       */
    }

    public void removeOperation(int id)
    {
        this.operationDao.removeOperation(id);
    }


    public Operation getOperationById(int id)
    {
        return this.operationDao.getOperationById(id);
    }

}
