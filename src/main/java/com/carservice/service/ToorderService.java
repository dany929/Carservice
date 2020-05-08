package com.carservice.service;

import com.carservice.dao.ToorderDao;
import com.carservice.model.Toorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public List<Toorder> listToorders()
    {
        return this.toorderDao.listToorders();
    }

    public int listLastToorder(){return this.toorderDao.listLastToorder();}



    public void addToorder(Toorder toorder)
    {
        this.toorderDao.addToorder(toorder);

    }

    public void updateToorder(Toorder toorder)
    {
        this.toorderDao.updateToorder(toorder);

    }



  public void removeToorder(int id)
    {
        this.toorderDao.removeToorder(id);
    }


    public Toorder getToorderById(int id)
    {
        return this.toorderDao.getToorderById(id);
    }


    public void deleteOrderLine(Toorder o) { this.toorderDao.deleteToorder(o);
    }
}
