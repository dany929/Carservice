package com.carservice.client;

import com.carservice.model.Operation;
import com.carservice.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class OperationController {

    private OperationService operationService;

    @Autowired(required = true)
    @Qualifier(value = "operationService")
    public void setOperationService(OperationService ps)
    {
        this.operationService = ps;
    }


    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value ="/operations/", method = RequestMethod.GET)
    public String listOperations(Model model) {
        model.addAttribute("operation", new Operation());
        model.addAttribute("listOperations", this.operationService.listOperations());
        return "operations";
    }

    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/operations/add", method = RequestMethod.POST)
    public ModelAndView addOperation(Operation operation)
    {
        this.operationService.addOperation(operation);
        return new ModelAndView("redirect:/operations/") ;
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping(value = "/operations/remove", method = RequestMethod.POST)
    public ModelAndView  removeOperation(@RequestParam int id)
    {
        this.operationService.removeOperation(id);
        return  new ModelAndView("redirect:/operations/") ;
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping(value = "/operations/edit", method = RequestMethod.POST)
    public ModelAndView  updateOperation(@RequestParam int id)
    {
        ModelAndView model = new ModelAndView("operations");
        model.addObject("operation", this.operationService.getOperationById(id));
        model.addObject("listOperations", this.operationService.listOperations());
        System.err.println("Контроллер апдейт заполнил");
        return model;
    }

/*
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Customer> listFilter()
    {
        return this.operationService.listFilter();
    }

    @RequestMapping("customerdata/{id}")
    public String bookData(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", this.operationService.getCustomerById(id));

        return "customerdata";
    }
   // */



}

