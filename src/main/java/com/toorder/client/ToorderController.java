package com.toorder.client;

import com.toorder.model.Toorder;
import com.toorder.service.ToorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class ToorderController {

    private ToorderService toorderService;

    @Autowired(required = true)
    @Qualifier(value = "toorderService")
    public void setToorderService(ToorderService ps)
    {
        this.toorderService = ps;
    }


    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value ="toorders", method = RequestMethod.GET)
    public String listToorder(Model model) {
        model.addAttribute("toorder", new Toorder());
        model.addAttribute("listToorder", this.toorderService.listToorders());
        return "toorders";
    }

    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/toorders/add", method = RequestMethod.POST)
    public String addToOrder(@ModelAttribute("toorder") Toorder toorder)
    {
        System.err.println("Детали у тек объекта"+toorder.getPartid());
        if(!toorderService.listToorders().contains(toorder))
        {
            System.err.println("Контроллер Адд ТООРДЕР попал в иф");
            this.toorderService.addToorder(toorder);
        }
        else
            {
                System.err.println("Контроллер Адд ТООРДЕР попал в елсу");
                this.toorderService.updateToorder(toorder);
            }

        return "redirect:/toorders";
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/removetoorder/{id}")
    public String  removeToOrder(@PathVariable("id") int id)
    {
        this.toorderService.removeToorder(id);
        return  "redirect:/toorders";
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/edittoorder/{id}")
    public String  updateToOrder(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("toorder", this.toorderService.getToorderById(id));
        model.addAttribute("listToorder", this.toorderService.listToorders());

        System.err.println("Контроллер апдейт заполнил");

        return "toorders";
    }

/*
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Customer> listFilter()
    {
        return this.toorderService.listFilter();
    }

    @RequestMapping("customerdata/{id}")
    public String bookData(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", this.toorderService.getCustomerById(id));

        return "customerdata";
    }
   // */



}

