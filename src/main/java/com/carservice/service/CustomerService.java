package com.carservice.service;

import com.carservice.dao.CustomerDao;
import com.carservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{

    @Autowired
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }


    public List<Customer> listCustomers()
    {
        return this.customerDao.listCustomers();
    }


    public void addCustomer(Customer customer)
    {
        this.customerDao.addCustomer(customer);
         }

    public void updateCustomer(Customer customer)
    {
        this.customerDao.updateCustomer(customer);

    }

    public void removeCustomer(String id)
    {
        this.customerDao.removeCustomer(id);
    }


    public Customer getCustomerById(String gosznak)
    {
        return this.customerDao.getCustomerById(gosznak);
    }

}
