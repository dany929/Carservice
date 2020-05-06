package com.carservice.client;

import com.carservice.model.Operation;
import com.carservice.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value ="operations", method = RequestMethod.GET)
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
    public String addOperation(@ModelAttribute("operation") Operation operation)
    {
        this.operationService.addOperation(operation);
        /*
        if(operation.getOperationid() == 0)
        {
            System.err.println("Контроллер Адд попал в иф");
            this.operationService.addOperation(operation);
        }
        else
        {
            System.err.println("Контроллер Адд попал в елсу");
            this.operationService.updateOperation(operation);
        }
         */

        return "redirect:/operations";
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/removeoperation/{id}")
    public String  removeOperation(@PathVariable("id") int id)
    {
        this.operationService.removeOperation(id);
        return  "redirect:/operations";
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/editoperation/{id}")
    public String  updateOperation(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("operation", this.operationService.getOperationById(id));
        model.addAttribute("listOperations", this.operationService.listOperations());

        System.err.println("Контроллер апдейт заполнил");

        return "operations";
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

