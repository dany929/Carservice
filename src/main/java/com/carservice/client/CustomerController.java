package com.carservice.client;

import com.carservice.model.Customer;
import com.carservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value ="/customers/", method = RequestMethod.GET)
    public ModelAndView listCustomers() {
        ModelAndView model =new ModelAndView("customers");
        model.addObject("customer", new Customer());
        model.addObject("listCustomers", this.customerService.listCustomers());
        return model;
    }



    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/customers/add", method = RequestMethod.POST)
    public ModelAndView addCustomer(Customer customer)
    {
        this.customerService.addCustomer(customer);

        return new ModelAndView("redirect:/customers/") ;
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping(value = "/customers/remove", method = RequestMethod.POST)
    public ModelAndView  removeCustomer(@RequestParam String id)
    {
        this.customerService.removeCustomer(id);
        return  new ModelAndView("redirect:/customers/") ;
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping(value = "/customers/edit", method = RequestMethod.POST)
    public ModelAndView  updateCustomer(@RequestParam String id)
    {
        ModelAndView model = new ModelAndView("customers");
        model.addObject("customer", this.customerService.getCustomerById(id));
        model.addObject("listCustomers", this.customerService.listCustomers());

        System.err.println("Контроллер апдейт заполнил");

        return model;
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

