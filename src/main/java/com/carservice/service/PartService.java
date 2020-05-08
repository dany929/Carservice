package com.carservice.service;

import com.carservice.dao.PartDao;
import com.carservice.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    @Autowired
    private PartDao partDao;

    public void setPartDao(PartDao partDao) {
        this.partDao = partDao;
    }


    public List<Part> listParts() {
        return this.partDao.listParts();
    }


    public void addPart(Part part) {
        this.partDao.addPart(part);

    }

    public void updatePart(Part part) {
        this.partDao.updatePart(part);

    }

    public void removePart(int id) {
        this.partDao.removePart(id);
    }


    public Part getPartById(int id) {
        return this.partDao.getPartById(id);
    }

    public List<Part> filterByOrdered() {
        return this.partDao.filterByOrdered();
    }
}
