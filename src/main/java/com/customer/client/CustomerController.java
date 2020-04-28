package com.customer.client;

import com.customer.model.Customer;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService ps)
    {
        this.customerService = ps;
    }


    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value ="customers", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCustomers", this.customerService.listCustomers());
        return "customers";
    }

    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/customers/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer)
    {

        if(!customerService.listCustomers().contains(customer))
        {
            System.err.println("Контроллер Адд попал в иф");
            this.customerService.addCustomer(customer);
        }
        else
        {
            System.err.println("Контроллер Адд попал в елсу");
            this.customerService.updateCustomer(customer);
        }

        return "redirect:/customers";
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/remove/{id}")
    public String  removeCustomer(@PathVariable("id") String id)
    {
        this.customerService.removeCustomer(id);
        return  "redirect:/customers";
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/edit/{id}")
    public String  updateCustomer(@PathVariable("id") String id, Model model)
    {
        model.addAttribute("customer", this.customerService.getCustomerById(id));
        model.addAttribute("listCustomers", this.customerService.listCustomers());

        System.err.println("Контроллер апдейт заполнил");

        return "customers";
    }


    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Customer> listFilter()
    {
        return this.customerService.listFilter();
    }
/*
    @RequestMapping("customerdata/{id}")
    public String bookData(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", this.customerService.getCustomerById(id));

        return "customerdata";
    }
*/


}

