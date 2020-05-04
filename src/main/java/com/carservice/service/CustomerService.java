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


    public  List<Customer> listFilter()
    {
        return  this.customerDao.listCustomerFiltered();
    }


    public void addCustomer(Customer customer)
    {
        this.customerDao.addCustomer(customer);
     /*
       if(!listCustomers().contains(customer))

       {
           System.err.println("Сервис Адд попал в иф");
           this.customerDao.addCustomer(customer);
           System.err.println("Сервис Адд попал в иф после дао");
       }
       else
           {
               System.err.println("Сервис Адд попал в елсу");
              this.customerDao.updateCustomer(customer);
           }

      */
    }

    public void updateCustomer(Customer customer)
    {
        this.customerDao.updateCustomer(customer);
      /*
      if(this.listCustomers().contains(customer))
        {
            System.err.println("Сервис Апдейт попал в иф");
            this.customerDao.updateCustomer(customer);
        }
        else
        {
            System.err.println("Сервис Апдейт попал в елсу");
            this.customerDao.addCustomer(customer);
        }

       */
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
